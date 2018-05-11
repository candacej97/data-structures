package project4_new;
import java.util.*;

/**
 * Implementation of a binary search tree. This includes a root variable and a size variable.
 * 
 * @author Candace Johnson
 * @version 4/10/2017
 */
public class MyBST<E extends Comparable<E>> {
	protected BSTNode<E> root;
	private int size;
	
	/**
	 * Default constructor sets the root to null and size of the BST to 0.
	 */
	public MyBST () {
		root = null;
		size = 0;
	}
	
	/**
	 * If e is null, NullPointerException is thrown. If @param is not an instance of E, 
	 * ClassCastException is thrown. Otherwise, e is transfered to the addHelper(n, e) method
	 * and the size is increased by 1.
	 * 
	 * @param e
	 * @return boolean
	 */
	public boolean add (E e) throws NullPointerException, ClassCastException {
		if (e == null)
			throw new NullPointerException();
		// checks if the object entered is of the required type
		if (e instanceof Comparable<?>) {
			//create a new node
			BSTNode<E> newNode = new BSTNode<E>(e);
			// if there is no root, make the new node the root
			if (root == null) {
				root = newNode;
				size++; // increment size
				return true;
			}
			// if the tree already exists, we will not add a duplicate
			if (contains(e))
				return false;
			// otherwise, increment size and return helper method
			size++;
			return addHelper(root, newNode);
		}
		else
			throw new ClassCastException();
	}

	/**
	 * The method checks the node to see if it is a duplicate of the newNode created in the
	 * add(e) method. If not, it traverses through the BST to add the node in the correct
	 * spot depending on the species name and tree id number and returns True. Otherwise, 
	 * it returns false.
	 * 
	 * @param node
	 * @param newNode
	 * @return boolean
	 */
	private boolean addHelper(BSTNode<E> node, BSTNode<E> newNode) {
		// if this is a duplicate node
		if (node.getData().compareTo(newNode.getData()) == 0) {
			return false;
		}
		// if 
		else if (node.getData().compareTo(newNode.getData()) > 0) {
			if (node.getLeft () != null)
				addHelper(node.getLeft(), newNode);
			else {
				node.setLeft(newNode);
				return true;
			}
		}
		else if (node.getData().compareTo(newNode.getData()) < 0) {
			if (node.getRight() != null)
				addHelper(node.getRight(), newNode);
			else {
				node.setRight(newNode);
				return true;
			}
		}
		return false;
	}
    
	/**
	 * The @param checked if it is null, if it is contained in the tree. If it is null,
	 * NullPointerException is thrown. If it is not in the tree, false will be returned. If
	 * it is not of type E, ClassCastException will be thrown. Otherwise, it will be used 
	 * as a param in the removeHelper(n, p) method and the size of the BST will 
	 * be decremented. 
	 * 
	 * @param o
	 * @return boolean
	 */
	// It is safe to suppress the warnings because I check if the object is comparable in the if statement
	@SuppressWarnings("unchecked")
	public boolean remove (Object o) throws ClassCastException, NullPointerException {
		if (o == null)
			throw new NullPointerException();
		else if (!contains(o))
			return false;
		else if (o instanceof Comparable<?>) {
			//decrement size
			size--;
			removeHelper(root, (E) o);
			return true;
		}
		else
			throw new ClassCastException();
	}
	
	/**
	 * This method removes the node by either setting the node's data to null (if its a leaf),
	 * setting the node's data to its child's data (when there is no subtree), or using the
	 * predecessor rule to determine which node will take its place.
	 * 
	 * @param node
	 * @return BSTNode<E>
	 */
	private BSTNode<E> removeNode (BSTNode<E> node) {
		// if node is a leaf
		if (node.getLeft() == null && node.getRight() == null) {
			return node.getRight();
		}
		// make parent point to grandchild if node only has a right or left node
		else if (node.getLeft() == null && node.getRight() != null) {
			return node.getLeft();
		}
		else if (node.getLeft() != null && node.getRight() == null) {
			return node.getRight();
		}
		else {
			// when the node is in the middle of the tree
			// USE PREDECESSOR RULE
			E data = findPredecessor(node);
			node.setData(data);
			node.setLeft(removeHelper(node.getLeft(), data));
			
		}
		return null;
	}

