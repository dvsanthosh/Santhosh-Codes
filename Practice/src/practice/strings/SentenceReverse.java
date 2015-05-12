/**
 * 
 */
package practice.strings;

/**
 * @author D.V.Santhosh
 *
 */
public class SentenceReverse {

	public SentenceReverse(String string) {
		
		String [ ] temp = string.split(" ");
		for(int i= temp.length-1;i>=0;i--)
			System.out.print(temp[i]+ " ");
	}
}
