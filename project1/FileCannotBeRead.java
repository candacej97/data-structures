package project1;

/**
 * Will print out an error message when the file cannot be read
 * @author Candace Johnson
 */
class FileCannotBeRead extends Exception {
	
	FileCannotBeRead(){
		System.err.println("Error: The file entered cannot be read.");
	}
	
}
