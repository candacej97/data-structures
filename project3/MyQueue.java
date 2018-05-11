package project3;
/**
 * My implementation of a Queue class based on the project3 spec and the Java 8
 * documentation for any exceptions that should be thrown. There are two global
 * private variables, front and back. These keep track of the front and back of the
 * queue.
 * 
 * @author Candace Johnson
 * @param <E>
 * @version 3/27/2017
 */
public class MyQueue<E> {

	private MyNode<E> front;
	private MyNode<E> back;
	
	/**
	 * Constructor initializes the two private variables to null when a new instance
	 * of this object is created.
	 */
	MyQueue () {
		// front points to the front of a line
		front = null;
		back = null;
	}
	
	/**
	 * Method returns the data of the first node. If the queue is empty, it will
	 * return null.
	 * 
	 * @return null/front.getData()
	 */
	E peek () {
		if (front == null)
			return null;
		return front.getData();
	}
	
	/**
	 * Method returns the first node's data (null if the queue is empty), and
	 * removes that first node. 
	 * 
	 * @return null/temp (i.e. data from the first element)
	 */
	E poll () {
		if (front == null)
			return null;
		E temp = front.getData();
		front = front.getNext();
		return temp;
	}
	
	/**
	 * Method adds the given param to the back of the queue. Per the project 3 specs,
	 * the method also returns true if the parameter is able to be added to the back
	 * of the queue. An exception is thrown if the parameter is null.
	 * 
	 * @param item
	 * @throws NullPointerException
	 * @return true
	 */
	boolean offer (E item) {
		if (item == null)
			throw new NullPointerException();
		// Do not cause an IllegalArgumentException per Question 147 on Piazza
		// ClassCastException is thrown by JVM automatically when using generics	
		
		MyNode<E> newNode = new MyNode<E>(item);

		if (front == null) {
			front = newNode;
			back = front;
		}
		else{
			newNode.setNext(back.getNext());
			back.setNext(newNode);
			back = newNode;
		}
		return true;
	}
	
}
