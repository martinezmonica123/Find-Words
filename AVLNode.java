package project4;


/**
 * Implementation of an AVL node.
 *
 * @author Monica Martinez
 * @version December 6, 2014
 *
 */
public class AVLNode<E extends Comparable <E> > implements Comparable <AVLNode <E> > {
	
	protected AVLNode<E> left;
	protected AVLNode<E> right;
	protected E data;
	protected int height;
	
	protected int numNodes;
	
	/**
	 * AVLNode constructor if children are not known.
	 * 
	 * @param data
	 */
	public AVLNode(E data){
		this.data = data;
		left = null;
		right = null;
		height = 0;
		numNodes++;
	}
	
	/**
	 * AVLNode constructor if left and right child are known.
	 * 
	 * @param data
	 * @param left
	 * @param right
	 */
	public AVLNode(E data, AVLNode<E> left, AVLNode<E> right){
		this.data = data;
		this.left = left;
		this.right = right;
		height = 0;	
		numNodes++;
	}

	/**
	 * Returns left child of a node.
	 * @return
	 */
	public AVLNode<E> getLeft() {
		return left;
	}

	/**
	 * Sets left child of a node.
	 * @param left
	 */
	public void setLeft(AVLNode<E> left) {
		this.left = left;
	}

	/**
	 * Returns right child of a node.
	 * @return
	 */
	public AVLNode<E> getRight() {
		return right;
	}

	/**
	 * Sets right child of a node.
	 * @param right
	 */
	public void setRight(AVLNode<E> right) {
		this.right = right;
	}
	
	/**
	 * returns node data.
	 * @return
	 */
	public E getData() {
		return data;
	}

	/**
	 * Sets node data.
	 * @param data
	 */
	public void setData(E data) {
		this.data = data;
	}

	/**
	 * Returns node height.
	 * @return
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Sets node height.
	 * @param height
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(AVLNode<E> other) {
		return this.data.compareTo(other.data);
	}

	/**
	 * Returns number of nodes created.
	 * @return
	 */
	public int getNumNodes() {
		return numNodes;
	}	
	
}
