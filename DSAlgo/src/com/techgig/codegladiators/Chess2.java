package com.techgig.codegladiators;

import java.util.LinkedList;
import java.util.Queue;

public class Chess2 {
	
	static class Position{
		int x;
		int y;
		int move;
		
		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public int getMove() {
			return move;
		}
		
		public void setMove(int move) {
			this.move =  move;
		}
		
		Position(int x, int y, int move){
			this.x = x;
			this.y = y;
			this.move = move;
		}
	}
	
	public static void main(String[] args){
		System.out.println(getStepCount(1,1,1,2));
		System.out.println(getStepCount(1,1,2,2));
		System.out.println(getStepCount(1,1,1,3));
		System.out.println(getStepCount(1,1,2,3));
		System.out.println(getStepCount(1,1,3,3));
		System.out.println(getStepCount(1,1,1,4));
		System.out.println(getStepCount(1,1,2,4));
		System.out.println(getStepCount(1,1,3,4));
		System.out.println(getStepCount(1,1,4,4));
		System.out.println(getStepCount(1,1,1,5));
		System.out.println(getStepCount(1,1,2,5));
		System.out.println(getStepCount(1,1,3,5));
		System.out.println(getStepCount(1,1,4,5));
		System.out.println(getStepCount(1,1,5,5));
		System.out.println(getStepCount(1,1,1,6));
		System.out.println(getStepCount(1,1,2,6));
		System.out.println(getStepCount(1,1,3,6));
		System.out.println(getStepCount(1,1,4,6));
		System.out.println(getStepCount(1,1,5,6));
		System.out.println(getStepCount(1,1,6,6));
		System.out.println(getStepCount(1,1,1,7));
		System.out.println(getStepCount(1,1,2,7));
		System.out.println(getStepCount(1,1,3,7));
		System.out.println(getStepCount(1,1,4,7));
		System.out.println(getStepCount(1,1,5,7));
		System.out.println(getStepCount(1,1,6,7));
		System.out.println(getStepCount(1,1,7,7));
		System.out.println(getStepCount(1,1,1,8));
		System.out.println(getStepCount(1,1,2,8));
		System.out.println(getStepCount(1,1,3,8));
		System.out.println(getStepCount(1,1,4,8));
		System.out.println(getStepCount(1,1,5,8));
		System.out.println(getStepCount(1,1,6,8));
		System.out.println(getStepCount(1,1,7,8));
		System.out.println(getStepCount(1,1,8,8));		
	}
	
	

	

	private static int getStepCount(int input1, int input2, int input3, int input4) {
		boolean[][] visits = new boolean[8][8];
		int[] xValid = {1,2, 1, 2,-1,-2,-2,-1};
		int[] yValid = {2,1,-2,-1,-2,-1, 1, 2};
		Position start = new Position(input1-1,input2-1,0);
		Queue<Position> q = new LinkedList<>();
		q.add(start);
		
		while (!q.isEmpty()){
			Position curr = q.poll();
			visits[curr.getX()][curr.getY()] = true;
			if (curr.getX() == input3 -1 && curr.getY() == input4-1){
				return curr.getMove();
			}
			for (int i=0;i<8;i++){
				int nextX = curr.getX()+xValid[i];
				int nextY = curr.getY()+yValid[i];
				if (validMove(nextX,nextY) && !visits[nextX][nextY]){
					q.add(new Position(nextX,nextY,curr.getMove()+1));
				}
			}
		}
		return 100;
	}

	private static boolean validMove(int i, int j) {
		return ((i >=0 && i<=7) && (j >=0 && j<=7));
	}

}
