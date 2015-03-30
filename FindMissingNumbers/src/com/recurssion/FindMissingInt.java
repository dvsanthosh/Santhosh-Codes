/**
 * You are given a sorted list of distinct integers from 0 to 99, for instance [0, 1, 2, 50, 52, 75].
Your task is to produce a string that describes numbers missing from the list; in this case "3-49,51,53-74,76-99". The items should be sorted in ascending order and separated by commas. When a gap spans only one number, the item is the number itself; when a gap is longer, the item comprises the start and the end of the gap, joined with a minus sign.

 */
package com.recurssion;

import java.util.ArrayList;
import java.util.List;

/**
 * @author D.V.Santhosh
 *
 */
public class FindMissingInt {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[] givenArray = new int [] {0, 1, 2, 50, 52, 75};
		String missingNum = getRanges(findMissingNum(givenArray));
		System.out.println("Missing ranges of numbers in the given array is " +missingNum);
		
	}

public static List<Integer> findMissingNum(int [] givenArray)
{
	List<Integer> missingNums = new ArrayList<Integer>();
	for (int i=0; i<100;i++)
	{
		if(!inGivenArray(i,givenArray))
			missingNums.add(i);
	}
	return missingNums;
	//System.out.println("The missing numbers are:\n" + check1);
}

public static String getRanges(List<Integer> missingNums){
	ArrayList<String> result = new ArrayList<String>();
	
	for (int j = 0; j < missingNums.size(); j++) {
		int lower = missingNums.get(j);
		int upper = lower;
		try {
			while(missingNums.get(j+1)-missingNums.get(j) == 1){
				upper = missingNums.get(j+1);
				j++;
			}
		} catch (Exception e) {
			upper = missingNums.get(j);	// end of array list
		}
	
		if(upper != lower)
			result.add(Integer.toString(lower).concat("-").concat(Integer.toString(upper)));
		else
			result.add(Integer.toString(lower));
		
	}
	return result.toString();

}

public static boolean inGivenArray(int num, int [] givenArray)
{
	int i=0;
	while(i<givenArray.length) {
		if(givenArray[i] == num){
			return true;
		}
		i++;
	}
	return false;
}

}
