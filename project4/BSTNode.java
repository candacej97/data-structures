package project4_new;

/**
 * Implementation of a binary search tree node that contains the data, and references to the
 * right and left nodes.
 * 
 * @author Candace Johnson
 * @version 4/10/2017
 */
public class BSTNode<E extends Comparable<E>> implements Comparable<BSTNode<E>> {
	private E data;
	private BSTNode<E> right;
	private BSTNode<E> left;
	
	/**
	 * Constructor takes the data that will be set to this node's data.
	 * 
	 * @param data
	 */
	public BSTNode (E data) {
		this.data = data;
		right = null;
		left = null;
	}

	/**
	 * Returns the data of this node. 
	 * 
	 * @return data
	 */
	public E getData() {
		return data;
	}

	/**
	 * Sets the data of this node. 
	 * 
	 * @param data
	 */
	public void setData(E data) {
		this.data = data;
	}

	/**
	 * Returns the right node of this node. 
	 * 
	 * @return BSTNode<E>
	 */
	public BSTNode<E> getRight() {
		return right;
	}

	/**
	 * Sets the right node of this node. 
	 * 
	 * @param n
	 */
	public void setRight(BSTNode<E> n) {
		this.right = n;
	}

	/**
	 * Returns the left node of this node. 
	 * 
	 * @return BSTNode<E>
	 */
	public BSTNode<E> getLeft() {
		return left;
	}

	/**
	 * Sets the left node of this node. 
	 * 
	 * @param n
	 */
	public void setLeft(BSTNode<E> n) {
		this.left = n;
	}
	
	/**
	 * Overrides the Java compareTo(x) method. This method will compare the data of this node
	 * to the data of the @param.
	 * 
	 * @param node
	 */
	@Override
	public int compareTo(BSTNode<E> node) {
		return data.compareTo(node.getData());
	}
	
}
