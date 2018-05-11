package project4_new;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class opens and reads .csv files, parses the lines within it, creates Tree objects 
 * and uses those objects to give back specific information depending on the user's input
 * of a species name. 
 * 
 * @author Candace Johnson
 * @version 4/19/2017
 */
public class NYCStreetTrees {

	static TreeCollection nycTrees = new TreeCollection();

	/**
	 * Splits the given line of a CSV file according to commas and double quotes
	 * (double quotes are used to surround  multi-word entries that may contain commas). 
	 * 
	 * @param textLine  line of text to be parsed
	 * @return ArrayList<String>   containing all individual entries/tokens found on the
	 *  line.
	 */
	public static ArrayList<String> splitCSVLine(String textLine) {
		ArrayList<String> entries = new ArrayList<String>();
		int lineLength = textLine.length();
		StringBuffer nextWord = new StringBuffer();
		char nextChar;
		boolean insideQuotes = false;
		boolean insideEntry= false;
		
		//iterate over all characters in the textLine
		for (int i = 0; i < lineLength; i++) {
			nextChar = textLine.charAt(i);
			
			//handle smart quotes as well as regular quotes 
			if (nextChar == '"' || nextChar == '\u201C' || nextChar =='\u201D') { 
				//change insideQuotes flag when nextChar is a quote
				if (insideQuotes) {
					insideQuotes = false;
					insideEntry = false; 
				}
				else {
					insideQuotes = true; 
					insideEntry = true; 
				}
			}
			else if (Character.isWhitespace(nextChar)) {
				if  ( insideQuotes || insideEntry ) {
					// add it to the current entry
					nextWord.append( nextChar );
				}
				else  { // skip all spaces between entries 
					continue;
				}
			}
			else if ( nextChar == ',') {
				if (insideQuotes) //comma inside an entry 
					nextWord.append(nextChar);
				else { //end of entry found 
					insideEntry = false; 
					entries.add(nextWord.toString());
					nextWord = new StringBuffer();
				}
			}
			else {
				//add all other characters to the nextWord 
				nextWord.append(nextChar);
				insideEntry = true; 
			}

		}
		// add the last word (assuming not empty)
		// trim the white space before adding to the list
		if (!nextWord.toString().equals("")) {
			entries.add(nextWord.toString().trim());
		}

		return entries;
	}
	
	/**
	 * Method will create a Tree object by parsing all the strings from @param
	 * 
	 * @param a
	 * @return Tree object
	 */
	static Tree createTree(ArrayList<String> a){
		Tree tree;
		//we know we need specific columns: 1, 4, 7, 8, 10, 26, 30, 40, 41
		tree = new Tree(Integer.parseInt(a.get(0)), Integer.parseInt(a.get(3)), a.get(6), 
				a.get(7), a.get(9), Integer.parseInt(a.get(25)), a.get(29), Double.parseDouble(a.get(39)), 
				Double.parseDouble(a.get(40)));
		
		return tree;
	}
	
