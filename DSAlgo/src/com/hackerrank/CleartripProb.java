package com.hackerrank;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class CleartripProb {

	static class TrieDS{
		private TrieNode root;

		public TrieDS()
		{
			root = new TrieNode(' '); 
		}
		
		class TrieNode 
		{
			char data; 
			boolean isEnd; 
			int count;  
			List<TrieNode> childList; 

			public TrieNode(char c)
			{
				childList = new LinkedList<>();
				isEnd = false;
				data = c;
				count = 0;
			}  
			public TrieNode getChild(char c)
			{
				if (childList != null){
					for (TrieNode eachChild : childList){
						if (eachChild.data == c){
							return eachChild;
						}
					}
				}
				return null;
			}
		}
		
		public void insertWord(String word)
		{
			if (searchWord(word) == true) 
				return;        
			TrieNode current = root; 
			for (char ch : word.toCharArray() )
			{
				TrieNode child = current.getChild(ch);
				if (child != null)
					current = child;
				else 
				{
					current.childList.add(new TrieNode(ch));
					current = current.getChild(ch);
				}
				current.count++;
			}
			current.isEnd = true;
		}
		
		public boolean searchWord(String word)
		{
			TrieNode current = root;  
			for (char ch : word.toCharArray() )
			{
				if (current.getChild(ch) == null){
					return false;
				}
				else{
					current = current.getChild(ch);
				}
			}      
			if (current.isEnd == true) {
				return true;
			}
			return false;
		}
		
		public int getHeight(TrieNode node) {
		    int lev = 0;
		    if(node != null) {
		        for(TrieNode child : node.childList) {
		        	lev = Math.max(lev, 1 + getHeight(child));
		        }      
		    }   
		    return lev;
		}
	}


	static int longestChain(String[] words) {
		
		TrieDS trieDS = new TrieDS();
		for (String word: words){
			trieDS.insertWord(word);
		}
		return trieDS.getHeight(trieDS.root);

	}

	

	public static void main(String[] args) throws IOException{
		System.out.println(longestChain(new String[]{"ba","a","b","bca", "safhasdfh"}));
	}

}
