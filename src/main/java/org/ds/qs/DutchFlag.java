package org.ds.qs;

public class DutchFlag {

	private void swap(int[] array, int index1, int index2){
		int temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;

	}

	void sort(int[] array){
		int low=0;
		int mid=0;
		int high = array.length-1;
		
		while(mid<high){
			if(array[mid]==0){
				swap(array, low, mid);
				low++;
				mid++;
				
			}else if(array[mid]==1){
				mid++;
			}else{
				swap(array, mid, high);
				high--;
			}
		}
	}
	
	void display(int[] array){
		for(int a:array){
			System.out.print(a+",");
		}
		
	}
	
	public static void main(String[] args) {
		int array[] = {2,2,2,1,1,1,0,0};
		DutchFlag df = new DutchFlag();
		
		df.display(array);
		df.sort(array);
		df.display(array);

		int a =0;
				int c =3;
		System.out.println("starting"+c);
		c = a==0?++c:0;
		System.out.println(c);

	}
}
