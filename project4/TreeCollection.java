package project4_new;

import java.util.*;

/**
 * Holds all Tree objects in a binary search tree. Inherits methods from MyBST.
 * 
 * @author Candace Johnson
 * @version 4/13/2017
 */
public class TreeCollection extends MyBST<Tree>{

	int bySPC;
	Collection<String> uniqueSPC = new ArrayList<String>();	
	int numOfTreesSB;
	ArrayList<Integer> treesInBoroughs;
	
	
	/**
	 * This default constructor will set the root to null and set 5 spaces in the
	 * treesInBoroughs ArrayList<Integer> to 0;
	 */
	public TreeCollection() {
		root = null;
		treesInBoroughs = new ArrayList<Integer>();
		// set first 5 spots to 0 instead of null to avoid nullpointerexception
		for (int i = 0; i < 5; i++) {
			treesInBoroughs.add(0);
		}
	}
	
	/**
	 * This method returns the inherited size() method.
	 * 
	 * @return size()
	 */
	public int getTotalNumberOfTrees(){
		return size();
	}
	

	/**
	 * Sets the global variable of bySPC to 0. Uses getMatchingSpecies to receive
	 * a collection of specific species names that match the @param then uses each specific
	 * species name as a @param for getCBTSHelper(n, s), starting from the root every time.
	 * 
	 * @param speciesName
	 * @return bySPC  global variable
	 */
	public int getCountByTreeSpecies (String speciesName) {
		bySPC = 0; // reset every time the method is called
		Collection<String> list = getMatchingSpecies(speciesName);
		for (String s: list) {
			getCBTSHelper(root, s);
		}
		return bySPC;
	}
	
	/**
	 * Takes a @param as a guide to search through the BST by speciesName.
	 * This search is case insensitive.
	 * The method modified the global variable bySPC each time a node(Tree) of the same
	 * species is found. 
	 * 
	 * @param node
	 * @param speciesName
	 */
	private void getCBTSHelper (BSTNode<Tree> node, String speciesName) {
		if (node == null) {
			// do nothing
		}		
		// if species name equals @param
		if (node.getData().getSpecies().equalsIgnoreCase(speciesName)) {
			bySPC++;	
			// call rec method on both right and left nodes
			if (node.getLeft() != null)
				getCBTSHelper(node.getLeft(), speciesName);
			if (node.getRight() != null)
				getCBTSHelper(node.getRight(), speciesName);
		}
		else if (!node.getData().getSpecies().equalsIgnoreCase(speciesName)) {
			// check if name of species is > or < than @param
			if (node.getData().getSpecies().compareToIgnoreCase(speciesName) < 0 && node.getRight() != null) {
				getCBTSHelper(node.getRight(), speciesName);
			}
			else if (node.getData().getSpecies().compareToIgnoreCase(speciesName) > 0 && node.getLeft() != null) {
				getCBTSHelper(node.getLeft(), speciesName);
			}
		}
	}
	
