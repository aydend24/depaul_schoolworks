package algs13;

import algs13.MyLinked.Node;
import stdlib.*;

public class MyLinked {
	static class Node { // implement a Node abstraction
		public Node() {
		}

		public double item;
		public Node next;
	}

	int N; // how does N work? -- is N the size of the linked list?
	Node first; 

	public MyLinked() {
		first = null;
		N = 0;
		checkInvariants(); // Why check invariants?
	}

	private void myassert(String s, boolean b) { // b is MyLinked
		if (!b) // if not b
			throw new Error("Assertion failed: " + s);
	}

	private void checkInvariants() {
		myassert("Empty <==> first==null", (N == 0) == (first == null));
		Node x = first;
		for (int i = 0; i < N; i++) {
			if (x == null) {
				throw new Error("List too short!");
			}
			x = x.next;
		}
		myassert("EndOfList == null", x == null);
	}

	public boolean isEmpty() {
		return first == null;
	}

	public int size() {
		return N;
	}

	public void add(double item) {
		Node newfirst = new Node(); // create a new node
		newfirst.item = item; // set the item property for new node
		newfirst.next = first; // make the new node's next node point to first node of the input linked list
		first = newfirst; //make the first node of the linked list as new node, then the original first node become the second node of the original linked list
		N++; // maintain the invariant
	}

	/*
	 * TODO HW4 Exercises
	 */

	// return Double.NEGATIVE_INFINITY if the linked list is empty
	public double max() {
		return max(first);
	}

	private static double max(Node x) {
		// base case
		double maxNum = Double.NEGATIVE_INFINITY;
		while (x != null) {
			maxNum = Math.max(maxNum, x.item);
			x = x.next;
		}

		// TODO 1.3.27
		return maxNum;
	}

	public double maxRecursive() {
		return maxRecursive(first, Double.NEGATIVE_INFINITY);
	}

	private static double maxRecursive(Node x, double result) {
		// TODO 1.3.28
		if(x==null) { // if x is null, then the max is the input result
			return result;
		}
		return maxRecursive(x.next, Math.max(x.item, result));
		// Math.max(a,b) is used for compare a and b, and return the larger one;
	}

	// delete the kth element
	public void delete(int k) {
		if (k < 0 || k >= N)
			throw new IllegalArgumentException();
		// TODO 1.3.20
		if (k == 0) { // the element intended to be deleted is the first one
			first = first.next; // the first node is deleted
			N--; // maintain invariant
			return; // end
		}
		Node prev = first; // set a start pointer
		for (int i = 0; i < k - 1; i++) { // traverse the list
			prev = prev.next; // move forward the pointer
		}
		prev.next = prev.next.next; // while i = k-1, skip the kth node, then move the prev.next point to the
									// prev.next.next of the original list
		N--; // maintain invariant
		checkInvariants();
		return; // end
	}

	// reverse the list "in place"... without creating any new nodes
	public void reverse() {
		// base case
		if (first == null || first.next == null)
			return; // a linked list with 1 or 0 node

		// reverse a linked list with at least 2 nodes
		Node curr = first; // head != null
		Node temp = first.next; // head.next != null
		Node prev = null;
		curr.next = prev;
		// reverse the FIRST pointer from curr1->curr1.next to curr1->null
		// make the first node become the last node

		while (temp != null) { // while temp = null (curr.next = null) end the loop
			prev = curr; // move prev forward to next node(the original curr)
			curr = temp; // move curr forward to next node(the original curr.next)
			temp = curr.next; // move curr.next forward to next node(the original curr.next.next)
			curr.next = prev; // reverse the former pointer from curr.former->curr.former.next to
								// curr.former->curr.former.prev
		}
		first = curr;
	}

