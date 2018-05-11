package project3;

/**
 * My implementation of a Node class based on the project 3 spec and what we have
 * learned that a node contains. There are two global private variables, data and
 * next. These keep track of the data in the node and a reference to the next node
 * in relation to the current one.
 * 
 * @author Candace Johnson
 * @version 3/27/2017
 */
public class MyNode<E> {
	private E data;
	private MyNode<E> next;
	
	/**
	 * This constructor takes the parameter and sets it as the data, and sets the
	 * next node to null.
	 * 
	 * @param dataField
	 */
	public MyNode(E dataField) {
		data = dataField;
		next = null;
	}
	/**
	 * This constructor takes two parameters and sets the first one to the data,
	 * and sets the second one to be the data of the next node.
	 * 
	 * @param dataField
	 */
	public MyNode(E dataField, MyNode<E> nextNode) {
		data = dataField;
		next = nextNode;
	}
	
	/**
	 * Allows other classes to set the data of the current node.
	 * 
	 * @param dataField
	 */
	public void setData(E dataField){
		this.data = dataField;
	}
	/**
	 * Method allows other classes to retrieve the data of the current node.
	 */
	public E getData() {
		return data;
	}
	
	/**
	 * Allows other classes to set the current node's next node's data.
	 * 
	 * @param node
	 */
	public void setNext(MyNode<E> node){
		this.next = node;
	}
	/**
	 * Method allows other classes to retrieve the next node of the current node.
	 */
	public MyNode<E> getNext() {
		return next;
	}

}