	/**
	 * Creates a new ArrayList collection. For each @param that is placed in this method,
	 * the global variable uniqueSPC will be accessed to check if the @param is in the list
	 * of unique species, if it is, it will be added to the ArrayList that will be returned.
	 * 
	 * @param speciesName
	 * @return ArrayList<String> list
	 */
	public Collection<String> getMatchingSpecies (String speciesName) {
		// create a new collection
		Collection<String> list = new ArrayList<String>();
		for (String s: uniqueSPC) {
			if (s.toLowerCase().contains(speciesName.toLowerCase()) && !list.contains(s))
				list.add(s);
		}
		return list;
	}
	
	
	/**
	 * This method returns the amount of trees in the given borough name
	 * If the name is not a real borough, return 0
	 * boroName is treated as case-insensitive
	 * 
	 * @param boroName
	 * @return an int
	 */
	public int getCountByBorough (String boroName){
		
		switch (boroName.toLowerCase()) {
        	case "queens": return treesInBoroughs.get(0);
        	case "brooklyn": return treesInBoroughs.get(1);
        	case "bronx": return treesInBoroughs.get(2);
        	case "manhattan": return treesInBoroughs.get(3);
        	case "staten island": return treesInBoroughs.get(4);
        	default: 
        		return 0;
		}			
	}
	
	
	/**
	 * This method returns the number of Tree objects whose species AND borough match the 
	 * parameters. If the species or the boroname provided are non-existent, return 0.
	 * The boroName and speciesName are treated as case-insensitive.
	 * This method calls getMatchingSpecies and uses the collection that is returned from
	 * that to call getCBTSBHelper(n, m, b) to search through the BST starting from the 
	 * root each time.
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
		
		ArrayList<String> list = new ArrayList<String>();
		for (String s : getMatchingSpecies(speciesName))
			list.add(s);
		
		for (String m : list){
			if (root != null){
				numOfTreesSB += getCBTSBHelper(root, m, boroName);
			}
		}
		
		return numOfTreesSB;
	}
	
	/**
	 * Takes the @param node and compares its speciesName to the @param speciesName and
	 * its borough to the @param boroName. As it recursively searches through the BST,
	 * the return value is accumulated.
	 * 
	 * @param node
	 * @param speciesName
	 * @param boroName
	 * @return int
	 */
	private int getCBTSBHelper (BSTNode<Tree> node, String speciesName, String boroName) {
			// if node is null
			if (node == null)
				return 0;
			// if species name equals @param
			if (node.getData().getSpecies().equalsIgnoreCase(speciesName)) {
				if (node.getData().getBoroname().equalsIgnoreCase(boroName)) {
					if (node.getLeft() != null && node.getRight() != null) {
						int n = 1;
						n += getCBTSBHelper(node.getRight(), speciesName, boroName);
						n += getCBTSBHelper(node.getLeft(), speciesName, boroName);
						return n;
					}
					if (node.getLeft() != null)
						return 1 + getCBTSBHelper(node.getLeft(), speciesName, boroName);
					if (node.getRight() != null)
						return 1 + getCBTSBHelper(node.getRight(), speciesName, boroName);
					return 1;
				}
				else {
					if (node.getLeft() != null && node.getRight() != null) {
						int n = 0;
						n += getCBTSBHelper(node.getRight(), speciesName, boroName);
						n += getCBTSBHelper(node.getLeft(), speciesName, boroName);
						return n;
					}
					// call rec method on both right and left nodes
					if (node.getLeft() != null)
						return 0 + getCBTSBHelper(node.getLeft(), speciesName, boroName);
					if (node.getRight() != null)
						return 0 + getCBTSBHelper(node.getRight(), speciesName, boroName);
					return 0;
				}
			}
			// if species name does not equal @param
			else {
				// check if name of species is > or < than @param
				if (node.getData().getSpecies().compareToIgnoreCase(speciesName) < 0) {
					if (node.getRight() != null) {
						return 0 + getCBTSBHelper(node.getRight(), speciesName, boroName);
					}
					return 0;
				}
				else if (node.getData().getSpecies().compareToIgnoreCase(speciesName) > 0) {
					if (node.getLeft() != null) {
						return 0 + getCBTSBHelper(node.getLeft(), speciesName, boroName);
					}
					return 0;
				}
				return 0;
			}
	}
	
	/**
	 * Overrides the inherited add(e) method by using the super.add(e) to do the original
	 * addition of the Tree object then accesses the Tree object's borough and species names
	 * to modify the treesInBoroughs and uniqueSPC global variables.
	 * 
	 * @param Tree e
	 * @return boolean
	 */
	@Override
	public boolean add (Tree e) {
		super.add(e);
		
		// add trees the count of trees by boroughs
		switch (e.getBoroname().toLowerCase()) {
	    	case "queens": 
	    		treesInBoroughs.set(0, treesInBoroughs.get(0) + 1);
	    		break;
	    	case "brooklyn":
	    		treesInBoroughs.set(1, treesInBoroughs.get(1) + 1);
	    		break;
	    	case "bronx": 
	    		treesInBoroughs.set(2, treesInBoroughs.get(2) + 1);
	    		break;
	    	case "manhattan": 
	    		treesInBoroughs.set(3, treesInBoroughs.get(3) + 1);
	    		break;
	    	case "staten island": 
	    		treesInBoroughs.set(4, treesInBoroughs.get(4) + 1);
	    		break;
		}
		
		if (!uniqueSPC.contains(e.getSpecies()))
			uniqueSPC.add(e.getSpecies());
		
		if (super.add(e))
			return true;
		return false;
	}

	/**
	 * Represents the TreeCollection object by returning a String of the total number 
	 * of Trees in the city and each borough.
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

}
