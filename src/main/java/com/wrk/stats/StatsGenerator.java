package com.wrk.stats;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class StatsGenerator {
	private static final int batchSize = 100;
	private static final String COMMA = ",";
	private static Map<String, String> locations = new HashMap<>();

	static {
		locations.put("NY:PRD", "");
		locations.put("LN:PRD", "");
		locations.put("HK:PRD", "");
		locations.put("NY:PRD", "");
		locations.put("NY:PRD", "");
		locations.put("NY:PRD", "");
	}

	public StatsGenerator() {
	}

	public static void main(String[] args) throws IOException,
			InterruptedException, ExecutionException {
		List<Future<TradeTypeStatsWrapper>> result = null;
		if (args != null && args.length != 0) {
			result = new StatsGenerator().process(args[0]);
		}

		TradeTypeStatsWrapper composite = new TradeTypeStatsWrapper();
		for (Future<TradeTypeStatsWrapper> tradeTypeStatsWrapper : result) {
			TradeTypeStatsWrapper individualResult = tradeTypeStatsWrapper
					.get();
			composite.process(individualResult);
		}

		System.out.println("done" + composite);
		
		TradeType[] types = TradeType.values();
		for(TradeType type : types){
			
			System.out.println(type + ":"+type.ordinal()+ "name"+		type.name() +":"+ type.getName());
		}

	}

	private List<Future<TradeTypeStatsWrapper>> process(String fileName)
			throws IOException, InterruptedException {
		// pseudo code
		/*
		 * we will divide file reading on batch size 1. finding no of rows in
		 * file 2. based on batch size we will fire multiple threads reading
		 * from given line till end line 3. Every thread will read and update
		 * the stats that will be combined at the end when all have done their
		 * processing
		 */

		File file = new File(fileName);
		int totalNoOfLines = getNoOfLines(file);
		int batches = noOfBatch(totalNoOfLines, batchSize);

		CountDownLatch counter = new CountDownLatch(batches);

		List<StatsGeneratorTask> tasks = new ArrayList<>();
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String header = reader.readLine();
        
		for (int i = 1; i <= batches; i++) {
			// first batch start from 2
			int startLine = i == 1 ? 2 : (i - 1) * batchSize + 1;
			int endLine = i * batchSize >= totalNoOfLines ? totalNoOfLines : i
					* batchSize;
			tasks.add(new StatsGeneratorTask(startLine, endLine, file, counter,
					header));
		}

		// 10 2- 5, 6 10

		ExecutorService service = Executors.newFixedThreadPool(4);
		List<Future<TradeTypeStatsWrapper>> futures = new ArrayList<>();

		for (StatsGeneratorTask task : tasks) {
			futures.add(service.submit(task));
		}

		counter.await();
		service.shutdown();

		return futures;
	}

	private static int noOfBatch(int totalSize, int batchSize) {
		return ((totalSize + batchSize - 1) / batchSize);
	}

	private static int getNoOfLines(File f) throws IOException {
		BufferedReader breader = null;
		int i = 0;
		try {
			breader = new BufferedReader(new FileReader(f));
			i = 0;
			while (breader.readLine() != null) {
				i++;
			}
			breader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (breader != null) {
				breader.close();
			}
		}
		return i;
	}

	private static class StatsGeneratorTask implements Callable {
		private int tradeTypeIndex, statusIndex;
		private int startLine, endLine;
		private File file;
		private TradeTypeStatsWrapper statsWrapper = new TradeTypeStatsWrapper();
		private CountDownLatch counter;
		
		public StatsGeneratorTask(int startLine, int endLine, File file,
				CountDownLatch counter, String header) {
			super();
			this.startLine = startLine;
			this.endLine = endLine;
			this.file = file;
			this.counter = counter;
			init(header);
		}

		private void init(String header) {
			String[] headerString = header.split(COMMA);
			int i = 0;
			for (String each : headerString) {
				if (Header.TradeSubType.name().equalsIgnoreCase(each)) {
					tradeTypeIndex = i;
				} else if (Header.Status.name().equalsIgnoreCase(each)) {
					statusIndex = i;
				}
				i++;
			}
		}

		@Override
		public Object call() throws IOException {
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new FileReader(file));
				int i = 1;
				//
				while (i < startLine) {
					reader.readLine();
					i++;
				}

				while (i <= endLine) {
					String data = reader.readLine();
					i++;

					if (data == null) {
						System.out.println("got null" + this);
					}

					String[] dataArray = data.split(COMMA);
					TradeType tradeTypeTemp = TradeType.findType(dataArray[tradeTypeIndex]);
					if(tradeTypeTemp == null){
						System.out.println("null for" + dataArray[tradeTypeIndex]);
						return null;
					}
					
					Status status = Status.valueOf(dataArray[statusIndex]);
					statsWrapper.process(tradeTypeTemp, status);
				}

				return statsWrapper;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				counter.countDown();
				reader.close();

			}
			return statsWrapper;
		}

		@Override
		public String toString() {
			return "StatsGeneratorTask [startLine=" + startLine + ", endLine="
					+ endLine + ", file=" + file + ", statsWrapper="
					+ statsWrapper + "]";
		}

	}

	public static enum Header {
		TradeSubType, Status
	}

	public static enum TradeType {
		AsiaMM("AsiaMM"), IRSwap("IRSwap"), MCGen2("MC Gen2"), MCGen2MF(
				"MC Gen2 MF"), MCGenericMF("MC Generic MF"), EquitySwap(
				"EquitySwap"), GenericPDE("GenericPDE"),NONE("NONE");

		private final String name;

		private TradeType(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return super.toString();
		}

		public String getName() {
			return name;
		}
		
	   public static TradeType findType(String enumString){
		   for(TradeType tradeType :TradeType.values()){
			   if(tradeType.getName().equalsIgnoreCase(enumString))
				   return tradeType;
		   }
		   return null;
	   }

	}

	private static enum Status {
		FAILED, UTR_NOT_SUPPORTED, SUPPORTED;
	}

	private static class UnmodifiableTradeTypeStats extends TradeTypeStats {

		TradeTypeStats stats;

		public UnmodifiableTradeTypeStats(TradeTypeStats stats) {
			super();
			this.stats = stats;
		}

		@Override
		public AtomicInteger getTotal() {
			return stats.getTotal();
		}

		@Override
		public AtomicInteger getFailed() {
			return stats.getFailed();
		}

		@Override
		public AtomicInteger getSupported() {
			return stats.getSupported();
		}

		@Override
		public AtomicInteger getUtr_not_supported() {
			return stats.getUtr_not_supported();
		}

		@Override
		void process(Status status) {
			new UnsupportedOperationException("Not supported");
		}

	}

	private static class TradeTypeStatsWrapper {
		private Map<TradeType, TradeTypeStats> map = new ConcurrentHashMap();
		{
			map.put(TradeType.AsiaMM, new TradeTypeStats());
			map.put(TradeType.EquitySwap, new TradeTypeStats());
			map.put(TradeType.GenericPDE, new TradeTypeStats());
			map.put(TradeType.IRSwap, new TradeTypeStats());
			map.put(TradeType.MCGen2, new TradeTypeStats());
			map.put(TradeType.MCGen2MF, new TradeTypeStats());
			map.put(TradeType.MCGenericMF, new TradeTypeStats());
			map.put(TradeType.NONE, new TradeTypeStats());
		}

		public void process(TradeType tradeType, Status status) {
			map.get(tradeType).process(status);
		}

		public TradeTypeStats get(TradeType tradeType) {
			return new UnmodifiableTradeTypeStats(map.get(tradeType));
		}

		public Map<TradeType, TradeTypeStats> get() {
			return Collections.unmodifiableMap(map);
		}

		public void process(TradeTypeStatsWrapper otherStatsWrapper) {
			Iterator<Entry<TradeType, TradeTypeStats>> entries = otherStatsWrapper
					.get().entrySet().iterator();

			while (entries.hasNext()) {
				Entry<TradeType, TradeTypeStats> entry = entries.next();
				TradeTypeStats tradeStats = map.get(entry.getKey());
				tradeStats.process(entry.getValue());
			}

		}

		@Override
		public String toString() {
			return "TradeTypeStatsWrapper [map=" + map + "]";
		}

	}

	private static class TradeTypeStats {
		AtomicInteger total = new AtomicInteger();
		AtomicInteger failed = new AtomicInteger();
		AtomicInteger supported = new AtomicInteger();
		AtomicInteger utr_not_supported = new AtomicInteger();

		public TradeTypeStats() {
			super();
		}

		public AtomicInteger getTotal() {
			return total;
		}

		public AtomicInteger getFailed() {
			return failed;
		}

		public AtomicInteger getSupported() {
			return supported;
		}

		public AtomicInteger getUtr_not_supported() {
			return utr_not_supported;
		}

		void process(TradeTypeStats stats) {
			total.getAndAdd(stats.getTotal().intValue());
			failed.getAndAdd(stats.getFailed().intValue());
			supported.getAndAdd(stats.getSupported().intValue());
			utr_not_supported
					.getAndAdd(stats.getUtr_not_supported().intValue());
		}

		void process(Status status) {
			if(status == null ){
				System.out.println("status is null" + status);
			}
			switch (status) {
			case FAILED:
				total.incrementAndGet();
				failed.incrementAndGet();
				break;

			case UTR_NOT_SUPPORTED:
				total.incrementAndGet();
				utr_not_supported.incrementAndGet();
				break;

			case SUPPORTED:
				total.incrementAndGet();
				supported.incrementAndGet();
				break;

			default:
				break;
			}
		}

		@Override
		public String toString() {
			return "TradeTypeStats [total=" + total + ", failed=" + failed
					+ ", supported=" + supported + ", utr_not_supported="
					+ utr_not_supported + ", failed%="+ (failed.floatValue()/total.floatValue()) * 100 +"]";
		}

	}


}

