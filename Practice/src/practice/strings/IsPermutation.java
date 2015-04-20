/**
 * this method checks whether a string is permutation of another or not.
 */
package practice.strings;

/**
 * @author D.V.Santhosh
 *
 */
public class IsPermutation {

	public IsPermutation(String string, String string2) {
		boolean result;
		
		result = string.length()==string2.length() ? true : false;
	
		//result = sort(string).equals(sort(string2));								// for case sensitive
		result = sort(string.toUpperCase()).equals(sort(string2.toUpperCase()));	// for case insensitive
		System.out.println(string + " is permutation of " + string2 + ": " + result);
	}
	
	private String sort(String s){
		char [] content = s.toCharArray();
		java.util.Arrays.sort(content);
		return new String(content);
	}

}
