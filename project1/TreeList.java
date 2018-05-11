package project1;

import java.util.ArrayList;

/**
 * Creates an ArrayList named listOfAllTrees that contains a list of all Tree Objects
 * 
 * @author Candace Johnson
 * @version 2/11/2017
 */
public class TreeList extends ArrayList<Tree>{

	ArrayList<Tree> listOfAllTrees;
	int totalNumOfTrees;
	int numOfTrees;
	int numOfTreesInBoro;
	int numOfTreesSB;
	ArrayList<String> finalList;
	
	
	/**
	 * This default constructor will create an empty list that will eventually contain Tree objects
	 */
	public TreeList() {
		this.listOfAllTrees = new ArrayList<Tree>();
	}
	
	/**
	 * This method counts the amount of tree objects stored in the listOfAllTrees
	 * 
	 * @return numOfTrees
	 */
	public int getTotalNumberOfTrees(){
		this.totalNumOfTrees = this.listOfAllTrees.size();
		return this.totalNumOfTrees;
	}

	/**
	 * This method counts the amount of Tree objects saved with the matching species name entered.
	 * Iterate through the ArrayList of Trees using a for loop to match the species name
	 * 
	 * @param speciesName
	 * @return numOfSpecies
	 */
	public int getCountByTreeSpecies (String speciesName){
		this.numOfTrees = 0;
		for(int i=0; i<listOfAllTrees.size(); i++){
			//checks if the param is the species is a substring of the species of the Tree object
			if(listOfAllTrees.get(i).getSpecies().toLowerCase().contains(speciesName.toLowerCase())){
				this.numOfTrees++;
			}
		}
		return this.numOfTrees;
	}
	
	/**
	 * This method returns the amount of trees in the given borough name
	 * If the name is not a real borough, return 0
	 * boroName is treated as case-insensitive
	 * 
	 * @param boroName
	 * @return numOfTreesInBoro
	 */
	public int getCountByBorough (String boroName){
		numOfTreesInBoro = 0;
		
		switch (boroName.toLowerCase()) {
        	case "queens": break;
        	case "brooklyn": break;
        	case "bronx": break;
        	case "manhattan": break;
        	case "staten island": break;
        	default: return numOfTreesInBoro;
		}
		
		for(int i=0; i<listOfAllTrees.size(); i++){
			if(boroName.equalsIgnoreCase(listOfAllTrees.get(i).getBoroname())){
				numOfTreesInBoro++;
			}
		}
		return numOfTreesInBoro;
	}
	
	/**
	 * This method returns the number of Tree objects whose species AND borough match the 
	 * parameters
	 * If the species or the boroname provided are non-existent, return 0
	 * boroName and speciesName are treated as case-insensitive
	 * 
	 * @param speciesName
	 * @param boroName
	 * @return numOfTrees
	 */
	public int getCountByTreeSpeciesBorough(String speciesName, String boroName){
		numOfTreesSB = 0;
		
		//first check if boroName exist
		switch (boroName.toLowerCase()) {
    		case "queens": break;
	    	case "brooklyn": break;
	    	case "bronx": break;
	    	case "manhattan": break;
	    	case "staten island": break;
	    	default: return numOfTreesSB;
		}
		
		for(Tree t: this.listOfAllTrees){
			//check to make sure that the given boroname matches the boroname of the tree
			if(boroName.equalsIgnoreCase(t.getBoroname())){
				//checks the species name of the tree
				if(t.getSpecies().toLowerCase().contains(speciesName.toLowerCase())){
					numOfTreesSB++;
				}
			}
		}
				
		return numOfTreesSB;
	}
	
	/**
	 * The method returns an ArrayList of Strings that contains a list of the trees species that 
	 * match the speciesName given.
	 * 
	 * @param speciesName
	 * @return listOfSpeciesNames
	 */
	public ArrayList<String> getMatchingSpecies(String speciesName){
		ArrayList<String> listOfSpeciesNames = new ArrayList<String>();
		
		//Search through the listOfAllTrees object for any trees that have species names that contain param
		for(int i=0; i<listOfAllTrees.size(); i++){
			
			//if object's list of trees contains param
			if(listOfAllTrees.get(i).getSpecies().toLowerCase().contains(speciesName.toLowerCase())){
				listOfSpeciesNames.add(listOfAllTrees.get(i).getSpecies());
			}
		}
		
		//create a new arraylist to get rid of the duplicates
		finalList = new ArrayList<String>();
		//loop through the arraylist and delete duplicates
		for(String s : listOfSpeciesNames){
			if(!finalList.contains(s)){
				finalList.add(s);
			}
		}
		
		return finalList;
	}

