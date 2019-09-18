package java8.collection;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

public class StackSample {

	public static void main(String[] args) {
//		stackSample();//LIFO
		
		/*
		 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
		 * The brackets must close in the correct order, "()" and "(){}[]" are all valid but "(]" and "([)]" are not.
		 */
//		stackSample2();
		
		/*
		 * Catalan number
		 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
		 * For example, given n = 3, a solution set is: "((()))", "(()())", "(())()", "()(())", "()()()"
		 */
//		stackSample3();
		stackSample4();
    }

	private static void stackSample() {
		Stack<String> stack = new Stack<>();
        System.out.println("now the satck is "+isEmpty(stack));
        stack.push("1");
        stack.push("2");
        stack.push("3");
        stack.push("4");
        stack.push("5");
        stack.push("6");
        System.out.println("now the stack is "+isEmpty(stack));
        
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.search("3"));
	}
     
    public static String isEmpty(Stack<String> stack) {
        return stack.empty() ? "empty" : "not empty";
    }  

    private static void stackSample2() {
    	String a = "(())abc{[(])}"; 
    	String b = "(()))abc{[]}"; 
    	String c = "(()()abc{[]}";
    	String d = "(())abc{[]()}"; 
    	
    	System.out.println("a:" + isValid(a));
    	System.out.println("b:" + isValid(b));
    	System.out.println("c:" + isValid(c));
    	System.out.println("d:" + isValid(d));
    }

    public static boolean isValid(String input) {
    	int stackSize = input.length();  
    	Stack<Character> stack = new Stack<>();  
    	int x = 0;  
    	for (char c : input.toCharArray()) {  

    		switch (c) {  
    		case '{':  
    		case '[':  
    		case '(':  
    			stack.push(c);  
    			break;  
    		case '}':  
    		case ']':  
    		case ')':  
    			if (!stack.isEmpty()) {  
    				char chx = (char) stack.pop();  
    				if ((c == '}' && chx != '{') ||  
    						(c == ']' && chx != '[') ||  
    						(c == ')' && chx != '(')  
    						) {  
    					System.err.println("Error, " + input + ", " + c + " at " + x);  
    					return false; 
    				}  
    			} else {  
    				System.err.println("Error, " + input + ", " + c + " at " + x);  
    				return false;  
    			}  
    			break;  
    		default:  
    			break;  
    		}  
    		x++;  
    	}
    	if (stack.isEmpty())
    		return true;
    	else 
    		System.err.println("Error, " + input + ", " + stack.toString());  
    	return stack.isEmpty();		
    }

    private static void stackSample3() {
    	Stack<String> s = generateParenthesis(3);
		while (!s.isEmpty()) {
			System.out.println(s.pop());			
		}
    }
    
    public static void generate(int leftNum, int rightNum, String s, Stack<String> result) {
		if (leftNum == 0 && rightNum == 0) {
			result.push(s);
		}
		
		if (leftNum > 0) {
			generate(leftNum - 1, rightNum, s + '(', result); //--leftNum ?
		}
		
		if (rightNum > 0 && leftNum < rightNum) {
			generate(leftNum, rightNum - 1, s + ')', result);
		}
	}
	
	public static Stack<String> generateParenthesis(int n) {
		Stack<String> s = new Stack<>();
		generate(n, n, "result: ", s);
		return s;
	}

	private static void stackSample4() {
		inoutstack2(0, new Stack<Character>(), "");
	}
	
	static char[] in = {'a','b','c'};

	private static void inoutstack2(int n, Stack<Character> stk, String sout) {   
//		System.out.println("current complexity: " + n + "\t\t" + " out: " + sout);

		if (n == in.length && stk.isEmpty()) { 
			System.out.println(sout);
		} else {
			Stack<Character> s1 = (Stack<Character>) stk.clone();
			Stack<Character> s2 = (Stack<Character>) stk.clone();
			
			if (n < in.length) {
				s1.push(in[n]);
				inoutstack2(n + 1, s1, sout + "");
			}
			
			if (!s2.isEmpty()) {
				String temp = sout + s2.peek();
				s2.pop();
				inoutstack2(n, s2, temp);
			}
		}
	}
	
	
	//recursive
	public int catalen1(int n) {
		if (n==1) {
			return  1;
		}
		return catalen1(n-1)*2*(2*n-1)/(n+1);
	}

	//iterate
	public int catalen2(int n) {
		long C = 1;
		for (int i = 0; i < n; ++i) {
			C = C * 2 * (2 * i + 1) / (i + 2);
		}
		return (int) C;
	}
}
