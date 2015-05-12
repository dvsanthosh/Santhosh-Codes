/**
 * This class is starting class. created to call other classes and methods created for practice.
 */
package practice;

import practice.inheri.Person;
import practice.inheri.Students;
import practice.strings.IsPermutation;
import practice.strings.SentenceReverse;
import practice.strings.StringReverse;

/**
 * @author D.V.Santhosh
 *
 */
public class Starting{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Comment Which ever you dont want
//		new StringReverse("Hello World!");
//		new IsPermutation("god","doG");
//		new SentenceReverse(" This is the string to be reversed");
		
		Students p = new Students();
		p.display();

	}

}
