package com.tree;

import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* Class to represent Tree node */
class TreeNode {
	int data;
	TreeNode left, right;

	public TreeNode(int item) {
		data = item;
		left = null;
		right = null;
	}
}

/* Class to print Level Order Traversal */
class BinaryTree {

	TreeNode root;

	/* Given a binary tree. Print its nodes in level order
     using array for implementing queue  */
	void printLevelOrder() 
	{
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		while (!queue.isEmpty()) 
		{

			/* poll() removes the present head.
            For more information on poll() visit 
            http://www.tutorialspoint.com/java/util/linkedlist_poll.htm */
			TreeNode tempNode = queue.poll();
			System.out.print(tempNode.data + " ");

			/*Enqueue left child */
			if (tempNode.left != null) {
				queue.add(tempNode.left);
			}

			/*Enqueue right child */
			if (tempNode.right != null) {
				queue.add(tempNode.right);
			}
		}


	}

	public List<Integer> rightSideView(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();

		if(root == null) return result;

		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);

		while(queue.size() > 0){
			//get size here
			int size = queue.size();

			for(int i=0; i<size; i++){
				TreeNode top = queue.remove();

				//the first element in the queue (right-most of the tree)
				if(i==0){
					result.add(top.data);
				}
				//add right first
				if(top.right != null){
					queue.add(top.right);
				}
				//add left
				if(top.left != null){
					queue.add(top.left);
				}
			}
		}

		return result;
	}

	public List<Integer> leftSideView(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();

		if(root == null) return result;

		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);

		while(queue.size() > 0){
			//get size here
			int size = queue.size();

			for(int i=0; i<size; i++){
				TreeNode top = queue.remove();

				//the first element in the queue (right-most of the tree)
				if(i==0){
					result.add(top.data);
				}
				//add left
				if(top.left != null){
					queue.add(top.left);
				}
				//add right first
				if(top.right != null){
					queue.add(top.right);
				}
			}
		}

		return result;
	}

	// Iterative method to find height of Binary Tree
	int heightIterative(TreeNode node) 
	{
		// Base Case
		if (node == null)
			return 0;

		// Create an empty queue for level order traversal
		Queue<TreeNode> q = new LinkedList<>();

		// Enqueue Root and initialize height
		q.add(node);
		int height = 0;

		while (!q.isEmpty()) 
		{
			int qSize = q.size();

			// Dequeue all nodes of current level and Enqueue all
			// nodes of next level
			while (qSize > 0) 
			{
				TreeNode newnode = q.remove();
				if (newnode.left != null)
					q.add(newnode.left);
				if (newnode.right != null)
					q.add(newnode.right);
				qSize--;
			}
		}
		return height;
	}

	/*The function Compute the "height" of a tree. Height is the
    number f nodes along the longest path from the root node
    down to the farthest leaf node.*/
	int heightRecur(TreeNode node)
	{
		/* base case tree is empty */
		if (node == null)
			return 0;

		/* If tree is not empty then height = 1 + max of left
	         height and right heights */
		return (1 + Math.max(heightRecur(node.left), heightRecur(node.right)));
	}

	int diameterOpt(TreeNode root, int height)
	{
		/* lh --> Height of left subtree
           rh --> Height of right subtree */
		int lh = 0, rh = 0;

		if (root == null)
		{
			height = 0;
			return 0; /* diameter is also 0 */
		}

		/* ldiameter  --> diameter of left subtree
           rdiameter  --> Diameter of right subtree */ 
		/* Get the heights of left and right subtrees in lh and rh
         And store the returned values in ldiameter and ldiameter */
		lh++;     rh++; 
		int ldiameter = diameterOpt(root.left, lh);
		int rdiameter = diameterOpt(root.right, rh);

		/* Height of current node is max of heights of left and
         right subtrees plus 1*/
		height = Math.max(lh, rh) + 1;

		return Math.max(lh + rh + 1, Math.max(ldiameter, rdiameter));
	}

	/* A wrapper over diameter(Node root) */
	int diameter()
	{
		int height = 0;
		return diameterOpt(root, height);
	}

	public static void main(String args[]) 
	{
		/* creating a binary tree and entering 
         the nodes */
		BinaryTree tree_level = new BinaryTree();
		tree_level.root = new TreeNode(1);
		tree_level.root.left = new TreeNode(2);
		tree_level.root.right = new TreeNode(3);
		tree_level.root.left.left = new TreeNode(4);
		tree_level.root.left.right = new TreeNode(5);

		System.out.println("Level order traversal of binary tree is - ");
		tree_level.printLevelOrder();
	}
}