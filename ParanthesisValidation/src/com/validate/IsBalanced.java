/**
 * The following code checks for the parenthesis mentioned in a string are balanced
 * And Every parenthesis opened must be closed in the same order.	
 */
package com.validate;

import java.util.Scanner;
import java.util.Stack;

/**
 * This class implements isBalanced menthod to 
 * @author D.V.Santhosh
 * 
 *
 */
public class IsBalanced {

	/**
	 * Read the string and pass it to isBalanced method.
	 * @param args Nothing shall be passed.
	 */
	public static void main(String[] args) {
		
		Scanner in = new Scanner (System.in);
		System.out.println("String Please: ");
		String str = in.next();
		in.close();

		
		if (isBalanced(str))
			System.out.println(" Balanced");
		else
			System.out.println(" Not Balanced! Pls check the Parenthesis sequence");

	}
	
	
	/**
	 * This method checks for balanced parenthesis in the passed string and returns true or false.
	 * 
	 * @param str Provide the string in which parenthesis sequence has to be verified.
	 * @return Boolean returns true if sequence is balanced.
	 * 
	 */
	public static boolean isBalanced(String str){
		int flag = 0;
		Stack<Character> st = new Stack<Character>();
	
		for (int j = 0; j < str.length(); j++) {
		
			Character current = str.charAt(j);
			
			if (current =='{' || current =='['|| current == '('){
				System.out.println("Pushing" + current);
				st.push(current);
			}
			
			else if( current == '}' || current == ']' || current ==')'){
				System.out.println("Trying to pop" + current);
				if(st.isEmpty()) {flag=1;break;}

				Character to_match = st.pop();

				if(to_match=='{'&&current=='}') {}

				else if(to_match=='('&&current==')') {}

				else if(to_match=='['&&current==']') {}

				else {flag=1; break;}
			}
		}
		if (flag ==1 || !(st.isEmpty()))
			return false;
		
		//All checks clear, Send OK.
		return true;

	}
}
