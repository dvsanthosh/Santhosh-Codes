/**
 * 
 */
package com.SortedArray;

/**
 * @author D.V.Santhosh
 *
 */
public class IndexOfArray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = {0,1,2,3,4,5};
		//key of val 3 must be 3
		int val= 3;
		int len=arr.length;
		int key = -1;
		for (int i=0;i<len;i++){
			if(arr[i]== val){
				key = i;
				break;
			}	
		}
		System.out.println(" Key: "+key);
	}
}
