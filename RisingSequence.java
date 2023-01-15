package ds2;

import algs4.*;


public class RisingSequence {

	public static void main(String[] args) {
		// Input the file
		StdIn.fromFile("src/data/tinyTale.txt");
		
		// Create a new queue
		Queue<String> sequence = new Queue<String>();
		
		while (!StdIn.isEmpty()) {
			// Read lines
			String line = StdIn.readLine();
			String[] words = line.split("\\s+");
			
			// Enqueue
			for(String word : words) {
				sequence.enqueue(word);
			}
			
			// Create a new string to save previous word
			String prev = "";
			
			// If the precious word is the word that was just printed
			while(!sequence.isEmpty()) {
				// Create a new string to save current word
				String curr = sequence.dequeue();
				// If the current word to be dequeued is alphabetically larger than the previous word
				if (curr.compareTo(prev) > 0) {
					// Print the current word
					StdOut.println(curr);
					// Update the previous word
					prev = curr; 
				}
			}
			StdOut.println("");
			
//			// If the precious word is the word that was just dequeued
//			while(!sequence.isEmpty()) {
//				// Create a new string to save current word
//				String curr = sequence.dequeue();
//				// If the current word to be dequeued is alphabetically larger than the previous word
//				if (curr.compareTo(prev) > 0) {
//					// Print the current word
//					StdOut.println(curr);
//				}
//			// Update the previous word
//				prev = curr; 
//			}
//			StdOut.println("");
		}

	}

}