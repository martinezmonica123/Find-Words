package project4;

import java.util.ArrayList;


/**
 * Words Class - Contains methods to generate and print 
 * an array of permutations based on their existence in a Dictionary object array.
 * 
 * @author Monica Martinez
 * @version December 6, 2014
 *
 */
public class Words {
		
		public ArrayList<String> words;

		/**
		 * Class Constructor 
		 * 
		 * Creates array to be populated by permutations created.
		 * 
		 */
		public Words(){
			words = new ArrayList<String>();
		}
		
		
		/**
		 * Wrapper method for permutations method.
		 * 
		 * @param string
		 * @param dictCopy
		 */
		public void permutation(String string, Dictionary dictCopy){
			permutationRec("", string, dictCopy);
		}
		/**
		 * Recursively generates permutations of a given string. 
		 * Permutations are from length 2 to the number of elements in set.
		 * Checks to see if the permutation generated is also found in the dictCopy array.
		 * If yes then it sorts the words array and adds the permutation to it if 
		 * it is not already there (i.e. no duplicate values are added.)
		 * 
		 * @param prefix    Empty String
		 * @param string    The word to be permuted.
		 * @param dictCopy  Dictionary object that contains all permitted permutations.
		 * 
		 * 
		 */
		private void permutationRec(String prefix, String string, Dictionary dictCopy){
			
	        if (string.length() == 0) {
	            if(dictCopy.wordSearch(prefix) == true){
	  //  			Collections.sort(words);
	            	if(!words.contains(prefix))
	            		words.add(prefix);	
	            }  
	            
	            else if (!(dictCopy.prefixSearch(prefix) ))
	            	return;
        		
	        }
	        else {
	        	for (int i = 0; i < string.length(); i++){ 
	        		        		
	        		String next = prefix + string.charAt(i);
	        		String rest = string.substring(0,i)+ string.substring(i+1);
	        		
		            if(dictCopy.wordSearch(next) == true){
		    //			Collections.sort(words);
		            	if(!words.contains(next))
		            		words.add(next);
		            }
		            permutationRec(next, rest, dictCopy);
	        	}
	        }
		}
			
		/**
		 * Prints the elements of the permutations array.
		 */
		public void print(){
			for (int i = 0; i < words.size(); i++)
				System.out.println(words.get(i));
		
		}
	}




