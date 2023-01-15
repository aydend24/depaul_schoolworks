package algs11.HW;

import stdlib.StdOut;

class HW2 {

	/**
	 * As a model, here is a minValue function, both iteratively and recursively
	 */
	/** iterative version */
	public static double minValueI(double[] list) {

		double minSoFar = list[0];

		int i = 1;

		while (i < list.length) {

			if (list[i] < minSoFar)

				minSoFar = list[i];

			i++;

		}

		return minSoFar;

	}

	/** recursive version */
	public static double minValue(double[] list) {

		return minValueHelper(list, 1, list[0]);

	}

	static double minValueHelper(double[] list, int i, double minSoFar) {

		if (i < list.length) {

			if (list[i] < minSoFar) {

				return minValueHelper(list, i + 1, list[i]);

			} else {

				return minValueHelper(list, i + 1, minSoFar);

			}

		} else {

			return minSoFar;

		}
	}

	/**
	 * PROBLEM 1: Translate the following sum function from iterative to recursive
	 */
	public static double sumI(double[] a) {

		double result = 0.0;

		int i = 0;

		while (i < a.length) {

			result = result + a[i];

			i = i + 1;

		}

		return result;

	}

	/** recursive version */
	public static double sum(double[] a) {

		return sumHelper(a, 0, 0);

	}

	public static double sumHelper(double[] arr, double tally, int i) {

		// TODO
		if (arr.length == 0) { // There is null or no element.

			return 0.0;

		} else if (arr.length - 1 == i) { // There is only one element.

			return arr[i];

		} else { // There is more than one element

			return arr[i] + sumHelper(arr, tally, i + 1);

		}

	}

	/** PROBLEM 2: Do the same translation for this in-place reverse function */
	public static void reverseI(double[] a) {

		int N = a.length;

		int i = 0;

		while (i < N / 2) {

			double lo = a[i];

			double hi = a[N - 1 - i];

			a[i] = hi;

			a[N - i - 1] = lo;

			i = i + 1;

		}

	}

	/** recursive version */
	public static void reverse(double[] a) {

		reverseHelper(a, 0);

	}

	public static void reverseHelper(double[] a, int i) {

		// TODO
		int N = a.length;

		if (i >= N / 2) { // Element swapping will stop before the median element of the array

			return;

		} else {

			reverseHelper(a, i + 1);

			double x = a[i]; // Save the former value of a[i]

			a[i] = a[N - 1 - i]; // Update the value of a[i] by swapping it with the value of element in the
									// symmetric position in the Array

			a[N - 1 - i] = x; // Update the value of a[N-a-i] (the value of the element in the symmetric
								// position to a[i] in the Array) with x (the value of a[i] before being
								// updated).

		}

	}

	/**
	 * PROBLEM 3: Run runTerrible for one hour. You can stop the program using the
	 * red "stop" square in eclipse. Fill in the OUTPUT line below with the numbers
	 * you saw LAST --- edit the line, replacing the two ... with what you saw:
	 * 
	 * OUTPUT: terribleFibonacci(54)=86267571272
	 *
	 * Comment: the code uses "long" variables, which are like "int", but bigger.
	 * It's because fibonacci numbers get really big really fast.
	 */
	public static void runTerrible() {

		for (int N = 0; N < 100; N++)

			StdOut.printf("terribleFibonacci(%2d)=%d\n", N, terribleFibonacci(N));

	}

	public static long terribleFibonacci(int n) {

		if (n == 0)

			return 0;

		if (n == 1)

			return 1;

		return terribleFibonacci(n - 1) + terribleFibonacci(n - 2);

		// TODO
		/*
		 * Comment OUTPUT: terribleFibonacci(55)=139583862445
		 */
	}

	/**
	 * PROBLEM 4: The implementation of terribleFibonacci is TERRIBLE! Write a more
	 * efficient version of fibonacci which computes each fibonacci number between 0
	 * and n at most once.
	 * 
	 * Comment: You will want to use a local variable of type "long" rather than
	 * type "int", for the reasons discussed above.
	 */
	public static void runFibonacci() {

		for (int N = 0; N < 100; N++)

			StdOut.printf("fibonacci(%2d)=%d\n", N, fibonacci(N));

	}

	public static long fibonacci(int n) {

		// TODO
		if (n <= 1) {

			return n;

		} else {

			int[] fibonacci_cache = new int[n + 1]; // This array is for temporarily saving the historical values of
													// this sequence, and it's being updated till before the last value
													// of this sequence.

			fibonacci_cache[1] = 1;

			for (int i = 2; i <= n; i++) { // Iterate the array

				fibonacci_cache[i] = fibonacci_cache[i - 1] + fibonacci_cache[i - 2]; // Update the cache

			}

			return fibonacci_cache[n]; // Return the latest value of this cache array

		}

		// TODO
	}

	public static void main(String[] args) {

		// runTerrible
		// runTerrible(); // Sample OUTPUT: terribleFibonacci(55)=139583862445

		// runFibonacci
		runFibonacci(); // Sample OUTPUT: fibonacci(99)=-889489150

	}

}
