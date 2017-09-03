package com.hackerrank;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;


public class CleartripProb2 {



	static int longestChain(String words[]) {
        int maxLength = 0;
        for (String word : words) {
            int current = getMaxLengthForCurrent(word, Arrays.asList(words));
            if (maxLength < current) {
                maxLength = current;
            }
        }

        return maxLength;
    }

    static int getMaxLengthForCurrent(String word, List<String> allWords) {
        if (word.length() == 1)
            return 1;

        Stack<String> stack = new Stack<String>();
        
        stack.add(word);

        int i = 0;
        int j = 1;

        while (!stack.isEmpty()) {
            String current = stack.peek();

            if (j > current.length()) {
                break;
            }

            if (allWords.contains(current)) {
                StringBuilder sb = new StringBuilder(current);
                sb.delete(i, j);
                stack.add(sb.toString());
            } else {
                stack.pop();
                i++;
                j++;
            }
        }

        return stack.size();
    }
	

	public static void main(String[] args) throws IOException{
		System.out.println(longestChain(new String[]{"ba","a","b","bca", "bcad"}));
	}

}
