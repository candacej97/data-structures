package project1;

/**
 * Will print out an error message when the species provided does not exist
 * @author Candace Johnson
 */
public class SpeciesNotFoundException extends Exception {

	public SpeciesNotFoundException() {
		System.err.println("Error: The species does not exist.");
	}

}
