package ds2;

import algs4.*;

/*
 * Yiming Deng
 * Student ID: 2121072
 * Email: ydeng24@depaul.edu
 */

public class A5TestHT {

	public static void averageClusterSize(int capacity, String filePath) {
		A5LPHT<String, Integer> A5LPHT = new A5LPHT<>(capacity);

		// use the algs4.StdIn class for input
		StdIn.fromFile(filePath);

		// fill each with word counts from data/tale.txt
		while (!StdIn.isEmpty()) {
			String word = StdIn.readString();

			// fill A5LPHT
			if (A5LPHT.contains(word)) {

				A5LPHT.put(word, A5LPHT.get(word) + 1);
			} else {
				A5LPHT.put(word, 1);
			}

		}

		// print the average size of the clusters in the A5LPHT table
		int averageSizeOfTheClusters = A5LPHT.averageClusterSize();
		StdOut.print(averageSizeOfTheClusters);

	}

	public static void averageChainLength(int capacity, String filePath) {
		A5SCHT<String, Integer> A5SCHT = new A5SCHT<>(capacity);

		// use the algs4.StdIn class for input
		StdIn.fromFile(filePath);

		// fill each with word counts from data/tale.txt
		while (!StdIn.isEmpty()) {
			String word = StdIn.readString();

			// fill A5SCHT
			if (A5SCHT.contains(word)) {

				A5SCHT.put(word, A5SCHT.get(word) + 1);
			} else {
				A5SCHT.put(word, 1);
			}

		}

		// print the average chain length in the A5SCHT table
		int averageChainLength = A5SCHT.averageChainLength();
		StdOut.print(averageChainLength);
	}

	public static void main(String[] args) {
		// define the filePath
		String filePath = "data/tale.txt";

		int A5LPHTcapacity1 = 20000;
		StdOut.print("The average size of the clusters in the A5LPHT table (capacity = 20000) is: ");
		averageClusterSize(A5LPHTcapacity1, filePath);
		StdOut.println();

		StdOut.println();
		int A5SCHTcapacity1 = 5000;
		StdOut.print("The average chain length in the A5SCHT table (capacity = 5000) is: ");
		averageChainLength(A5SCHTcapacity1, filePath);
		StdOut.println();

		StdOut.println();
		int A5LPHTcapacity2 = 15000;
		StdOut.print("The average size of the clusters in the A5LPHT (capacity = 15000) table is: ");
		averageClusterSize(A5LPHTcapacity2, filePath);
		StdOut.println();

		StdOut.println();
		int A5SCHTcapacity2 = 2500;
		StdOut.print("The average chain length in the A5SCHT table (capacity = 2500) is: ");
		averageChainLength(A5SCHTcapacity2, filePath);
	}
}
