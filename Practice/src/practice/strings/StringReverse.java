/**
 * Cracking the coding interview Q 1.2
 * Implement a fn void reverse(char* str) in C or C++ 
 */
package practice.strings;

/**
 * @author D.V.Santhosh
 *
 */
public class StringReverse {
	StringReverse(){
		System.out.println("Forgot to pass String");
	}
	
	public StringReverse(String str){
		StringBuffer strRes = new StringBuffer();
		int strlen= str.length();
		while(strlen>0)
			strRes.append(str.charAt(--strlen));
		System.out.println(strRes);
	}
}

