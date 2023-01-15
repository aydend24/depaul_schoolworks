package algs13;


import stdlib.*;

// PROBLEM 2.2.17
public class MyLinkedSort {
    static class Node {
        public Node() { }
        public double item;
        public Node next;
    }

    int N;
    Node first;
    
    public MyLinkedSort () {
        first = null;
        N = 0;
        checkInvariants ();
    }

    private void myassert (String s, boolean b) { if (!b) throw new Error ("Assertion failed: " + s); }
    private void checkInvariants() {
        myassert("Empty <==> first==null", (N == 0) == (first == null));
        Node x = first;
        for (int i = 0; i < N; i++) {
            if (x==null) {
                throw new Error ("List too short!");
            }
            x = x.next;
        }
        myassert("EndOfList == null", x == null);
    }

    public boolean isEmpty () { return first == null; }
    public int size () { return N; }
    public void add (double item) {
        Node newfirst = new Node ();
        newfirst.item = item;
        newfirst.next = first;
        first = newfirst;
        N++;
    }

    private static void print (String s, Node b) {
        StdOut.print (s + ": ");
        for (Node x = b; x != null; x = x.next)
            StdOut.print (x.item + " ");
        StdOut.println ();
    }
    private static void print (String s, Node b, double i) {
        StdOut.print (s + ": ");
        for (Node x = b; x != null; x = x.next)
            StdOut.print (x.item + " ");
        StdOut.println (": " + i);
    }

    static public Node sort(Node l ){ 
	   // base case: list is of size 1. return
    	if(l == null || l.next == null) 
    		return l;
    	
	   // otherwise use split to create two lists    	
    	Node[] split = split(l);
    	
	   // recursively sort each of them
    	Node lt = sort(split[0]);
    	Node rt = sort(split[1]);
    	
	   // use merge to combine them, and return the result
	   return merge(lt, rt);
	}
		 
	static public Node[] split(Node l){
      // parameter is a List
	  // it returns an array of size 2
	  // the 0th element is the left list
	  // the first element is the right list
		Node[] splitList = new Node[2];
		
    	Node prev = null;
    	Node slow = l;
    	Node fast = l;
    	 
    	while(fast != null && fast.next != null) {
    		prev = slow;
    		slow = slow.next;
    		fast = fast.next.next;
    	}
    	prev.next = null;
    	
    	splitList[0] = l;
    	splitList[1] = slow;
    	
		return splitList;
	  }
	
	static public Node merge(Node lt, Node rt){
	// merge creates a new List
	// whose elements come from the lt and rt MyLinkedLists
		Node newFirst = new Node();
		Node temp = newFirst;
		
		while(lt != null && rt != null) {
			if(lt.item < rt.item) {
				temp.next = lt;
				lt = lt.next;
			} else {
				temp.next = rt;
				rt = rt.next;
			}
			temp = temp.next;
		}
		
		if(lt != null) {
			temp.next = lt;
		}
		
		if(rt != null) {
			temp.next = rt;
		}
		
		return newFirst.next;
	}

    public static void main (String args[]) {
        int[] a1 = new int[20];
		for (int i = 0; i < a1.length; i++)
			a1[i] = i;
		StdRandom.shuffle (a1);
        MyLinkedSort b1 = new MyLinkedSort ();
        for (int i:a1) b1.add(i);
        MyLinkedSort.print("b1 before sort",b1.first);
        Node res1 = MyLinkedSort.sort(b1.first);
        MyLinkedSort.print("b1 after sort",res1);

        int[] a2 = new int[200];
		for (int i = 0; i < a2.length; i++)
			a2[i] = i;
		StdRandom.shuffle (a2);
        MyLinkedSort b2 = new MyLinkedSort ();
        for (int i:a1) b2.add(i);
        MyLinkedSort.print("b2 before sort",b2.first);
        Node res2 = MyLinkedSort.sort(b2.first);
        MyLinkedSort.print("b2 after sort",res2);

       // write code for a doubling Test
        int[] a3 = new int[30];
        for(int i = 0; i < a3.length; i++)
        	a3[i] = i+1;
        StdRandom.shuffle(a3);
        MyLinkedSort b3 = new MyLinkedSort();
        for(int i:a3)b3.add(i);
        MyLinkedSort.print("b3 before sort",b3.first);
        Node res3 = MyLinkedSort.sort(b3.first);
        MyLinkedSort.print("b3 after sort",res3);
         
    }
}



