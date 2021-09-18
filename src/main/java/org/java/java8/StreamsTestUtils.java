package org.java.java8;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamsTestUtils {

	public static void pythoTheoram(){
		Stream<double[]>  doubleStream =
				IntStream.rangeClosed(1, 100)
		.boxed()
		.flatMap(a->IntStream.rangeClosed(a, 100)
				.mapToObj(b-> new double[]{a,b,Math.sqrt(a*a+b*b)})
				.filter(t -> t[2]%1==0));
		
		doubleStream.forEach(d -> System.out.println(d[0]+":"+d[1]+":"+d[2]));
	}
	private static Map<String, Long> freqMap = new HashMap<>();

	public static void frequencyMap(){

		Stream<String> words = Stream.of("map","cap","CAP","tap","wap","WAP");
		 //style 1
		words.forEach(
				(String word)->
		{freqMap.merge(word.toLowerCase(),1l, Long::sum);});
				System.out.println(freqMap);

		freqMap = words.collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting()));
		
		System.out.println(freqMap);
		
	}

	/**
	 * Given an array of integer
	 * <pre>
	 * You are given the logs for users' actions on LeetCode, and an integer k.
	 * The logs are represented by a 2D integer array logs where each logs[i] = [IDi, timei]
	 * indicates that the user with IDi performed an action at the minute time[i].
	 *
	 * You are to calculate a 1-indexed array answer of size k such that, for each j (1 <= j <= k),
	 * answer[j] is the number of users whose UAM equals j
	 *</pre>
	 * Algos
	 * <li>Make a map of ids <-> set of active minutes
	 * <li>Reprocess previous map to make user active minute
	 * i.e uam <-> number of users (sum all users have same uam)
	 * <li>finally iterate from 0 to k-1 and to make out of int array
	 * where each i is equal to number of user whose uam is i
	 * where i is 1 indexed
	 *
	 * {@link "http://leetcode.com/problems/finding-the-users-active-minutes"}
	 *
	 * @param logs  is ids and its active minutes
	 * @param k
	 */
	public static void makeMap(int[][] logs, int k){
		int [] r =
				Arrays.stream(logs)
				.collect(()->new HashMap<Integer, Set<Integer>>(),
						(HashMap<Integer,Set<Integer>> map, int[] e)->{
							map.putIfAbsent(e[0], new HashSet<>());
							map.get(e[0]).add(e[1]);
							}, HashMap::putAll
						)
						.entrySet()
				.stream()
				.collect(
				 Collectors.collectingAndThen(
						Collectors.toMap(
						e1->e1.getValue().size(),
						v1->1,
								Integer::sum

						),
						m->{
							return IntStream.range(0,k)
								.map(i->{
									 Integer noOfIds =  m.get(i+1);
									 if(noOfIds==null)
									 	return 0;
									  else
									  	return noOfIds;
										})
									.toArray();
						}
				)
				)
				;
		Arrays.stream(r).forEach(System.out::print);
	}
	
	 static Map<Integer, Boolean> map = new HashMap<>();
	 
	 public static boolean canJump(int[] nums) {
		 map.put(0, true);
		 for (int i = 1; i < nums.length; i++) {
			 map.put(i, false);
		 }
		 
		 return canMove(nums.length-1, nums.length-1, nums, map);
	    }
	 
	 private static boolean canMove(int cur, int next, int[] nums,Map<Integer, Boolean> map) {
		 if(cur==0) {
			 return move(cur, next, nums, map);
		 }else {
			 for(int i=1; cur-i>=0;i++) {
				 
				 if(canMove(cur-i, cur, nums, map)&& (move(cur, next, nums,map))) {
					return true;
				 }
			 }
			 return false;
		 }
	 }
	 
	 private static boolean move( int i, int j ,int[] nums, Map<Integer, Boolean> m) {
		 if(m.get(j)==false) {
			 if(i+nums[i]>=j) {
				 m.put(j, true);
				 return true;
			 }
			 return false;
		 }
		 return true;
	}
	 
	 public static int numberOfArithmeticSlices(int[] A) {
		   if(A.length<3) return 0;
		   int total =0;
	       for (int i = 0; i < A.length-2; ) {
	    	    int diff= A[i+1]-A[i];
	    	    int j=i+1;
	    	    
	    	    for (; j < A.length-1; j++) {
						 if(diff != (A[j+1]-A[j])) {
							 break;
						 }
					}
	    	    total = total + noOfSlice(i, j);
	    	    i=j;
	       }  
		 return total;
	    }

		private static int noOfSlice(int s, int e) {
			int len = e - s + 1;
			if (len < 3)
				return 0;
			else {
				int total = 0;
				int group = 3;
				while (group <= len) {
					total = total + (len - (group - 1));
					group++;
					
				}
				return total;
			}

		}
	 
	public static void topTen(){
		List<String>  topTen = freqMap.keySet().stream().sorted(Comparator.comparing(freqMap::get).reversed()).limit(10).collect(Collectors.toList());
	}

	public void topAlbum(){
		Stream<Album> albumList = null;
		Map<Artist, Album> topHits = albumList.collect(Collectors.toMap(Album::getArtist, a->a, BinaryOperator.maxBy(Comparator.comparing(Album::getSales))));
	}


	
	public static void main(String[] args) {

	 	int[][] a ={{1,4},{1,1},{1,2},{3,3}};
	 	makeMap(a,3);
//		pythoTheoram();
//		frequencyMap();
//		System.out.println(numberOfArithmeticSlices(new int[] {1,2,3,4}));
//		List<List<String>> a = new ArrayList<>();
//		a.add( 
//		Lists.newArrayList("Boateng", "6.1","22","24","45"));
//		a.add(Lists.newArrayList("Kaka", "6" ,"22", "1", "1"));
//		a.add(Lists.newArrayList("Ronaldo", "5.8" ,"21", "120", "0"));
//		a.add(Lists.newArrayList("Suarez", "5.9" ,"210", "100", "1"));
//		
////		int[][] image ={{1,1,1},{1,1,0},{1,0,1}};
//		int[][] image = {{0,0,0},{0,1,1}};
//		
//		floodFill(image, 1, 1, 1);
//		Arrays.stream(image).forEach((int[] b)->{
//			for(int t:b) {
//				System.out.print(t +" ");
//			}
//			System.out.println("");
//		});
		
//		findLeastNumOfUniqueInts(new int[]{4,3,1,1,3,3,2}, 3);
	}
	
	public static int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> fmap = freqMap(arr);
        System.out.println(fmap);

        TreeMap<Integer, Integer> tm = 
				new TreeMap<>(
						(Integer a, Integer b)->{
							System.out.println("comparing"+a+":"+b);
							if(Integer.compare(fmap.get(a), fmap.get(b))==0) {
								return Integer.compare(a,b);
							}
					return Integer.compare(fmap.get(a), fmap.get(b));
				});
				
        tm.putAll(fmap);
        
		System.out.println(tm);
		
        while (k > 0 && tm.size() > 0) {

			Iterator< Integer> it = tm.keySet().iterator();

			while (it.hasNext()) {
				if (k == 0) {
					break;
				}

				Integer  key = it.next();

				if (tm.get(key) == 1) {
					System.out.println("removing single"+ key);
					k--;
					it.remove();
				}else if(tm.get(key)<=k) {
					k=k-tm.get(key);
					it.remove();
					System.out.println("removing all of multiple "+ key);
				}
				else
				{
					k--;
					tm.put(key, tm.get(key)-1);
					System.out.println("removing multiple"+ key);
				}
			}
		}
		
        System.out.println("result" +tm.size());
		return tm.size();
        
        
    }
    /**
     * Here we are working on intstream
     * 
     * @param arr
     * @return
     */
    private static Map<Integer, Integer> freqMap(int[] arr){
    	Arrays.stream(arr).min().getAsInt();
    	
        Map<Integer, Integer> fmap = 
				Arrays.stream(arr).collect(() -> {
			return new HashMap();
		}, (HashMap<Integer, Integer> mm, int k) -> {
			mm.merge(k, 1, (Integer old, Integer n) -> {
				return old + n;
			});
		}, (HashMap<Integer, Integer> mm1, HashMap<Integer, Integer> mm2) -> {

		});
        return fmap;
    }
    
    private static <T> Map<T, Long> freqMap(T[] arr){
        return Arrays.stream(arr)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        
    }

