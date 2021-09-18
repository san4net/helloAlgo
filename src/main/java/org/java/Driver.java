package org.java;

public class Driver {
	  enum Count{
		ONE,TWO,THREE;
	}
   /* public static final String ONE = "ONE";
    public static final String TWO = "TWO";
    public static final String THREE = "THREE";*/
    
	public Driver() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		MyObject syn = new Driver.MyObject(Count.ONE);
		Thread  thread1 = new Thread(new ThreadImpl(Count.ONE, syn));
		Thread thread2 = new Thread(new ThreadImpl(Count.TWO, syn));
		Thread thread3 = new Thread(new ThreadImpl(Count.THREE, syn));
		thread1.start();
		thread2.start();
		thread3.start();
		
	}
	
	public static class MyObject {
		Count id;
		public MyObject(Count id) {
			this.id = id;
		}
		public Count getId() {
			return id;
		}
		public void setId(Count id) {
			this.id = id;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			return result;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (id.getClass()!= obj.getClass())
				return false;
			Count other = (Count) obj;
			if (id != other)
				return false;
			return true;
		}
		
		
	}
}
