// Exercise 1.3.33
package algs13;

import stdlib.*;
import java.util.NoSuchElementException;

/**
 * This is a skeleton file for your homework. Edit the sections marked TODO. You
 * may also edit the function "main" to test your code.
 *
 * You should not need to add any new functions or fields.
 * 
 * You must not add static variables. As always, you must not change the
 * declaration of any method. Do not change any of the methods I have provided,
 * such as "toString" and "check".
 */
public class MyDeque {

	Node first = null;
	Node last = null;
	int N = 0;

	static class Node {
		public Node() {
		}

		public int item;
		public Node next;
		public Node prev;
	}

	public boolean isEmpty() {
		// TODO: check if the input deque is empty
		return size() == 0; // size ==0 then return true, or return false
	}

	public int size() {
		// TODO: get the size of the input deque
		return N; // N is the size of deque
	}

	public void pushLeft(int item) {
		// TODO: add a new node in front of the original first node

		Node node = new Node(); // declare a new node
		node.item = item; // assign an input item to this new node

		// base case
		if (isEmpty()) { // if the original deque is empty
			first = node;
			last = node;
			// the new node will be both the first and the last node in this deque
			N++; // maintain invariant N

			return; // end
		}

		// if the original deque isn't empty
		node.prev = null; // let null be the prev of new node
		node.next = first; // let the original first node be the next of the new node
		first.prev = node; // let the new node be the prev of the original first node
		first = node; // CHANGE the ADDRESS of first - the new node was placed in the first position
		N++; // maintain invariant N
	}

	public void pushRight(int item) {
		// TODO: add a new node behind of the original last node

		Node node = new Node(); // declare a new node
		node.item = item; // assign an input item to this new node

		// base case
		if (isEmpty()) { // if the original deque is empty
			first = node;
			last = node;
			// the new node will be both the first and the last node in this deque
			N++;
			// maintain invariant N
			return; // end
		}

		// if the original deque isn't empty
		node.next = null; // let null be the next of new node
		node.prev = last; // let the original last node be the prev of the new node
		last.next = node; // let the new node be the next of the original last node
		last = node; // CHANGE the ADDRESS of last - the new node was placed in the last position
		N++; // maintain invariant N
	}

	public int popLeft() {
		// TODO: delete the first node of this deque

		// base case
		if (isEmpty()) // if the original deque is empty
			throw new NoSuchElementException(); // throw designated error

		// save the item of the node to be deleted as int x
		int x = first.item;

		// base case
		if (N == 1) { // there is only one node in this deque
			first = null;
			last = null;
			// let both first and last become null

		} else { // there are more than one node in this deque
			first = first.next; // let the original first.next become first
			first.prev = null; // let first.prev point to null
		}
		N--; // maintain invariant N

		return x;
	}

	public int popRight() {
		// TODO: delete the last node of this deque

		if (isEmpty()) // if the original deque is empty
			throw new NoSuchElementException(); // throw designated error

		// save the item of the node to be deleted as int x
		int x = last.item;

		// base case
		if (N == 1) { // there is only one node in this deque
			first = null;
			last = null;
			// let both first and last become null

		} else { // there are more than one node in this deque
			last.prev.next = null; // let the original last.prev.next point to null
			last = last.prev; // let the original last.prev become last
		}
		N--; // maintain invariant N
		return x;
	}

	// exercise 1.3.47
	//
	// The concat method should take the Nodes from "that"
	// after execution, "that" should be empty.
	// See the tests in the main program.
	//
	// This method should create no new Nodes;
	// therefore it should not call pushLeft or pushRight.
	// Do not use a loop or a recursive definition.
	//
	public void concat(HW5_MyDeque that) {
		// TODO -- see comments above

		// save the size of the edited deque as int L
		int L = that.size(); //

		// base case
		if (isEmpty()) { // if the original deque is empty
			first = that.first; // the first of edited deque is the first
			last = that.last; // the last of edited deque is the last
			N = L; // the size of the concat deque is the size of the edited one
			return;
		}
		if (that.isEmpty()) { // if the edited deque is empty
			return; // NOTHING of the original one will be changed
		}

		// both of the original and the edited deques are not empty
		that.first.prev = last;
		// the first.prev of the edited deque should point to the last of the original
		// one
		last.next = that.first;
		// As well, the last.next of the original deque should point to the first of the
		// edited one
		last = that.last;
		// the last of the concat deque should be the last of the edited one

		N += L; // size of the concat deque is the sum of these 2 deques
		
		// after execution, "that" should be empty.
		that.first = null; 
		that.last = null;
		that.N = 0;

	}

