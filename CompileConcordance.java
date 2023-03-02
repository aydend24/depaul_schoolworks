package ds2;

import java.util.*;
import algs4.*;
import myalgs4.*;

/*
 * Yiming Deng
 * Student ID: 2121072
 * Email: ydeng24@depaul.edu
 */

public class CompileConcordance {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Declare an object of type myalgs4.AVLTreeST to use as the concordance
		AVLTreeST<String, ArrayList<Integer>> concordance = new myalgs4.AVLTreeST<>();
		StdOut.println("Please enter the pathname of a text file below (e.g. data/tinyTale.txt): ");
		String path = StdIn.readString();
		StdIn.fromFile(path);
		
		// For quick test
//		StdIn.fromFile("data/tinyTale.txt");
		
		int lineCount = 1;
		
		while(!StdIn.isEmpty()) {
			
			String line = StdIn.readLine();
			String[] words = line.split("\\s+");
			
			for(String word: words) {
				
				// Use an ArrayList object for the list for each word.
				ArrayList<Integer> tmp = new ArrayList<>();
				
				if(!concordance.contains(word)) {
					concordance.put(word, tmp);
				}
				
				// When the word has already existed in the avl tree
				// Use an ArrayList object for the list for each word.
				ArrayList<Integer> lineNum = new ArrayList<>();
				
				// assign lineNum's address to the concordance's key(word)
				lineNum = concordance.get(word);
				
				// update the key
				lineNum.add(lineCount);
				concordance.put(word, lineNum);
			}
			// update the count of lines
			lineCount ++;
		}
		
		for (String word : concordance.keys()) {
			StdOut.println(word + "          " + concordance.get(word));
		}

	}

}
