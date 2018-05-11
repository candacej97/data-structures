package project2;

/**
 * Will print out an error message when the file is empty
 * 
 * @author Candace Johnson
 */
class FileIsEmptyException extends Exception {
	
	FileIsEmptyException(){
		System.err.println("Error: The file entered is empty.");
	}
	
}