	// Delete should delete and return the kth element from the left.
	// See the tests in the main program.
	//
	// This method should create no new Nodes;
	// therefore it should not call pushLeft or pushRight.
	// You can use a loop or a recursive definition here.
	//
	public int delete(int k) {
		// TODO -- see comments above
		int x; // save k.item as int x

		// base case: invalid k value
		if (k < 0 || k >= N) {
			throw new NoSuchElementException(); // throw designated error
		}

		// delete first or last
		else if (k == 0) { // delete first
			x = first.item; // k.item is the first.item
			if (N == 1) { // there is only one node in this deque
				first = null;
				last = null;
			} else {
				first = first.next;
				first.prev = null;
			}
			N--;
			
		} else if (k == N - 1) { // delete last
			x = last.item; // k.item is the last.item
			if (N == 1) { // there is only one node in this deque
				first = null;
				last = null;
			} else { 
				last.prev.next = null;
				last = last.prev;
			}
			N--;

			// main implement
		} else {
			Node curr = first; // declare a temporary pointer to traverse the list

			for (int i = 0; i < k - 1; i++) { // traverse the deque to k-1
				curr = curr.next; 
			}

			x = curr.next.item; // k.item is the curr.next.item
			
			// delete k
			curr.next = curr.next.next; 
			curr.next.prev = curr;

			N--; // maintain invariant N
		}
		return x;
	}

	public String toString() {
		if (first == null)
			return "[]";
		StringBuilder sb = new StringBuilder("[");
		sb.append(first.item);
		for (Node cursor = first.next; cursor != null; cursor = cursor.next) {
			sb.append(" ");
			sb.append(cursor.item);
		}
		sb.append("]");
		return sb.toString();
	}

	private void checkInvariants() {
		if (N < 0)
			throw new Error();
		if (N == 0) {
			if (first != null || last != null)
				throw new Error();
		} else {
			if (first == null || last == null)
				throw new Error();
		}
		if (N > 0) {
			Node prev = null;
			Node current = first;
			for (int i = 0; i < N; i++) {
				// StdOut.println ("checking " + current.item);
				if (current.prev != prev)
					throw new Error();
				prev = current;
				current = current.next;
			}
			if (current != null)
				throw new Error();
			Node next = null;
			current = last;
			for (int i = 0; i < N; i++) {
				// StdOut.println ("checking " + current.item);
				if (current.next != next)
					throw new Error();
				next = current;
				current = current.prev;
			}
			if (current != null)
				throw new Error();
		}
	}

	private void check(String expected) {
		checkInvariants();
		if (expected != null) {
			if (!expected.equals(this.toString()))
				throw new Error("Expected \"" + expected + "\", got \"" + this + "\"");
		}
		StdOut.println(this);
	}

	private void check(int iExpected, int iActual, String expected) {
		if (iExpected != iActual)
			throw new Error("Expected \"" + iExpected + "\", got \"" + iActual + "\"");
		check(expected);
	}

