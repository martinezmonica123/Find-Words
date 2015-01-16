package project4;

import java.util.Iterator;
import java.util.Queue;

/**
 * AVL Tree Implementation.
 * 
 * @author Monica Martinez
 * @version December 6, 2014
 *
 * 
 */
public class AVLTree <E extends Comparable<E> > implements Iterable<E>  {
	
	
	protected AVLNode<E> root;
	private int numNodesTree;
	
	/**
	 * Constructor instantiates root variable.
	 */
	public AVLTree() {		
		root = null;
	}


//------------------------------INSERTION-----------------------------------
	
	/**
	 * Wrapper  method for recursive insertion into AVL Tree. 
	 * Also increases number of nodes in tree.
	 * 
	 * @param item
	 */
	public void add(E item) {
		if (item != null){
			root = addRec(root, item);
			numNodesTree++;
		}			
	}
	
	/**
	 * Recursively adds nodes into the AVLTree.
	 * 
	 * @param tree
	 * @param item
	 * @return tree
	 */
	private AVLNode<E> addRec(AVLNode<E> tree, E item) {
		
		if (tree == null) {
			tree = new AVLNode<E>(item);
			return tree;
		}
		else if (item.compareTo(tree.getData()) < 0) {
			tree.setLeft(addRec(tree.getLeft(), item));

		}
		else if (item.compareTo(tree.getData()) > 0) {
			tree.setRight(addRec(tree.getRight(), item));

		}	
		//only balances and/or updates node heights if the number of Nodes is greater than 1
		if (tree.numNodes > 1) {
			
			//balance if AVL tree property re height differences is broken
			if (balanceFactor(tree) == 2 || balanceFactor(tree) == -2)
				return balance(tree);
			
			//update node heights
			else {
				updateHeight(tree);
				return tree;
			}
		}
		return tree;
	}
	
	
//------------------------------DELETION-----------------------------------	
	/**
	 * Wrapper method for recursive tree deletion.
	 * Decreases number of overall tree nodes.
	 * 
	 * @param item
	 */
	public void remove(E item) {
		if (item != null) {
			root = removeRec(root, item);
			numNodesTree--;
		}
	}
	
	/**
	 * Recursively deletes tree nodes. 
	 * 
	 * @param tree
	 * @param item
	 * @return
	 */
	private AVLNode<E> removeRec(AVLNode<E> tree, E item){
		
		if (tree == null)
			return tree;
		
		else if (item.compareTo(tree.getData()) < 0)  
			tree.left = removeRec(tree.left, item);

		else if (item.compareTo(tree.getData()) > 0) 
			tree.right = removeRec(tree.right, item);

		else {
			//no children
			if (tree.left == null && tree.right == null)
				return null;
			
			//one child
			else if (tree.left == null)
				return tree.right;
			else if (tree.right == null)
				return tree.left;
			
			//two children
			else if (tree.left != null && tree.right != null) {
				tree.data = findMin(tree.right).getData();
				tree.right = removeRec(tree.right, tree.getData());
				return balance(tree);
			}
		}
		
		//balance if AVL tree property re height differences is broken
		if (balanceFactor(tree) == 2 || balanceFactor(tree) == -2)
			return balance(tree);
		
		//update node heights
		else {
			updateHeight(tree);
			return tree;
		}
	}  
	
	
//------------------------------CONTAINS-----------------------------------	
	/**
	 * Wrapper method for contains method.
	 * @param item
	 * @return
	 */
	public boolean contains(E item) {
		if (item != null) 
			return contains(root, item);
		return false;
	}
	
	/**
	 * Binary search for AVL tree.
	 * 
	 * @param nodeRoot
	 * @param item
	 * @return
	 */
	private boolean contains(AVLNode<E> nodeRoot, E item) {
		
		while (nodeRoot != null) {
			if (item.compareTo(nodeRoot.getData()) < 0 )
				nodeRoot = nodeRoot.left;
			else if (item.compareTo(nodeRoot.getData()) > 0 )
				nodeRoot = nodeRoot.right;
			else
				return true;
		}
		return false;	
	}
	
	
//-------------------------------------------------------------------------	
	
	/**
	 * Determine the number of nodes in the AVL tree.
	 * 
	 * @return numNodesTree
	 */
	public int size(){
		return numNodesTree;
	}
	
	/**
	 * Finds and returns smallest node in tree.
	 * 
	 * @param node
	 * @return
	 */
	private AVLNode<E> findMin(AVLNode<E> node){
        if (node == null)
            return node;

        while (node.left != null)
            node = node.left;
        return node;
    }
	
    /**
     * Determines if tree is empty.
     * 
     * @return true if empty, false otherwise.
     */
    private boolean isEmpty() {
        return root == null;
    }
	
	/**
     *Wrapper for printTree method.
     */
    public void printTree(){
        if (isEmpty())
            System.out.println("Empty tree");
        else
            printTree(root);
    }

    /**
     * Recursive in-order traversal for printing tree.
     * 
     * @param t the node that roots the tree.
     */
    private void printTree(AVLNode<E> tree) {
        if (tree != null) {
            printTree(tree.left);
            System.out.println(tree.getData());
            printTree(tree.right);
        }
    }
	
	
//--------------------HEIGHT/BALANCE METHODS------------------------------
	   