//    private static <K,V> Map<K,V> freqencyMap(List<Map<K,V>> list){
//    	list.stream().flatMap(x->x.entrySet().stream())
//				.collect()
//    	return null;
//	}
	
	
  public static  int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
	 fillRecursive(image, sr, sc, newColor, image[sr][sc], new HashSet<>());
     return image;    
    }
  
  static class Coordinate{
	  int x; int y;

	public Coordinate(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	static  Coordinate instance(int x, int y) {
		return new Coordinate(x, y);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	  
  }

static int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};

private static void fillRecursive(int[][]image, int sr, int sc, int newColor,int prevColor, Set<Coordinate> visited) {
	if(visited.contains(Coordinate.instance(sr, sc))) {
		return;
	}
	if(sr<0 || sc<0) {
		return;
	}
	if(sr>=image.length ||  sc>=image[sr].length) {
		return;
	}
	System.out.println(sr+":"+sc);
	if(image[sr][sc]!= prevColor) {
		return ;
	}
	
	image[sr][sc]=newColor;
	visited.add(new Coordinate(sr, sc));
	
	for(int[] temp: directions) {
		  fillRecursive(image, sr+temp[0], sc+temp[1], newColor, prevColor, visited);
	}
}
	
		
	
	
	
	
	
	public boolean checkStraightLine(int[][] coordinates) {
        double m = (coordinates[0][1]-coordinates[1][1])/(coordinates[0][0]-coordinates[1][0]);
        
        m =  round(m,2);
        for(int i=1; i<coordinates.length;i++){
                
              for(int j=i+1; j<coordinates.length;j++){
                double temp =  round(  
                    (coordinates[i][1]-coordinates[j][1])/(coordinates[i][0]-coordinates[j][0]), 2);
               
                 if(Double.compare(temp, m)!=0){
                   return false;
                }   
              }
        }
        
        return true;
        
            
    }
    
    private double round( double val,int place){
        BigDecimal bd = BigDecimal.valueOf(val);
        bd = bd.setScale(place, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode() {
		}

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}
	
	public boolean isCousins1(TreeNode root, int A, int B) {
		if (root == null)
			return false;
		
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		
		while(!queue.isEmpty()) {
			int size=queue.size();
			boolean aPresent = false;
			boolean bPresent = false;
				for(int i=0;i<size;i++) {
						TreeNode cur = queue.poll();
						aPresent = (cur.val==A);
						bPresent = (cur.val==B);
						
						if(cur.left!=null && cur.right!=null) {
							if(cur.left.val==A && cur.right.val==B)
									return false;
							if(cur.left.val==B && cur.right.val==A)
									return false;
							
						}
						if(cur.left!=null) {
							queue.offer(cur.left);
						}

						if(cur.right!=null) {
							queue.offer(cur.right);
						}
				}
			
		 if(aPresent&&bPresent)return true;		
				
		}
		return false;
		
	}
	
	public boolean isCousins(TreeNode root, int A, int B) {
		if (root == null)
			return false;
		
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		
		while (!queue.isEmpty()) {
			int size = queue.size();
			boolean isAexist = false;
			boolean isBexist = false;
			
			for (int i = 0; i < size; i++) {
				TreeNode cur = queue.poll();
				if (cur.val == A)
					isAexist = true;
				if (cur.val == B)
					isBexist = true;
				if (cur.left != null && cur.right != null) {
					if (cur.left.val == A && cur.right.val == B) {
						return false;
					}
					if (cur.left.val == B && cur.right.val == A) {
						return false;
					}
				}
				if (cur.left != null) {
					queue.offer(cur.left);
				}
				if (cur.right != null) {
					queue.offer(cur.right);
				}
			}
			if (isAexist && isBexist)
				return true;
		}
		
		
		return false;
	}
	
	
	public static class Album{
		Artist artist;
		double sales;

		public Album(Artist artist, double sales) {
			this.artist = artist;
			this.sales = sales;
		}

		public Artist getArtist() {
			return artist;
		}

		public double getSales() {
			return sales;
		}


	}

	public static class Artist{

	}
	
	public static class Player{
		String name;
		double height;
		double bmi;
		int score;
		int defended;
		
		public Player(String name) {
			super();
			this.name = name;
		}
		public Player(String name, double height, double bmi, int score, int defended) {
			super();
			this.name = name;
			this.height = height;
			this.bmi = bmi;
			this.score = score;
			this.defended = defended;
		}

		public String getName() {
			return name;
		}

		public double getHeight() {
			return height;
		}

		public double getBmi() {
			return bmi;
		}

		public int getScore() {
			return score;
		}

		public int getDefended() {
			return defended;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Player other = (Player) obj;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}
		@Override
		public String toString() {
			return "Player [name=" + name + ", height=" + height + ", bmi=" + bmi + ", score=" + score + ", defended="
					+ defended + "]";
		}
		
		
	}
	
	
}
