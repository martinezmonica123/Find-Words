package project4;

import java.io.*;
//import java.io.FileNotFoundException;
import java.util.Scanner;


public class Game {


	/**
	 * Game Class - Drives the "Find Words" Game by creating objects of 
	 * a Dictionary and a Words Class in order to print all the possible permutations of a 
	 * given string (assuming each possible permutation matches an entry in a dictionary file provided by the user.)
	 * 
	 * @param args  Input File - Dictionary File
	 * @throws Exception 
	 * @throws FileNotFoundException
	 * 
	 */
	public static void main(String[] args) throws Exception {
		
		//command line input		
		Scanner fin = null;
		Scanner input = null;
		File dictFile = null;
		Dictionary dictFileCopy = null;
		
		//Check for file input
		try
		{
			if (args.length > 1 || args.length < 1) 			
				throw new Exception("ERROR: Invalid Arguement Length");
			
				
			dictFile = new File(args[0]);	
			
			//check if file input exists
			if (!dictFile.exists()) 
				throw new FileNotFoundException("ERROR: File Entered Does Not Exist");			
			
			//check if file input is readable
			else if (!dictFile.canRead())
				throw new Exception("ERROR: File Entered Cannot Be Read");
		
								
			try 
			{
				//Create scanner object
				fin = new Scanner(dictFile);
				//create Dictionary object
				dictFileCopy = new Dictionary();
		
				//populate Dictionary object array with items from scanner object
				while ( fin.hasNext() ) {
					String line = fin.nextLine();
					dictFileCopy.add(line);
				}
			
				System.out.println("-----------------------------------");
			
				//user input
				input = new Scanner(System.in);
				System.out.println("Enter a String of 2-10 Characters: ");
			
				//converts input to lower case 
				String strInput = input.next().toLowerCase();
			
				int len = strInput.length();
			
				//check for proper input string length
				if (len > 10 || len < 2)
					throw new Exception("Error: Too Many Characters Entered");
				else if (len < 2)
					throw new Exception("Error: Too Few Characters Entered");
				
				//check for non-letter characters
				for(int i = 0; i< len; i++){
					if (!Character.isLetter(strInput.charAt(i)))
						throw new Exception("Error: Input Contains Non-Letter Elements");
				}	

				System.out.println("-----------------------------------");
			
				//Words Object 
				Words stringToPermute = new Words();
			
				//Find Permutations
				stringToPermute.permutation(strInput, dictFileCopy);
			
				//Print Permutations
				stringToPermute.print();
				
				
			}
			catch (Exception e)
			{
				System.err.println(e.getMessage());
				System.exit(0);
			}
					
			
			finally 
			{
				fin.close();
				input.close();
			}
					
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
			System.exit(0);
		}
			
		catch (Exception e1) 
		{
			System.out.println(e1.getMessage());
			System.exit(0);
		}
	}
}