	public static void main(String args[]) {
		// Here are some tests to get you started.
		// You can edit this all you like.
		HW5_MyDeque d1, d2, d3;
		Integer k;

		////////////////////////////////////////////////////////////////////
		// push/pop tests
		////////////////////////////////////////////////////////////////////
		d1 = new HW5_MyDeque();
		d1.pushLeft(11);
		d1.check("[11]");
		d1.pushLeft(12);
		d1.check("[12 11]");
		d1.pushLeft(13);
		d1.check("[13 12 11]");
		k = d1.popLeft();
		d1.check(13, k, "[12 11]");
		k = d1.popLeft();
		d1.check(12, k, "[11]");
		k = d1.popLeft();
		d1.check(11, k, "[]");

		d1 = new HW5_MyDeque();
		d1.pushRight(11);
		d1.check("[11]");
		d1.pushRight(12);
		d1.check("[11 12]");
		d1.pushRight(13);
		d1.check("[11 12 13]");
		k = d1.popRight();
		d1.check(13, k, "[11 12]");
		k = d1.popRight();
		d1.check(12, k, "[11]");
		k = d1.popRight();
		d1.check(11, k, "[]");

		////////////////////////////////////////////////////////////////////
		// test exceptions
		////////////////////////////////////////////////////////////////////
		try {
			d1.popLeft();
			throw new Error("Expected exception");
		} catch (NoSuchElementException e) {
		}
		try {
			d1.popRight();
			throw new Error("Expected exception");
		} catch (NoSuchElementException e) {
		}

		////////////////////////////////////////////////////////////////////
		// concat tests (and more push/pop tests)
		////////////////////////////////////////////////////////////////////
		d1 = new HW5_MyDeque();
		d1.concat(new HW5_MyDeque());
		d1.check("[]");
		d1.pushLeft(11);
		d1.concat(new HW5_MyDeque());
		d1.check("[11]");

		d1 = new HW5_MyDeque();
		d2 = new HW5_MyDeque();
		d2.pushLeft(11);
		d1.concat(d2);
		d1.check("[11]");

		d1 = new HW5_MyDeque();
		for (int i = 10; i < 15; i++) {
			d1.pushLeft(i);
			d1.checkInvariants();
		}
		for (int i = 20; i < 25; i++) {
			d1.pushRight(i);
			d1.checkInvariants();
		}
		d1.check("[14 13 12 11 10 20 21 22 23 24]");
		d2 = new HW5_MyDeque();
		d1.concat(d2);
		d1.check("[14 13 12 11 10 20 21 22 23 24]");
		d2.check("[]");

		for (int i = 30; i < 35; i++) {
			d2.pushLeft(i);
			d2.checkInvariants();
		}
		for (int i = 40; i < 45; i++) {
			d2.pushRight(i);
			d2.checkInvariants();
		}
		d2.check("[34 33 32 31 30 40 41 42 43 44]");

		d3 = new HW5_MyDeque();
		d2.concat(d3);
		d2.check("[34 33 32 31 30 40 41 42 43 44]");
		d3.check("[]");

		d1.concat(d2);
		d1.check("[14 13 12 11 10 20 21 22 23 24 34 33 32 31 30 40 41 42 43 44]");
		d2.check("[]");
		for (int i = 0; i < 20; i++) {
			d1.popLeft();
			d1.checkInvariants();
		}

		////////////////////////////////////////////////////////////////////
		// delete tests
		////////////////////////////////////////////////////////////////////
		d1 = new HW5_MyDeque();
		d1.pushLeft(11);
		d1.delete(0);
		d1.check("[]");
		for (int i = 10; i < 20; i++) {
			d1.pushRight(i);
			d1.checkInvariants();
		}
		d1.delete(0);
		d1.check("[11 12 13 14 15 16 17 18 19]");
		d1.delete(8);
		d1.check("[11 12 13 14 15 16 17 18]");
		d1.delete(4);
		d1.check("[11 12 13 14 16 17 18]");
		d1.delete(0);
		d1.check("[12 13 14 16 17 18]");
		d1.delete(0);
		d1.check("[13 14 16 17 18]");
		d1.delete(0);
		d1.check("[14 16 17 18]");
		d1.delete(0);
		d1.check("[16 17 18]");
		d1.delete(0);
		d1.check("[17 18]");
		d1.delete(0);
		d1.check("[18]");
		d1.delete(0);
	}
}
