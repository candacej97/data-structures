package project3;
import java.util.EmptyStackException;

/**
 * My implementation of a Stack class based on the project3 spec and the Java 8
 * documentation for any exceptions that should be thrown.
 * One global private variable is used, top. This variable is used to indicate where
 * the top of the stack is (i.e. where the last element added is).
 * 
 * @author Candace Johnson
 * @param <E>
 * @version 3/27/2017
 */
public class MyStack<E> {

	private MyNode<E> top;
	
	/**
	 * Constructor initializes a new instance of this object to have a top pointing
	 * to null, which indicates that it is empty.
	 */
	MyStack(){
		// top points to the top of the stack
		top = null;
	}
	
	/**
	 * This method will return true if the object is empty (top points to null)
	 * or will return false otherwise.
	 * 
	 * @return true/false
	 */
	boolean empty(){
		if (top == null) 
			return true;
		return false;
	}
	
	/**
	 * Returns the data of the last added node. If the top is null, it will throw
	 * an exception as per Java 8 documentation.
	 * 
	 * @throws EmptyStackExeption
	 * @return top.getData()
	 */
	E peek () {
		if (top == null)
			throw new EmptyStackException();
		return top.getData();
	}
	
	/**
	 * Removes the last entered element and returns its data. If the top is null
	 * it will throw an exception.
	 * 
	 * @throws EmptyStackException
	 * @return data from the top node
	 */
	E pop () {
		if (top == null)
			throw new EmptyStackException();
		E temp = top.getData();
		top = top.getNext();
		return temp;
	}
	
	/**
	 * Method creates a new node (filled with the param) and adds it onto the stack.
	 * It will return the same data per project3 spec.
	 * 
	 * @param item
	 * @return item
	 */
	E push (E item) {
		MyNode<E> next = new MyNode<E>(item, top);
		top = next;
		return item;
	}
	
}
