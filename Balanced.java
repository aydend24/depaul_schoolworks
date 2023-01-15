package ds2;

import algs4.*;

public class Balanced {
	public static boolean isBalanced(String input) {
        Stack<Character> stack = new Stack<Character>();

        // Scan the string
        for(char chr : input.toCharArray()){
            switch(chr){
                case '(':
                case '[':
                case '{':
                // found right enlousure, push into stack
                stack.push(chr);
                break;

                case ')':
                    if(stack.isEmpty() || stack.pop() != '('){
                        return false;
                    }
                    break;
                case ']':
                    if(stack.isEmpty() || stack.pop() != '['){
                        return false;
                    }
                    break;
                case '}':
                    if(stack.isEmpty() || stack.pop() != '{'){
                        return false;
                    }
                    break;
            }
        }
        return stack.isEmpty();
	}

	public static void main(String[] args) {
		StdOut.print("Enter a string: ");
		String input = StdIn.readString();
		boolean isBalanced = isBalanced(input);

		if (isBalanced) {
			StdOut.println(input + ": input string is balanced");
		} else {
			StdOut.println(input + ": input string is not balanced");
		}

//		StdOut.println(input);
	}

}
