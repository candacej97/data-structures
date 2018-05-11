package project2;
import java.util.*;

import project2.Dictionary;

/**
 * Creates all different permutations of letters entered and checks if they are words dependent on a dictionary object.
 * Efficiently searches through a dictionary object by using a binary search.
 * 
 * @author Candace Johnson
 * @version 2/24/2017
 */
public class Permutations {
	
	protected String stringOfLetters = "";
	protected ArrayList<String> allPermutations = new ArrayList<String>();
	protected ArrayList<String> allWords = new ArrayList<String>();
	protected ArrayList<String> prefixesNotFound = new ArrayList<String>();
	
	/**
	 * Checks if param entered are in fact ONLY letters
	 * If not, exception is thrown
	 * If so, characters are added to a String
	 * 
	 * @param letters
	 * @throws IllegalArgumentException
	 */
	public Permutations(String letters) throws IllegalArgumentException {
		//Create array to iterate over characters easier
		char[] enteredChars = letters.toCharArray();

	    for (char c : enteredChars) {
	        if(!Character.isLetter(c)) {
	            throw new IllegalArgumentException();
	        }
	        this.stringOfLetters += c;
	    }
	    
	    getAllPermutations();
	}
	
	/**
	 * Calls and returns a helper method to fill an ArrayList of Stings of all permutations of the letters entered
	 * 
	 * @return permutationHelper("", stringOfLetters)
	 */
	public ArrayList<String> getAllPermutations() { 
	    return permutationHelper("", stringOfLetters); 
	  }

	/**
	 * Creates permutations by keeping track of letters being added to a "prefix" and keeping track of the length of the letters so that
	 * it can be added to the ArrayList of allPermutations
	 * 
	 * @param prefix
	 * @param letters
	 * @return allPermutations
	 */
	private ArrayList<String> permutationHelper(String prefix, String letters) {
		  
	    int permLength = letters.length();
	    if (permLength == 0 && !allPermutations.contains(prefix)){allPermutations.add(prefix);}
	    else {
	        for (int i = 0; i < permLength; i++)
	            permutationHelper(prefix + letters.charAt(i), letters.substring(0, i) + letters.substring(i+1, permLength));
	    }
	    
	    return allPermutations;
	  }
	
	/**
	 * Calls and returns a helper method to fill an ArrayList of Stings of all words made of the letters of entered
	 * 
	 * @param dictionary
	 * @return getWordsHelper(dictionary, "", stringOfLetters, stringOfLetters.length())
	 */
	public ArrayList<String> getAllWords(Dictionary dictionary){
		getWordsHelper(dictionary, "", stringOfLetters, stringOfLetters.length());
		return allWords;
	}
	
	/**
	 * Implementing a binary search through the dictionary recursively to search for words.
	 * 
	 * @param d
	 * @param prefix
	 * @param letters
	 * @param length
	 * @return allWords
	 */
	private ArrayList<String> getWordsHelper(Dictionary d, String prefix, String letters, int length){
		//Checks if the prefix is not even in the dictionary
		if (prefix.length() > 0 && !d.isPrefix(prefix) && !prefixesNotFound.contains(prefix)) prefixesNotFound.add(prefix);
		else if (prefixesNotFound.contains(prefix)) ;
		
		//Checks if the prefix created isPrefix and then isWord as long as prefix is not in wordArray
		else if (length == 0 && !allWords.contains(prefix)) {
			if (d.isWord(prefix)) {
				allWords.add(prefix);
			}
		}
		
		else {
			for (int i = 0; i < length; i++) {
				//recursive
	            getWordsHelper(d, prefix + letters.charAt(i), letters.substring(0, i) + letters.substring(i+1, length), length-1);
			}
		}
	    
	    return allWords;
	  }
	
	/**
	 * Creates a formatted string for printing out to the console/terminal properly.
	 * 
	 * @return output
	 */
	@Override
	public String toString(){
		String output = "";
		int numOfWords = allWords.size();
		
		//sort allWords so that it always prints out alpha order
		Collections.sort(allWords);
		
		//differentiate grammar used when creating the string
		if(numOfWords > 1) output = String.format("Found %d words:", numOfWords);
		else if(numOfWords == 1) output = String.format("Found %d word:", numOfWords);
		else output = "Sorry, no words found.";	
		
		for (String word: allWords){
			output += "\n   " + word;
		}
		
		return output;
	}
}
