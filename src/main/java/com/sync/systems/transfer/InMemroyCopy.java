package com.sync.systems.transfer;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.sync.systems.Utils.Status;


public class InMemroyCopy<T> implements Copy<T> {
	volatile boolean copied = false;
	T sourceFile, destFile;
	private long size = 0;

	public InMemroyCopy(T srcFile, T destFile2) {
		super();
		this.sourceFile = srcFile;
		this.destFile = destFile2;
	}

	@Override
	public T copy() throws Exception {
		Path src = Paths.get((String)sourceFile);
		File srcFile = src.toFile();
		if(srcFile.isDirectory()){
			File files[] = srcFile.listFiles();
			for(File f : files){
				if(f.isDirectory()){
					// skip c
					continue;
				}
				Path destPath = Paths.get((String)destFile,f.getName());
				copy(f.toPath(), destPath);
			}
		}
		return (T) Status.SUCCESS;
	}
	
	private	T copy(Path src, Path dest) throws Exception{
		long status = -1;
		RandomAccessFile srcFile = new RandomAccessFile(src.toFile(), "rw");
		RandomAccessFile destFile = new RandomAccessFile(dest.toFile(), "rw");
		size = srcFile.length();
		
		if (srcFile.getChannel() == null || destFile.getChannel() == null) {
			return (T) Long.valueOf(status);
		}

		try (FileChannel fcIn = srcFile.getChannel();
				FileChannel fcOut = destFile.getChannel();) {
			long timeIn = System.currentTimeMillis();
			ByteBuffer byteBuffIn = fcIn.map(FileChannel.MapMode.READ_WRITE, 0,
					(int) fcIn.size());
			fcIn.read(byteBuffIn);
			byteBuffIn.flip();
			ByteBuffer writeMap = fcOut.map(FileChannel.MapMode.READ_WRITE, 0,
					(int) fcIn.size());
			writeMap.put(byteBuffIn);
			long timeOut = System.currentTimeMillis();
			System.out.println("Memory mapped copy Time for a file of size :"
					+ (int) fcIn.size() + " in sec : " + (timeOut - timeIn)
					/ 1000);
			status = size ;
			copied = true;

		} catch (Exception e) {
			e.printStackTrace();
			status = 0;
		}
		
		return (T) String.valueOf(status);
	}

	@Override
	public boolean isCopied() {
		return copied;
	}
	
	public T getSourceFile() {
		return sourceFile;
	}
	
	public void setSourceFile(T sourceFile) {
		this.sourceFile = sourceFile;
	}
	
	public T getDestFile() {
		return destFile;
	}
	
	public void setDestFile(T destFile) {
		this.destFile = destFile;
	}
	/**
	 * This is size of the file
	 */
	@Override
	public long size() {
		// TODO Auto-generated method stub
		return size;
	}

	public static void main(String[] args) throws Exception {
		new InMemroyCopy<>("d:/abc","d:/abc/test").copy();
	}

}
