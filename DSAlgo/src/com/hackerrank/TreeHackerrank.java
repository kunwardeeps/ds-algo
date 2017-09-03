package com.hackerrank;

import java.util.LinkedList;
import java.util.Queue;

class TreeNodeFract {
	int num;
	int den;
	TreeNodeFract left, right;

	public TreeNodeFract(int num, int den) {
		this.num = num;
		this.den = den;
		left = null;
		right = null;
	}

	@Override
	public boolean equals(Object o) {

		if (o == this) {
			return true;
		}

		if (!(o instanceof TreeNodeFract)) {
			return false;
		}

		TreeNodeFract n = (TreeNodeFract) o;

		return num == n.num && den == n.den;
	}
}

class TreeHackerrank {

	TreeNodeFract root;

	void constructTree(TreeNodeFract parent, int level){
		if (level == 1){
			root = parent;
		}
		if (level <= 21){
			parent.left = new TreeNodeFract(parent.num, parent.den + parent.num);
			parent.right = new TreeNodeFract(parent.den + parent.num, parent.den);
			level++;
			constructTree(parent.left, level);
			constructTree(parent.right, level);
		}
	}

	String getNthElement(int n) 
	{
		Queue<TreeNodeFract> queue = new LinkedList<TreeNodeFract>();
		queue.add(root);
		int i = 0;
		while (!queue.isEmpty()) 
		{

			TreeNodeFract tempNode = queue.poll();
			i++;
			if (i == n){
				return tempNode.num+"/"+tempNode.den;
			}

			if (tempNode.left != null) {
				queue.add(tempNode.left);
			}

			if (tempNode.right != null) {
				queue.add(tempNode.right);
			}
		}
		return null;

	}

	String getNodePosition(TreeNodeFract node) 
	{
		Queue<TreeNodeFract> queue = new LinkedList<TreeNodeFract>();
		queue.add(root);
		int i = 0;
		while (!queue.isEmpty()) 
		{

			TreeNodeFract tempNode = queue.poll();
			i++;
			if (tempNode.equals(node)){
				return Integer.toString(i);
			}

			if (tempNode.left != null) {
				queue.add(tempNode.left);
			}

			if (tempNode.right != null) {
				queue.add(tempNode.right);
			}
		}

		return null;

	}




	public static void main(String args[]) 
	{
		TreeHackerrank tree = new TreeHackerrank();
		tree.constructTree(new TreeNodeFract(1, 1), 1);
		
		String input = "N 3";
		String[] arr = input.split(" ");
		
		if (arr.length == 2){
			System.out.println(tree.getNthElement(Integer.parseInt(arr[1])));
		}
		else {
			System.out.println(tree.getNodePosition(new TreeNodeFract(Integer.parseInt(arr[1]), Integer.parseInt(arr[2]))));
		}


	}
}