	// remove
	public void remove(double item) {
		// TODO 1.3.26
		// base case
		if(first == null) return; // express exit
		if(first.item == item) { // the target of delete is located at the first node
			first = first.next;
			N--;
			remove(item);
			return;
		}
		Node curr = first; // set a pointer 
		while(curr.next != null) { // make sure that the curr.next is not null
			if(curr.next.item == item) { // match the item
				curr.next =curr.next.next; 
				N--; 
			} else {
				curr = curr.next; //traverse the list
			}
		}			
		checkInvariants();
	}

	private static void print(String s, MyLinked b) {
		StdOut.print(s + ": ");
		for (Node x = b.first; x != null; x = x.next)
			StdOut.print(x.item + " ");
		StdOut.println();
	}

	private static void print(String s, MyLinked b, double i) {
		StdOut.print(s + ": ");
		for (Node x = b.first; x != null; x = x.next)
			StdOut.print(x.item + " ");
		StdOut.println(": " + i);
	}

	/*
	 * TODO TEST Above
	 */
	// TEST Max
	private static void testMax() {
		MyLinked b = new MyLinked();
		print("empty", b, b.max());
		b.add(-1);
		print("singleton", b, b.max());
		b.add(-2);
		b.add(-3);
		b.add(-4);
		print("at end", b, b.max());
		b.add(5);
		print("at beginning", b, b.max());
		b.add(3);
		b.add(2);
		b.add(4);
		print("in the middle", b, b.max());
	}

	// TEST MaxRecursive
	private static void testMaxRecursive() {
		MyLinked b = new MyLinked();
		print("empty", b, b.maxRecursive());
		b.add(-1);
		print("singleton", b, b.maxRecursive());
		b.add(-2);
		b.add(-3);
		b.add(-4);
		print("at end", b, b.maxRecursive());
		b.add(5);
		print("at beginning", b, b.maxRecursive());
		b.add(3);
		b.add(2);
		b.add(4);
		print("in the middle", b, b.maxRecursive());
	}

	// TEST Delete
	private static void testDelete() {
		MyLinked b = new MyLinked();
		b.add(1);
		print("singleton", b);
		b.delete(0); 
		print("deleted", b);
		for (double i = 1; i < 13; i++) {
			b.add(i);
		}
		print("bigger list", b);
		b.delete(0);
		print("deleted at beginning", b);
		b.delete(10);
		print("deleted at end", b);
		b.delete(4);
		print("deleted in middle", b);
	}

	// TEST Reverse
	private static void testReverse() {
		MyLinked b = new MyLinked();
		b.reverse();
		print("reverse empty", b);
		b.add(1);
		print("singleton", b);
		b.reverse();
		print("reverse singleton", b);
		b.add(2);
		print("two", b);
		b.reverse();
		print("reverse two", b);
		b.reverse();
		print("reverse again", b);
		for (double i = 3; i < 7; i++) {
			b.add(i);
			b.add(i);
		}
		print("bigger list", b);
		b.reverse();
		print("reversed", b);
	}

	// TEST Remove
	private static void testRemove() {
		MyLinked b = new MyLinked();
		b.remove(4);
		print("removed 4 from empty", b);
		b.add(1);
		b.remove(4);
		print("removed 4 from singelton", b);
		b.remove(1);
		print("removed 1 from singelton", b);
		for (double i = 1; i < 5; i++) {
			b.add(i);
			b.add(i);
		}
		for (double i = 1; i < 5; i++) {
			b.add(i);
			b.add(i);
			b.add(i);
			b.add(i);
			b.add(i);
		}
		print("longer list", b);
		b.remove(9);
		print("removed all 9s", b); // does nothing
		b.remove(3);
		print("removed all 3s", b);
		b.remove(1);
		print("removed all 1s", b);
		b.remove(4);
		print("removed all 4s", b);
		b.remove(2);
		print("removed all 2s", b); // should be empty
	}

	// TEST Max
	public static void main(String args[]) {
		testMax();
		testMaxRecursive ();
		testDelete ();
		testReverse ();
		testRemove ();
	}
}
