/*
 * Algo to determine if a string has all unique chars.
 */

package character;

import java.util.Scanner;

public class RepeatingCharacter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(" Enter String");
		Scanner in = new Scanner(System.in);
		String input = in.next();
		in.close();
//		char [] cha = new char[256];
		boolean [] cha = new boolean[256];
		
		for (int i = 0; i < input.length(); i++) {
//			This will increase time complexity but will print all repeating chars			
//			for(int j=0;j<cha.length;j++)
//				if(input.charAt(i) == cha[j]){
//					System.out.println("It has repeating char: "+cha[j]);
//					break;
//				}
			
			// This block breaks at the first repeating char
			int ch = input.charAt(i);
			if(cha[ch]){
				System.out.println("It has repeating char:" + input.charAt(i));
				break;
			}
			cha[ch] = true;
		}

	}

}