	/**
	 * Recursive method for remove(o) searches through the BST for the node that needs to be
	 * removed.
	 * 
	 * @param node
	 * @param o
	 * @return BSTNode<E>
	 */
	private BSTNode<E> removeHelper(BSTNode<E> node, E o) {
		if (node == null){}
		// when you've found the node you want to remove
		if (node.getData().compareTo(o) == 0) {
			// call remove on the predecessor
			node = removeNode(node);
			return node;
		}
		else if (node.getData().compareTo(o) < 0) {
			removeHelper(node.getRight(), o);
		}
		else if (node.getData().compareTo(o) > 0) {
			removeHelper(node.getLeft(), o);
		}
		return node;
	}
	
	/**
	 * Takes the node @param and finds the predecessor's data.
	 * 
	 * @param node
	 * @return the predecessor's data
	 */
	private E findPredecessor (BSTNode<E> node) { 
		if (node.getLeft() == null)
			throw new NullPointerException();
		node = node.getLeft();
		while (node.getRight() != null) {
			node = node.getRight();
		}
		return node.getData();
	}

	/**
	 * The @param checked if it is null, if it is contained in the tree. If it is null,
	 * NullPointerException is thrown. If it is not in the tree, false will be returned. If
	 * it is not of type E, ClassCastException will be thrown. Otherwise, it will be used 
	 * as a param in the containsHelper(n, e) method.
	 * 
	 * @param o
	 * @return boolean
	 */
	// It is safe to suppress the warnings because I checked if the object is comparable in the if statement
	@SuppressWarnings("unchecked")
	public boolean contains (Object o) throws NullPointerException, ClassCastException {
		if (o == null)
			throw new NullPointerException();
		if (root == null)
			return false;
		// checks if the object entered is of the required type
		if (o instanceof Comparable<?>)
			return containsHelper(root, (E) o);
		else
			throw new ClassCastException();
	}

	/**
	 * Traverses through the BST for the @param o. If it is found true will be returned.
	 * Otherwise, false will be returned.
	 * 
	 * @param node
	 * @param o
	 * @return boolean
	 */
	private boolean containsHelper(BSTNode<E> node, E o) {
		if (node.getData().compareTo(o) == 0) {
			return true;
		}
		else if (node.getData().compareTo(o) > 0) {
			if (node.getLeft() != null)
				return containsHelper(node.getLeft(), o);
		}
		else if (node.getData().compareTo(o) < 0) {
			if (node.getRight() != null)
				return containsHelper(node.getRight(), o);
		}
		return false;
	}
	
	/**
	 * Traverses the BST to the farthest left node and returns the data it finds.
	 * 
	 * @return e
	 */
	public E first(){
		if(root == null)
			throw new NoSuchElementException();
		BSTNode<E> current = root;
		while(current.getLeft() != null){
			current = current.getLeft();
		}
		return current.getData();
	}
	
	/**
	 * Traverses the BST to the farthest right node and returns the data it finds.
	 * 
	 * @return e
	 */
	public E last(){
		if(root == null)
			throw new NoSuchElementException();
		BSTNode<E> current = root;
		while(current.getRight() != null){
			current = current.getRight();
		}
		return current.getData();
		
	}
	
	/**
	 * Builds a base string that can be returned of all the nodes in the BST. Calls the
	 * private toStringHelper(n, s) method.
	 * 
	 * @return String object
	 */
	@Override
	public String toString(){
		String s = "";
		s += toStringHelper (root, "[");
		s += "]";
		return s;
	}
	
	/**
	 * Traverses the BST using the InOrder method and adds the node data to a string that
	 * will be returned to toString() method.
	 * 
	 * @param node
	 * @param string
	 * @return String object
	 */
	private String toStringHelper (BSTNode<E> node, String string) {
		// using inorder method
		if (node == null)
			return string;
		// go as far left as you can
		if (node.getLeft() != null)
			string += toStringHelper(node.getLeft(), string);
		
		// then call the node
		string += node.getData() + ", ";
		
		// then every right in the path
		if (node.getRight() != null)
			string += toStringHelper(node.getRight(), string);
		return string;
	}
	
	/**
	 * Returns the size of the BST.
	 * 
	 * @return
	 */
	public int size () {
		return size;
	}
}
