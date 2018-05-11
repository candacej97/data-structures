package project2;

import java.io.*;
import java.util.*;

/**
 * Creates and stores the dictionary in an ArrayList
 *
 * @author Candace Johnson
 * @version 2/20/17
 */
public class Dictionary {
	protected ArrayList<String> dictionary;

	/**
	 * Takes a file and stores the ArrayList with the file contents.
	 *
	 * @param File f
	 * @throws IllegalArgumentException
	 */
	public Dictionary(File f) throws IllegalArgumentException{
		if(!f.exists() || !f.canRead()){
			throw new IllegalArgumentException();
		}
		else{
			try{setDictionary(f);}
			catch (FileNotFoundException e) {throw new IllegalArgumentException();}
			catch (FileIsEmptyException e) {throw new IllegalArgumentException();}
		}
	}

	/**
	 * Tests if the file is empty. If not, it will set the dictionary to be a list of words in the file
	 * by newline.
	 *
	 * @param f
	 * @throws IllegalArgumentException
	 * @throws FileNotFoundException
	 * @throws FileIsEmptyException
	 */
	private void setDictionary(File f) throws IllegalArgumentException, FileNotFoundException, FileIsEmptyException{
		Scanner scanner = new Scanner(f);
		String stringOfFile = "";

		if(scanner.hasNext()){
			scanner.useDelimiter("\t");
			stringOfFile = scanner.next();
			//split string to the arraylist
			dictionary = new ArrayList<String>(Arrays.asList(stringOfFile.split("\\s+")));
			scanner.close();
		}
		else{
			scanner.close();
			throw new FileIsEmptyException();
		}
	}
	
	/**
	 * Searches the dictionary for the parameter entered using my binary search method
	 *
	 * @param s
	 * @return boolean value
	 */
	public boolean isWord(String word){
		int start = 0;
		int end = dictionary.size()-1;
		
		return isWordBinary(word, start, end);		
	}

	/**
	 * This is the recursive method of searching through the dictionary in a binary way
	 * for words
	 * 
	 * @param word
	 * @param start
	 * @param end
	 * @return boolean value
	 */
	private boolean isWordBinary(String word, int start, int end){
		int mid = (start+end)/2;
		
		if(dictionary.get(start).compareTo(word) == 0){
			return true;
		}
		else if(dictionary.get(mid).compareTo(word) == 0){
			return true;
		}
		else if(dictionary.get(end).compareTo(word) == 0){
			return true;
		}
		else if(start == mid && mid == end && dictionary.get(end) != word){
			return false;
		}
		else{
			/*Check if start, mid, or end is closest to prefix, from there, make recursive
			function with start/end being the one chopping off some of the list elements */

			//w.compare(word), when w comes before word, will return -1
			//if start.compare(prefix) and mid.compare(prefix) == -1, next start = mid+1, end = end
			if(dictionary.get(start).compareTo(word) <= -1 && dictionary.get(mid).compareTo(word) <= -1){
				//change the start, end stays the same
				//recursive function
				return isWordBinary(word, mid + 1, end);
			}
			//if start.compare(prefix) == -1 and mid.compare(prefix) == 1, next start = start, end = mid-1
			else if(dictionary.get(start).compareTo(word) <= -1 && dictionary.get(mid).compareTo(word) >= 1){
				//change the end, start stays the same
				//recursive function
				return isWordBinary(word, start, mid - 1);
			}
			//else return false
			else{
				return false;
			}	
		}
	}
		
	/**
	 * Searches the dictionary for the parameter entered using the binary search method for prefixes
	 *
	 * @param s
	 * @return boolean value
	 */
	public boolean isPrefix(String prefix){
		int start = 0;
		int end = dictionary.size()-1;
		
		return isPrefixBinary(prefix, start, end);		
	}

	/**
	 * This is the recursive method of searching through the dictionary in a binary way
	 * for prefixes
	 * 
	 * @param prefix
	 * @param start
	 * @param end
	 * @return boolean value
	 */
	private boolean isPrefixBinary(String prefix, int start, int end){
		int mid = (start+end)/2;
		
		if(dictionary.get(start).startsWith(prefix)){
			return true;
		}
		else if(dictionary.get(mid).startsWith(prefix)){
			return true;
		}
		else if(dictionary.get(end).startsWith(prefix)){
			return true;
		}
		else if(start == mid && mid == end && !dictionary.get(end).startsWith(prefix)){
			return false;
		}
		else{
			/*Check if start, mid, or end is closest to prefix, from there, make recursive
			function with start/end being the one chopping off some of the list elements */

			//w.compare(word), when w comes before word, will return -1
			//if start.compare(prefix) and mid.compare(prefix) == -1, next start = mid+1, end = end
			if(dictionary.get(start).compareTo(prefix) < 0 && dictionary.get(mid).compareTo(prefix) < 0){
				//change the start, end stays the same
				//recursive function
				return isPrefixBinary(prefix, mid + 1, end);
			}
			//if start.compare(prefix) == -1 and mid.compare(prefix) == 1, next start = start, end = mid-1
			if(dictionary.get(start).compareTo(prefix) < 0 && dictionary.get(mid).compareTo(prefix) > 0){
				//change the end, start stays the same
				//recursive function
				return isPrefixBinary(prefix, start, mid - 1);
			}
			//else return false
			else{
				return false;
			}	
		}
	}

}