	/**
	 * Represents the TreeList object by returning a String of the total number of Trees in the city and each borough
	 * 
	 * @return treesToPrint
	 */
	@Override
	public String toString(){
		String treesToPrint = String.format("Total number of trees in NYC: %d\nTotal number of trees in Queens: %d\n"
				+ "Total number of trees in Brooklyn: %d\nTotal number of trees in Bronx: %d\n"
				+ "Total number of trees in Manhattan: %d\nTotal number of trees in Staten Island: %d\n", 
				this.getTotalNumberOfTrees(), this.getCountByBorough("queens"), this.getCountByBorough("brooklyn"), 
				this.getCountByBorough("bronx"), this.getCountByBorough("manhattan"), this.getCountByBorough("staten island"));
				
		return treesToPrint;
	}
	
	/*public static void main(String[] args){
		//TESTING ENVIRONMENT:
		
		//CREATE TREES AND TRY TO PRINT EACH OF THEIR PROPERTIES OUT AS WELL AS THESE PROPERTIES
		
		Tree t1 = new Tree(200540,3,"Alive","Fair","red maple",11375,"Queens",1027431.148,202756.7687);
		Tree t2 = new Tree(200540,21,"Alive","Fair","pin oak",11357,"Queens",1034455.701,228644.8374);
		Tree t3 = new Tree(204026,3,"Alive","Good","honeylocust",11211,"Brooklyn",1001822.831,200716.8913);
		Tree t4 = new Tree(204337,10,"Alive","Good","honeylocust",11211,"Brooklyn",1002420.358,199244.2531);
		Tree t5 = new Tree(189565,21,"Alive","Good","American linden",11215,"Brooklyn",990913.775,182202.426);
		Tree t6 = new Tree(190422,11,"Alive","Good","honeylocust",10023,"Manhattan",988418.6997,219825.5227);
		Tree t7 = new Tree(190426,11,"Alive","Good","honeylocust",10023,"Manhattan",988311.19,219885.2785);
		Tree t8 = new Tree(208649,9,"Alive","Good","American linden",10019,"Manhattan",987769.1163,217157.8561);
		Tree t10 = new Tree(209610,6,"Alive","Good","honeylocust",10305,"Staten Island",963073.1998,156635.5542);
		Tree t9 = new Tree(192755,21,"Alive","Fair","London planetree",11223,"Brooklyn",992653.7253,152903.6306);
		
		
		System.out.println(t1.getBoroname());
		
		//Create a TreeList Object
		TreeList allTrees = new TreeList();
		
		
		//Add trees to listOfAllTrees
		allTrees.listOfAllTrees.add(t1);
		allTrees.listOfAllTrees.add(t2);
		allTrees.listOfAllTrees.add(t3);
		allTrees.listOfAllTrees.add(t4);
		allTrees.listOfAllTrees.add(t5);
		allTrees.listOfAllTrees.add(t6);
		allTrees.listOfAllTrees.add(t7);
		allTrees.listOfAllTrees.add(t8);
		allTrees.listOfAllTrees.add(t9);
		allTrees.listOfAllTrees.add(t10);
		
		//print the list of all trees
		for(Tree t: allTrees.listOfAllTrees){
			System.out.printf("\nTree %d: \n%s\n", allTrees.listOfAllTrees.indexOf(t)+1,t.toString());
			
		}
		
		//print the methods called on the object of allTrees
		
		System.out.printf("Total Number of Trees: %d\n", allTrees.getTotalNumberOfTrees());
		System.out.printf("Count of Oak Trees: %d\n", allTrees.getCountByTreeSpecies("oak"));
		System.out.printf("Count of Linden Trees: %d\n", allTrees.getCountByTreeSpecies("linden"));
		System.out.printf("Count of Maple Trees: %d\n", allTrees.getCountByTreeSpecies("maple"));
		System.out.printf("Count of Honey Locust Trees: %d\n", allTrees.getCountByTreeSpecies("locust"));
		System.out.printf("Count of London Planetrees Trees: %d\n", allTrees.getCountByTreeSpecies("london"));
		System.out.printf("Count in Queens: %d\n", allTrees.getCountByBorough("queens"));
		System.out.printf("Count in SI: %d\n", allTrees.getCountByBorough("staten island"));
		System.out.printf("Count in BK: %d\n", allTrees.getCountByBorough("brooklyn"));
		System.out.printf("Count in BX: %d\n", allTrees.getCountByBorough("bronx"));
		System.out.printf("Count in Manhattan: %d\n", allTrees.getCountByBorough("manhattan"));
		System.out.printf("Count of HL in Manhattan: %d\n", allTrees.getCountByTreeSpeciesBorough("honeylocust", "manhattan"));
		
		//printing out the strings that are in the arraylist of the matching species of the input
		System.out.println("\nSpecies including the input:");
		for(String s : allTrees.getMatchingSpecies("lo")){
			System.out.println(s);}


		
		
		//Print toString output
		System.out.println("\n\nOutput of the toString() method:\n" + allTrees.toString());
		
		System.out.println(t1.equals(t2));
		
	}*/

}