	/**
	 * Method takes all inputs needed and formats them into a string by using the species name
	 * that the user enters to get more information about.
	 * 
	 * @param input
	 * @return info
	 */
	public static String getInfo(String input){
		int nycSPC = NYCStreetTrees.nycTrees.getCountByTreeSpecies(input);
		int nycT = NYCStreetTrees.nycTrees.getTotalNumberOfTrees();
		int qSPC = NYCStreetTrees.nycTrees.getCountByTreeSpeciesBorough(input, "queens");
		int qTrees = NYCStreetTrees.nycTrees.getCountByBorough("queens");
		int bkSPC = NYCStreetTrees.nycTrees.getCountByTreeSpeciesBorough(input, "brooklyn");
		int bkTrees = NYCStreetTrees.nycTrees.getCountByBorough("brooklyn");
		int mSPC = NYCStreetTrees.nycTrees.getCountByTreeSpeciesBorough(input, "manhattan");
		int mTrees = NYCStreetTrees.nycTrees.getCountByBorough("manhattan");
		int siSPC = NYCStreetTrees.nycTrees.getCountByTreeSpeciesBorough(input, "staten island");
		int siTrees = NYCStreetTrees.nycTrees.getCountByBorough("staten island");
		int bxSPC = NYCStreetTrees.nycTrees.getCountByTreeSpeciesBorough(input, "bronx");
		int bxTrees = NYCStreetTrees.nycTrees.getCountByBorough("bronx");
		
		double nycDiv = 0;
		if(nycT == 0){nycDiv = 0;}
		else{nycDiv = (double) nycSPC / (double) nycT; nycDiv*=100;}
		
		double qDiv = 0;
		if(qTrees == 0){qDiv = 0;}
		else{qDiv = (double) qSPC / (double) qTrees; qDiv*=100;}
		
		double bkDiv = 0;
		if(bkTrees == 0){bkDiv = 0;}
		else{bkDiv = (double) bkSPC / (double) bkTrees; bkDiv*=100;}
		
		double mDiv = 0;
		if(mTrees == 0){mDiv = 0;}
		else{mDiv = (double) mSPC / (double) mTrees; mDiv*=100;}
		
		double siDiv = 0;
		if(siTrees == 0){siDiv = 0;}
		else{siDiv = (double) siSPC / (double) siTrees; siDiv*=100;}
		
		double bxDiv = 0;
		if(bxTrees == 0){bxDiv = 0;}
		else{bxDiv = (double) bxSPC / (double) bxTrees; bxDiv*=100;}
		
		String nyc = "NYC";
		String q = "Queens";
		String bk = "Brooklyn";
		String bx = "Bronx";
		String si = "Staten Island";
		String m = "Manhattan";
		
		String info = String.format("%-15s: %,7d (%,-2d) %,15.2f%% \n%-15s: %,7d (%,-2d) %,15.2f%%\n"
				+ "%-15s: %,7d (%,-2d) %,15.2f%%\n%-15s: %,7d (%,-2d) %,15.2f%%\n"
				+ "%-15s: %,7d (%,-2d) %,15.2f%%\n%-15s: %,7d (%,-2d) %,15.2f%%\n", nyc,
				nycSPC, nycT, nycDiv, q, qSPC, qTrees, qDiv, bk, bkSPC, bkTrees, 
				bkDiv, m, mSPC, mTrees, mDiv, si, siSPC, siTrees, siDiv, bx,
				bxSPC, bxTrees, bxDiv);
		return info;
	}
	
	
	/**
	 * Takes the parameter as an array, combines the array into a string and feeds it into a new File obj.
	 * The File obj is read and information is taken from each line to create a Tree object, which then
	 * goes into a TreeCollection object.
	 * 
	 * User input is needed to decide what information to print out, depending on the species given
	 *
	 *
	 * @param args
	 * @throws SpeciesNotFoundException 
	 * @throws FileCannotBeRead 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws SpeciesNotFoundException, FileCannotBeRead, FileNotFoundException {
		
		String lineFromFile;
		ArrayList<String> lineArray;
		
		String m = "";
		for(String s : args){
			m+=s;
		}
		
		try{
			File file = new File(m);
			
			if(file.exists()){
				
				if(file.canRead()){
					Scanner fileIO = new Scanner(file);
					
					//only read the first line, it is full of headers
					fileIO.nextLine();
					
					// While the file has something left to read, it will read and input in array
					while(fileIO.hasNextLine()){
						lineFromFile = fileIO.nextLine();
						//splits the line
						lineArray = splitCSVLine(lineFromFile);
						//put the line into a Tree object & add the Tree to the TreeCollection						
						Tree t = createTree(lineArray);
						nycTrees.add(t);
					}
					
					fileIO.close();
				}
				else{throw new FileCannotBeRead();}	
				
				//create a scanner to ask the user to enter a species name that they want to learn more abt
				Scanner scanner = new Scanner(System.in);
				String input = "";
			
				while(!input.equalsIgnoreCase("quit")){
					System.out.println("Enter a species name you would like to know more about \n"
							+ "('quit' to stop): ");
					input = scanner.next();
					/* If the input is not found (any case) in the TreeList, throw an exception and ask
					 * again.
					 * If the input is found format the output to be pleasing to the eye, then print*/
					try{
						if(nycTrees.getCountByTreeSpecies(input) > 0){
							//print out the information we need
							String speciesMatches = "";
							for(String s : nycTrees.getMatchingSpecies(input)){
								speciesMatches += "\t" + s + "\n";
							}
							System.out.printf("All the species found: \n%s\n", speciesMatches);
							System.out.println(getInfo(input));
						}
						else if(input.equalsIgnoreCase("quit")){break;}
						else{throw new SpeciesNotFoundException();}
					}
					catch(SpeciesNotFoundException s){System.err.println("Error: The species '"
							+  input + "' does not exist.");continue;}
				}//end of while loop
				
				scanner.close(); // close scanner
			}
			else{throw new FileNotFoundException();}			
		} //end of try block
		catch(FileNotFoundException f){System.err.println("Error: The file you provided '"
				+ m + "' was not found.");}
		catch(FileCannotBeRead f){System.err.println("Error: The file you provided '"
				+ m + "' cannot be read.");}
		
		System.out.println("Goodbye!");
	} //end of main		
}//end of class
