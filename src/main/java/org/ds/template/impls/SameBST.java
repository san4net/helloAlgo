package org.ds.template.impls;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SameBST<T> {

	/**<p>
	 * Getting all the children of the BST, like the 0 index will be 
	 * have all children of 0 index element like wise for each element in the array.
	 * 
	 * </p>
	 * @param inputArray
	 * @return
	 */
	
	public List<List<Integer>> getChildrenOfArray(Integer[] inputArray ){
     List<List<Integer>> childList = new ArrayList<List<Integer>>();
     // we traverse the array  and find all possible children of each index
     
     for(int i =0; i<inputArray.length;i++){
    	 List<Integer> child = getChildrensOfAnIndex(inputArray,i);
    	 childList.add(child);
     }
		return childList;
	}
	
	/**
	 * Finding the children of specific index.  For this we need to traverse from 
	 * index+1 to the end of the inputArray. And checkin at each index whether the given element
	 * can be child or not.
	 * 
	 * @param inputArray
	 * @param index
	 * @return
	 */
	private List<Integer> getChildrensOfAnIndex(Integer[] inputArray, int index) {
		List<Integer> child = new ArrayList<Integer>();
		int possibleFather = inputArray[index];
		
		for(int startIndex = index+1; startIndex<inputArray.length; startIndex++){
			int possibleChild = inputArray[startIndex];
			
		 if(isChild(inputArray, possibleFather, possibleChild, index)){
			 child.add(inputArray[startIndex]);
		 }
		}
		return child;
	}
	
  /**
   * An element will be child if it is on the same side(i.e either greater/less ) while traversing from the root i.e is index 0.
   * 
   * @param originalArray
   * @param possibleFather
   * @param possibleChild
   * @return
   */
	
    private boolean isChild(Integer[] originalArray, int possibleFather, int possibleChild, int fatherIndex) {
		
		for(int startIndex=0;startIndex<fatherIndex;startIndex++){
			
			if(originalArray[startIndex]>possibleChild && originalArray[startIndex]>possibleFather){
				// both child and its father should be either less then ie. on left side. 
			}else if(originalArray[startIndex]<possibleChild && originalArray[startIndex]<possibleFather){
				// both child and its father should be either greater then ie. on right side. 
			}else{
				return false;
			}
		}
		return true;
	}

	 
	public static void main(String[] args) {

	   Integer a[] = {8, 3, 6, 1, 4, 7, 10, 14, 13};
	   Integer b[] = {8, 10, 14, 3, 6, 4, 1, 7, 13};
	   
	  SameBST<Integer> instance = new SameBST<Integer>();
	  List<List<Integer>> firstChildList =  instance.getChildrenOfArray(a);
	  List<List<Integer>> secondChildList =  instance.getChildrenOfArray(b);
	  
	  System.out.println("first child list: " +firstChildList+"\n second child list"+ secondChildList);
	  
	  // Now compare children of one array element
	  for(int i =0 ;i <a.length;i++){
		  List<Integer> childrenInArray1 = firstChildList.get(i);
		  
		  List<Integer> childrenInArray2 = secondChildList.get(indexOf(a[i], b));
		  
		  if(firstChildList.size() == secondChildList.size()){
			  Iterator<Integer> iter = childrenInArray1.iterator();
			  
			  while(iter.hasNext()){
				  if(!childrenInArray2.contains(iter.next())){
					  System.out.println("not same bst");	  
					  return ;
				  }
			  }
			  
		  }else{
			  System.out.println("not same bst");
			return;
		  }
		  
	  }
	  
	  System.out.println(" same bst");
	}

	private static int indexOf(Integer element, Integer[] inputArray) {
		for(int i =0 ;i<inputArray.length;i++){
			if(inputArray[i].equals(element)){
				return i;
			}
		}
		return 0;
	}

}
