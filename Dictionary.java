package project4;


/**
 * Dictionary Class - Contains methods to add and search an AVL Tree
 * of string dictionary entries that are meant to be populated by an input dictionary file.  
 * 
 * @author Monica Martinez 
 * @version December 6, 2014
 *
 */
public class Dictionary {

		public AVLTree<String> updatedDictCopy;
		
		/**
		 * Class Constructor
		 * 
		 * Creates AVL Tree to be populated with input file.
		 * 
		 */
		public Dictionary() {
			updatedDictCopy = new AVLTree<String>();
		}
		

		/**
		 * Searches dictionary for word.
		 * @param wordTarget
		 * @return true if wordTarget matches a dictionary entry in the dictCopy array.
		 * 
		 */
		public boolean wordSearch( String wordTarget){
			return updatedDictCopy.contains(wordTarget);	
		}
 
		
		/**
		 * Searches dictionary for word prefixes.
		 * @param prefixTarget
		 * @return true if prefixTarget matches a dictionary entry in the dictCopy array.
		 * 
		 */
		public boolean prefixSearch(String prefixTarget){
			return (updatedDictCopy.contains(prefixTarget));
		}
		
		/**
		 * Adds contents of a file onto AVL tree.
		 * 
		 * @param entry
		 * 
		 */
		public void add(String entry){
			updatedDictCopy.add(entry);
		}
			
	}



