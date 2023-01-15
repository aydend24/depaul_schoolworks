package algs24;

import stdlib.StdIn;
import stdlib.StdOut;

/**
 * The <tt>PtrHeap</tt> class is the priorityQ class from Question 2.4.24. It
 * represents a priority queue of generic keys.
 * 
 * It supports the usual <em>insert</em> and <em>delete-the-maximum</em>
 * operations, along with methods for peeking at the maximum key, testing if the
 * priority queue is empty, and iterating through the keys. For additional
 * documentation, see <a href="http://algs4.cs.princeton.edu/24pq">Section
 * 2.4</a> of <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin
 * Wayne.
 */

public class PtrHeap<K extends Comparable<? super K>> {

	static class Node<K> {
		int N; // number of elements
		K value; // value of the node
		Node<K> parent; // parent node
		Node<K> lchild; // left child node
		Node<K> rchild; // right child node

		// Node constructor
		public Node(K value, int N) {
			this.value = value;
			this.N = N;
		}
	}

	private Node<K> root; // root of binary tree
//	private Node<K> lastInserted;
	private Node<K> latestInserted;

	// helper methods
	private int size(Node<K> x) { // size of Node
		if (x == null) {
			return 0;
		}
		return x.N;
	}

	// check if it is less
	boolean less(K u, K v) {
		return (u.compareTo(v) < 0);
	}

	boolean isRoot(Node<K> n) {
		return n == root; // if n == root, then n is root, return true; otherwise, return false;
	}

	Node<K> find(int n) {
		return null;
	}

	// exchange
	void exch(Node<K> n1, Node<K> n2) {
		// only swap items of nodes
		K temp = n1.value;
		n1.value = n2.value;
		n2.value = temp;
	}

	// swim
	private void swim(Node<K> n) {
		if (n == null) { // if n is null, no need to swim
			return;
		}

		// n is root
		if (n.parent == null) { // if n is root, no need to swim
			return;
		}

		// compare value of n and its parent to decide whether to swim
		boolean isLess = less(n.value, n.parent.value); // true if is less

		// n(parent's child) is greater than parent, then swim(exchange values of n and
		// its parent)
		if (!isLess) {
			exch(n, n.parent);
			swim(n.parent); // swim up to n's parent
		}
	}

	// sink
	private void sink(Node<K> n) {

		Node<K> nodeExchanged;

		// base case
		if (n == null) { // if n is null, no need to sink
			return;
		}

		// no child
		if (n.lchild == null && n.rchild == null) { // if left child and right child are null, no need to sink
			return;

			// no left child
		} else if (n.lchild == null) { // if left child null, no need to sink
			// right child is the one to be exchanged with n
			nodeExchanged = n.rchild;
			// compare value to decide whether to exchange
			boolean isLess = less(n.value, nodeExchanged.value);
			// n < n.rchild
			if (isLess) {
				exch(nodeExchanged, n);
			}

			// no right child
		} else if (n.rchild == null) {
			// left child is the one to be exchanged with x
			nodeExchanged = n.lchild;
			// compare value to decide whether to exchange
			boolean isLess = less(n.value, nodeExchanged.value);
			// n < n.lchild
			if (isLess) {
				exch(nodeExchanged, n);
			}

			// left child and right child are not null
		} else {

			// compare value to decide which to be exchanged with n
			boolean isLess = less(n.lchild.value, n.rchild.value);
			// left child is greater
			if (!isLess) {
				nodeExchanged = n.lchild;

				// right child is greater
			} else {
				nodeExchanged = n.rchild;
			}

			// compare value to decide whether to exchange
			boolean xIsLess = less(n.value, nodeExchanged.value);
			// child is greater, exchange
			if (xIsLess) {
				exch(nodeExchanged, n);
				sink(nodeExchanged); // sink down to nodeExchange
			}

		}
	}

	// insert Node
	private Node<K> insert(Node<K> n, K value) {
		if (n == null) {
			latestInserted = new Node<K>(value, 1); // initialize a new node as the latest inserted node
			return latestInserted; // the latest inserted node is the root
		}

		// compare size of left child and right child to decide where to insert
		int L = size(n.lchild);
		int R = size(n.rchild);
		
		// size of left child is greater
		if (L >= R) {
			// insert to right
			Node<K> inserted = insert(n.rchild, value);
			n.rchild = inserted;
			inserted.parent = n;
			// size of right child is greater
		} else {
			// insert to left
			Node<K> inserted = insert(n.lchild, value);
			n.lchild = inserted;
			inserted.parent = n;
		}

		// maintain invariant
		n.N = size(n.lchild) + size(n.rchild) + 1;
		return n;
	}

	private Node<K> updateLatestInserted(Node<K> n) {
		// base case
		if (n == null) {
			return null;
		}
		if (n.lchild == null && n.rchild == null) {
			return n;
		}

		int L = size(n.lchild);
		int R = size(n.rchild);

		if (R < L) {
			return updateLatestInserted(n.lchild);
		} else {
			return updateLatestInserted(n.rchild);
		}
	}

	@SuppressWarnings("unchecked")
	/** Create an empty priority queue */
	public PtrHeap() {
		int N = 0;
		K value = null;
		Node<K> parent = null;
		Node<K> lchild = null;
		Node<K> rchild = null;

	}

	/** Is the priority queue empty? */
	public boolean isEmpty() {
		return size() == 0;
	}

	/** Return the number of items on the priority queue. */
	public int size() {
		return size(root);
	}

	/**
	 * Return the largest key on the priority queue. Throw an exception if the
	 * priority queue is empty.
	 */
	public K max() {
		if (root == null) {
			return null;
		}
		return root.value;

	}

	/** Add a new key to the priority queue. */
	public void insert(K x) {
		root = insert(root, x);
		swim(latestInserted);
	}

	/**
	 * Delete and return the largest key on the priority queue. Throw an exception
	 * if the priority queue is empty.
	 */
	public K delMax() {
		// base case
		if (size() == 1) {
			// root is the only one element and it is the max
			// no need to do anything else
			K max = root.value; 
			root = null;
			return max;
		}
		
		// root is the max
		// move the max(root)  to the lastestInserted(the last leaf)
		exch(root, latestInserted);
		
		// to get the last leaf(max)'s parent
		Node<K> latestParent = latestInserted.parent;
		K latestVal = latestInserted.value;
		
		// if max equals to its parent's left child
		if (latestInserted == latestParent.lchild) {
			// delete left child
			latestParent.lchild = null;
			// if max equals to its parent's left child
		} else {
			// delete left child
			latestParent.rchild = null;
		}

		Node<K> traverser = latestInserted;

		while (traverser != null) {
			traverser.N--;
			traverser = traverser.parent;
		}

		latestInserted = updateLatestInserted(root);

		sink(root);

		return latestVal;
	}
	
	// print
	private void printHeap(Node<K> n) {
		if (n == null)
			return;
		System.out.print(n.value + " ");
		printHeap(n.lchild);
		printHeap(n.rchild);
	}

	private void showHeap() {
		printHeap(root);
		System.out.println();
	}

	public static void main(String[] args) {
		PtrHeap<String> pq = new PtrHeap<>();
		StdIn.fromString("10 20 30 40 50 - - - 05 25 35 - - - 70 80 05 - - - - ");
		while (!StdIn.isEmpty()) {
			StdOut.print("pq:  ");
			pq.showHeap();
			String item = StdIn.readString();
			if (item.equals("-"))
				StdOut.println("max: " + pq.delMax());
			else
				pq.insert(item);
		}
		StdOut.println("(" + pq.size() + " left on pq)");

	}

}
