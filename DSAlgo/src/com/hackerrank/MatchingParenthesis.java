package com.hackerrank;

import java.util.Stack;

public class MatchingParenthesis {
	
	static int output = -1;
    static boolean isBalanced(String str, int n)
	{
        char[] exp = str.toCharArray();
		Stack<Character> st = new Stack<>();
        int j = 0;

		for(int i=0;i<exp.length;i++)
		{
			if (exp[i] == '(' ){
				st.push(exp[i]);
                j++;
            }

			if (exp[i] == ')')
			{
                
				if (st.isEmpty())
				{
					return false;
				} 

				else if (!isMatchingPair(st.pop(), exp[i]) )
				{
					return false;
				}
                if (j == n && output == -1){
                    output = i+1;
                }
			}

		}

		if (st.isEmpty())
			return true; 
		else
		{  
			return false;
		} 
	} 

	static boolean isMatchingPair(char character1, char character2)
	{
		if (character1 == '(' && character2 == ')')
			return true;
		else
			return false;
	}

    static int closingBracePosition(String inputSentence, int openingBraceNum) {
        if (!isBalanced(inputSentence, openingBraceNum)){
            return -1;
        }
        else {
            return output;
        }
    }
    
    public static void main(String[] args){
    	String input = "(Gotta (de)b)ug this";
    	System.out.println(closingBracePosition(input, 2));
    }
}
