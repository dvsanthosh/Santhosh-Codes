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
		// TODO Auto-generated constructor stub
		boolean result;
		result = string.length()==string2.length() ? true : false;
	
		result = sort(string).equals(sort(string2));
		
		System.out.println(string + " is permutation of " + string2 + ": " + result);
	}
	
	private String sort(String s){
		char [] content = s.toCharArray();
		java.util.Arrays.sort(content);
		return new String(content);
	}

}
