package ds2;

import algs4.*;
import java.util.*;

/*
 * Yiming Deng
 * Student ID: 2121072
 * Email: ydeng24@depaul.edu
 */

public class TranslateCodons {
	private static void codonsTranslator(String fileToBeTranslated, String referenceFile) {
		// Initialize a new BST to store the codons
		BST<String, String> codonsTable = new BST<>();

		// Import the reference file
		StdIn.fromFile(referenceFile);

		// Get codons from the reference file
		while (!StdIn.isEmpty()) {
			String line = StdIn.readLine();
			String[] codonsToLetters = line.split("\\s+");

			String dnaSet = codonsToLetters[0];
			String aminoAcid = codonsToLetters[1];

			// Fill the tree
			codonsTable.put(dnaSet, aminoAcid);
		}

		// Import the file to be translated
		StdIn.fromFile(fileToBeTranslated);
		while (!StdIn.isEmpty()) {
			String line = StdIn.readLine();
			String[] words = line.split("\\s+");

			String speciesName = words[0] + " " + words[1];
			String dnaSequence = words[2];

			// Initialize a list to store the DNA sequence and split the string of DNA
			// sequence at every 3rd character
			List<String> codonList = Arrays.asList(dnaSequence.split("(?<=\\G.{3})"));

			// Print out the Species Name as the first line
			StdOut.println(speciesName);

			// Print out the DNA sets as the second line
			for (String dnaSet : codonList) {
				StdOut.print(dnaSet + " ");
			}

			// A blank line
			StdOut.println();

			// Print out sequence of the abbreviations for amino acids as the third line
			for (int i = 0; i < codonList.size(); i++) {
				if (i < codonList.size() - 1) {
					// Format the output sequence
					StdOut.print(codonsTable.get(codonList.get(i)) + "-");
				}
				if (i == codonList.size() - 1) {
					// Format the output sequence
					StdOut.print(codonsTable.get(codonList.get(i)));
				}
			}

			// A blank line
			StdOut.println("\n");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Define the file as reference
		String referenceFile = "data/codonto3letteraa.txt";
		// Define the file to be translated
		String fileToBeTranslated = "data/smallsequences.txt";
		codonsTranslator(fileToBeTranslated, referenceFile);

	}
}
