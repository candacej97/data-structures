package project2;
import java.io.File;

/**
 * This class is responsible for parsing the command line arguments,
 * creating the Dictionary and Permutations objects and then using them to display the results.
 * This class may have methods other than the main() method.
 * 
 * @author Candace Johnson
 * @version 2/28/2017
 */
public class ScrabbleHelper {

	static Dictionary dictionary;
	static Permutations permutations;
	
	/**
	 * Checks for and handles exceptions depending on why the exception is being thrown.
	 * Takes array, parses it, puts the contents into two variables
	 * Puts those variables into the constructors of the Dictionary and Permutations objects
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
					
		try{if(args.length == 0) throw new IllegalArgumentException();}
		//use system.exit(0) to make the program exit "successfully"
		catch (IllegalArgumentException e) {System.err.println("ERROR: No file entered"); System.exit(0);}
			
		try{if(args.length < 2) throw new IllegalArgumentException();}
		catch (IllegalArgumentException e) {System.err.print("ERROR: Not enough arguments were supplied."); System.exit(0);}
						
		//set the command-line arguments as values
		File file = new File(args[0]);
		String letters = args[1].toLowerCase();
		
				
		//input those values into their respective class constructors 
		try{dictionary = new Dictionary(file);}
		catch(IllegalArgumentException e){System.err.println("ERROR: file " + file + " does not exists."); System.exit(0);}
		
		try{if(letters.length() == 0) throw new IllegalArgumentException();}
		catch (IllegalArgumentException e) {System.err.print("ERROR: No letters provided, cannot compute words."); System.exit(0);}
		
		try{permutations = new Permutations(letters);}
		catch(IllegalArgumentException e){System.err.println("ERROR: Invalid character(s) entered."); System.exit(0);}
				
		//Call getAllWords so that Permutations class can compute which permutations are words, efficiently
		permutations.getAllWords(dictionary);
				
		//print out the string of the permutations class
		System.out.println(permutations.toString());

	}
}
