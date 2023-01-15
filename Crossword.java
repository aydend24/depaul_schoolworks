package algs11.HW;

import stdlib.StdOut;

/*A square is labeled with a positive number if and only if
 * 1. The square is white and 
 * 2. The square does not have a white square immediately above it, 
 * or it does not have a white square immediately to its left, or both.
 * The squares identified by these criteria are labeled with consecutive numbers in row-major order, starting at 1. 
 * 
 * Info above means:
 * 1. The square be labeled is on the top row
 * 2. Or the square in the leftmost column
 * 3. Or the square is on the right side of a black square
 * 4. Or the square is below a black square or now white square above it
 * 5. And ALL the squares to be labeled are WHITE
 */

public class Crossword {

	private Square[][] puzzle; 

	public Crossword(boolean[][] blackSquares) { 
		puzzle = new Square[blackSquares.length][blackSquares[0].length]; 
		/* PART(b)
		 * If the square:
		 * 1. Is black: Square(true, 0) // blackSquares[i][j]
		 * 2. Is not black: Square(false, x) // x != 0: square is labeled, else: x isn'tlabeled // tobeLabeled(i,j,blackSquares) or other white squares
		 */
		// FINISH
		int num = 1; // number to be labeled in the square

		for (int i = 0; i < puzzle.length; i++) {
			// Iterate rows of balckSquares

			for (int j = 0; j < puzzle[0].length; j++) {
				// Iterate columns of balckSquares

				if (blackSquares[i][j]) { // the Square is Black

					puzzle[i][j] = new Square(true, 0); // isBlack = true, label = 0 (not to be labeled)

				} else if (tobeLabeled(i, j, blackSquares)) { // the Square is to be labeled

					puzzle[i][j] = new Square(false, num); // isBlack = false (labeled square is not black), label = num

					num++;

				} else {

					puzzle[i][j] = new Square(false, 0);
					// Squares that is not Black and is not to be labeled, should be white
					// isBlack = false, label = 0

				}

			}

		}

	}

	private boolean tobeLabeled(int r, int c, boolean[][] blackSquares) { // helper method: tell if the square should be
																			// labeled
		// TODO
		// PART (a)
		/*
		 * Is the squares should be labeled - Yes: return true; // is white and // is on
		 * the top row || in the leftmost column || no white square above itself ||
		 * black square to the left - No : return false;
		 */

		// Exact Conditions to tell whether the squares should be labeled
		if (blackSquares[r][c])
			return false; // square is black, so its boolean is false (ALL the squares to be labeled are
							// WHITE)
		if (r == 0 || c == 0)
			return true; // The square be labeled is on the top row or in the leftmost column
		if (blackSquares[r - 1][c])
			return true; // The square is on the right side of a black square
		if (blackSquares[r][c - 1])
			return true; // The square is below a black square or now white square above it

		// Squares that do not match the conditions above should not be labeled - their
		// boolean are false
		return false;
	}

	public void print() {
		for (int i = 0; i < puzzle.length; i++) { 
			for (int j = 0; j < puzzle[0].length; j++) 
				StdOut.print(puzzle[i][j].getLabel());
			StdOut.println();
		}

	}

	// TEST
	public static void main(String[] args) {

		// TODO Auto-generated method stub
		boolean[][] test = new boolean[7][9]; // INPUT a puzzle with size of 7*9 (7 rows and 9 columns)

		// Top Row
		test[0][0] = true; // Square[0][0] (located in Row 0, Column 0) is Black
		test[0][3] = true; // Square[0][3] (located in Row 0, Column 3) is Black
		test[0][4] = true; // Square[0][4] (located in Row 0, Column 4) is Black
		test[0][5] = true; // Square[0][5] (located in Row 0, Column 5) is Black
		// Row 0 OUTPUT: 0 1 2 0 0 0 3 4 5

		// Row 1
		test[1][4] = true; // Square[1][4] (located in Row 1, Column 4) is Black
		// Row 1 OUTPUT: 6 0 0 7 0 8 0 0 0

		for (int j = 6; j < 9; j++)
			test[2][j] = true;
		// Square[2][6] (located in Row 2, Column 6) is Black
		// Square[2][7] (located in Row 2, Column 7) is Black
		// Square[2][8] (located in Row 2, Column 8) is Black
		// Row 2 OUTPUT: 9 0 0 0 10 0 0 0 0

		test[3][2] = true; // Square[3][2] (located in Row 3, Column 2) is Black
		test[3][6] = true; // Square[3][6] (located in Row 3, Column 6) is Black
		// Row 3 OUTPUT: 11 0 0 12 0 0 0 13 14

		for (int j = 0; j < 3; j++)
			test[4][j] = true;
		// Square[4][0] (located in Row 4, Column 0) is Black
		// Square[4][1] (located in Row 4, Column 1) is Black
		// Square[4][2] (located in Row 4, Column 2) is Black
		// Row 4 OUTPUT: 0 0 0 15 0 0 16 0 0

		test[5][4] = true; // Square[5][4] (located in Row 5, Column 4) is Black
		// Row 5 OUTPUT: 17 18 19 0 0 20 0 0 0

		test[6][3] = true; // Square[6][3] (located in Row 6, Column 3) is Black
		test[6][4] = true; // Square[6][4] (located in Row 6, Column 4) is Black
		test[6][5] = true; // Square[6][5] (located in Row 6, Column 5) is Black
		test[6][8] = true; // Square[6][6] (located in Row 6, Column 6) is Black
		// Row 6 OUTPUT: 21 0 0 0 0 0 22 0 0

		Crossword c = new Crossword(test);

		c.print(); // Print the test puzzle
		// OUTPUT
		// 0  1  2  0  0  0  3  4  5
		// 9  0  0  0  10 0  0  0  0
		// 11 0  0  12 0  0  0  13 14
		// 0  0  0  15 0  0  16 0  0
		// 17 18 19 0  0  20 0  0  0
		// 21 0  0  0  0  0  22 0  0
		
	}

}
