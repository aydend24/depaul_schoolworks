package ds2;

import algs4.*;
import myalgs4.*;

/*
 * Yiming Deng
 * Student ID: 2121072
 * Email: ydeng24@depaul.edu
 */
public class FillAndSearchTime {

	public static void main(String[] args) {
		
		// (1) Get and read the file
		// ===============================================================
		// get the target file using algs4.StdIn.fromFile()
		StdIn.fromFile("data/tinyTale.txt");

		// read all strings using algs4.StdIn.readAllStrings()
		String[] words = StdIn.readAllStrings();
		// ===============================================================

		// (2) BinarySearchST to counts words
		// ===============================================================
		// declare a ST by algs4.BinarySearchST
		BinarySearchST<String, Integer> stbs = new algs4.BinarySearchST<>();

		// delare a stopwatch to time the loops
		Stopwatch sw1 = new Stopwatch();

		// delare a stopwatch to time the loop
		Stopwatch timer1 = new Stopwatch();

		// one loop to fill stbs with words and word counts
		for (String word : words) { // for-each loop
			if (!stbs.contains(word)) {
				stbs.put(word, 0);
			}
			int count = stbs.get(word);
			stbs.put(word, count + 1);
		}
		double time1 = timer1.elapsedTime();

		// ---------------------------------------------------------------
		// delare a stopwatch to time the loop
		Stopwatch timer01 = new Stopwatch();
		for (String word : stbs.keys()) {
			StdOut.println(word + ": " + stbs.get(word));

		}

		double time01 = timer01.elapsedTime();

		double total1 = sw1.elapsedTime();

		// ===============================================================

		// (3) BSTEssential to fill the tree
		// ===============================================================
		// declare a ST by myalgs4.BSTEssential
		BSTEssential<String, Integer> stbste = new myalgs4.BSTEssential<>();

		// delare a stopwatch to time the loops
		Stopwatch sw2 = new Stopwatch();

		// delare a stopwatch to time the loop
		Stopwatch timer2 = new Stopwatch();

		// one loop to fill stbste with words and word counts
		for (String word : words) {
			Integer count = stbste.get(word);
			if (count == null) {
				count = 0;
				stbste.put(word, 0);
			}
			stbste.put(word, count + 1);
		}
		double time2 = timer2.elapsedTime();

		// ---------------------------------------------------------------
		// delare a stopwatch to time the loop
		Stopwatch timer02 = new Stopwatch();
		for (String word : stbste.keys()) {
			StdOut.println(word + ": " + stbste.get(word));
		}

		double time02 = timer02.elapsedTime();
 
		double total2 = sw2.elapsedTime();

		// ===============================================================

		// (4) Print out the time
		// ===============================================================

		StdOut.println("===============================================================");

		// print the time of loop of computing the counts of words
		StdOut.printf("BinarySearchST time of computing counts: " + "%.2f seconds\n", time1);
		// print the time of loop of getting the words and their counts
		StdOut.printf("BinarySearchST time of getting words and counts: " + "%.2f seconds\n", time01);

		// print the time of the loops
		StdOut.printf("BinarySearchST time of the loops to count and get words: " + "%.2f seconds\n", total1);

		StdOut.println("===============================================================");
		// print the time of loop of filling the tree
		StdOut.printf("BSTEssential time of filling the tree : " + "%.2f seconds\n", time2);
		// print the time of loop of getting the words and their counts
		StdOut.printf("BSTEssential time of  getting words and counts: " + "%.2f seconds\n", time02);

		// print the time of the loops
		StdOut.printf("BSTEssential time of the loops to filling and getting: " + "%.2f seconds\n", total2);
		// ===============================================================

		// (5) Draw the tree
		// ===============================================================
		// draw the tree
//		stbste.drawTree();
	}

}