	/**
	 * Recursively balances the tree with the appropriate rotation
	 * based on the type of imbalance detected.
	 * 
	 * @param tree
	 * @return
	 */
	private AVLNode<E> balance(AVLNode<E> tree) {
				
		if (tree.right.height > tree.left.height) {
			AVLNode<E> rightChild = tree.right;
			
			if (rightChild.right.compareTo(rightChild.left) > 0)
				return balanceRR(tree);
			else
				return balanceRL(tree);
		}
		else {
			AVLNode<E> leftChild = tree.left;
			
			if (leftChild.left.compareTo(leftChild.right) > 0)
				return balanceLL(tree);
			else
				return balanceLR(tree);
		}		
	}
	
	/**
	 *Determines the height difference between two nodes.
	 * 
	 * @param node
	 * @return
	 */
	private int balanceFactor(AVLNode<E> node) {
		
		if (node.right.equals(null) ) 
			return -node.height;
		if (node.left.equals(null) ) 
			return node.height;
		return node.right.height - node.left.height;
	}

	/**
	 * Updates the heights of the tree nodes.
	 * 
	 * @param node
	 */
	private void updateHeight(AVLNode<E> node) {
		
		if (node.equals(null) ) 
			node.height = 0;
		else if (node.left.equals(null))
			node.height = node.right.height + 1;
		else if (node.right.equals(null))
			node.height = node.right.height + 1;
		else
			node.height = Math.max(node.left.height, node.right.height) + 1;
	}
	
	
//--------------------ROTATION METHODS-----------------------------------
	/**
	 * Implementation of LL Rotation.
	 * Updates node heights after the rotation.
	 * 
	 * @param nodeA
	 * @return
	 */
	private AVLNode<E> balanceLL(AVLNode<E> nodeA) {
		
		AVLNode<E> nodeB = nodeA.left;
		
		nodeA.left = nodeB.right;
		nodeB.right = nodeA;
		
		updateHeight(nodeA);
		updateHeight(nodeB);
		
		return nodeB;
	}
	
	/**
	 * Implementation of LR Rotation.
	 * Updates node heights after the rotation.
	 * 
	 * @param nodeA
	 * @return
	 */
	private AVLNode<E> balanceLR(AVLNode<E> nodeA) {
		
		AVLNode<E> nodeB = nodeA.left;
		AVLNode<E> nodeC = nodeB.right;
		
		nodeA.left = nodeC.right;
		nodeB.right = nodeC.left;
		nodeC.left = nodeB;
		nodeC.right = nodeA;
		
		updateHeight(nodeA);
		updateHeight(nodeB);
		updateHeight(nodeC);
		
		return nodeC;
	}
	
	/**
	 * Implementation of RR Rotation.
	 * Updates node heights after the rotation.
	 * @param nodeA
	 * @return
	 */
	private AVLNode<E> balanceRR(AVLNode<E> nodeA) {
		
		AVLNode<E> nodeB = nodeA.right;
		
		nodeA.right = nodeB.left;
		nodeB.left = nodeA;
		
		updateHeight(nodeA);
		updateHeight(nodeB);
		
		return nodeB;
	}

	/**
	 * Implementation of RL Rotation.
	 * Updates node heights after the rotation.* 
	 * @param nodeA
	 * @return
	 */
	private AVLNode<E> balanceRL(AVLNode<E> nodeA) {
		
		AVLNode<E> nodeB = nodeA.right;
		AVLNode<E> nodeC = nodeB.left;
		
		nodeA.right = nodeC.left;
		nodeB.left = nodeC.right;
		nodeC.right = nodeB;
		nodeC.left = nodeA;
		
		updateHeight(nodeA);
		updateHeight(nodeB);
		updateHeight(nodeC);
		
		return nodeC;
	}

	
//---------------------------Iterator-----------------------------------
	
	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<E> iterator() {
		return new AVLTreeIterator<E>(this);
	}

	
//---------------------Internal Iterator Class----------------------------
	
	/**
	 *
	 *Internal Iterator Class for AVL Tree.
	 *
	 */
	class AVLTreeIterator<E extends Comparable<E> > implements Iterator<E> {
		
		AVLTree<E> tree;
		Queue<E> queue;
		
		/**
		 * AVLTreeIterator constructor.
		 * 
		 * @param tree
		 */
		public AVLTreeIterator(AVLTree<E> tree) {
			AVLNode<E> root;
			this.tree = tree;
			root = tree.root;
			if (root != null)
				queue.add(root.getData());
		}
			
		/* (non-Javadoc)
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext() {			
			return queue.size() > 0;
		}

		/* (non-Javadoc)
		 * @see java.util.Iterator#next()
		 */
		@SuppressWarnings("unchecked")
		@Override
		public E next() {
			
			E data = null;
			AVLNode<E> parentNode;
		
			if (hasNext()) {
				
				parentNode = (AVLNode<E>) queue.remove();
				data = parentNode.getData();
		
				if (parentNode.left!=null) 
					queue.add(parentNode.left.getData());
				if (parentNode.right!=null) 
					queue.add(parentNode.right.getData());
			
				return data;
			
			}
			return data;
		}

		/* (non-Javadoc)
		 * @see java.util.Iterator#remove()
		 */
		@Override
		public void remove() {
			return;
		}
		
	}

}
