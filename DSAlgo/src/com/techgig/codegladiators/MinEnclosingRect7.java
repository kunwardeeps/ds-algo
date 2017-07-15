package com.techgig.codegladiators;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class MinEnclosingRect7 {
	
	static Set<Rectangle> rectSet = new LinkedHashSet<Rectangle>();
	static List<Point> pointMasterList = new ArrayList<>();
	
	
	static class Rectangle implements Comparable<Rectangle>{
		int xMin;
		int yMin;
		int xMax;
		int yMax;
		
		public int getxMin() {
			return xMin;
		}

		public int getyMin() {
			return yMin;
		}

		public int getxMax() {
			return xMax;
		}

		public int getyMax() {
			return yMax;
		}

		public Rectangle(int xMin, int yMin, int xMax, int yMax) {
			this.xMin = xMin;
			this.yMin = yMin;
			this.xMax = xMax;
			this.yMax = yMax;
		}
		
		public int getPerimeter(){
			return 2 * ((xMax - xMin + 1) + (yMax - yMin + 1));
		}
		
		@Override
		public int compareTo(Rectangle rect){  
			return this.getPerimeter() - rect.getPerimeter();  
		} 
		
		@Override
	    public boolean equals(Object o) {
	        if (o == null || getClass() != o.getClass()) return false;

	        Rectangle rect = (Rectangle) o;

	        if ((this.getxMax() == rect.getxMax()) &&
	        		(this.getxMin() == rect.getxMin()) &&
	        		(this.getyMax() == rect.getyMax()) &&
	        		(this.getyMin() == rect.getyMin())) 
	        	return true;
	        else 
	        	return false;
	    }
		
		@Override
		public int hashCode(){
			return this.getxMax()+this.getxMin()+this.getyMax()+this.getyMin();
		}
	}  
	
	static class Point{
		int x;
		int y;
		public int getX() {
			return x;
		}
		public int getY() {
			return y;
		}
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	static int countEnclosingPoints(List<Point> pointList, Rectangle rect)
	{
		int count = 0;
		for(int i = 0; i < pointList.size(); i++)
		{
			if(pointList.get(i).getX() >= rect.getxMin() && pointList.get(i).getX() <= rect.getxMax() &&
					pointList.get(i).getY() >= rect.getyMin() && pointList.get(i).getY() <= rect.getyMax())
				count++;

		}
		return count;
	}
	
	static boolean enclosesPoint(Rectangle rect, int x, int y)
	{
		return (x >= rect.getxMin() && x <= rect.getxMax() && y >= rect.getyMin() && y <= rect.getyMax());
	}
	
	public static boolean isDisjoint(Rectangle rect1, Rectangle rect2){
		
		return !(enclosesPoint(rect2, rect1.getxMin(), rect1.getyMin()) ||
				enclosesPoint(rect2, rect1.getxMax(), rect1.getyMin()) ||
				enclosesPoint(rect2, rect1.getxMax(), rect1.getyMax()) ||
				enclosesPoint(rect2, rect1.getxMin(), rect1.getyMax()));
	}

	private static int minPerimeter(int input1, int input2, int input3, int input4, int[][] input5){
		if (input1 < 1 || input2 < 1 || input3 <1 || input4 <1){
			return 0;
		}
		for (int i = 0; i<input5.length; i++){
			pointMasterList.add(new Point(input5[i][0],input5[i][1]));
		}
		return minPerimeter(input4);
	}

	private static int minPerimeter(int k) {
		
		for (Point p: pointMasterList){
			Set<Rectangle> rectSetPartial = new LinkedHashSet<Rectangle>();
			if (k<600){
				List<Point> nearestPoints = getNearestPoints(p, k+1);
				setRectangles(rectSetPartial,nearestPoints,k,p);
			}
			else {
				List<Point> nearestKPoints = getNearestPoints(p, k);
				Rectangle rect = getRectangleFromPoints(nearestKPoints);
				int count = countEnclosingPoints(pointMasterList, rect);
				if (count == k){
					rectSet.add(getRectangleFromPoints(nearestKPoints));
				}
			}
		}
		
		List<Rectangle> rectList = new ArrayList<>();
		rectList.addAll(rectSet);
		
		int minPer = 5000;
		int currMin = 0;
		
		for (int i = 0; i< rectSet.size(); i++){
			for (int j = i+1; j<rectSet.size(); j++){
				if (isDisjoint(rectList.get(i), rectList.get(j))){
					currMin = rectList.get(i).getPerimeter()+ rectList.get(j).getPerimeter();
					if (minPer > currMin){
						minPer = currMin;
					}
				}
			}
		}
		
		if (minPer == 5000){
			minPer = 0;
		}
		
		return minPer;
	}

	private static void setRectangles(Set<Rectangle> rectSetPartial, List<Point> nearestPoints, int k, Point center) {
		
		getRectCombinations(nearestPoints, k, rectSetPartial, center);
		
		Iterator<Rectangle> iter = rectSetPartial.iterator();
		
		while (iter.hasNext()){
			Rectangle rect = iter.next();
			int count = countEnclosingPoints(pointMasterList, rect);
			if (count != k){
				iter.remove();
			}
		}
		
		if (rectSetPartial.size() == 1){
			rectSet.add((Rectangle)rectSetPartial.toArray()[0]);
			return;
		}
		
	    PriorityQueue<Rectangle> minPerHeap = new PriorityQueue<>(2, new Comparator<Rectangle>() {
	        @Override
	        public int compare(Rectangle o1, Rectangle o2) {
	            return o2.getPerimeter() - o1.getPerimeter();
	        }
	    });
	    for (Rectangle r : rectSetPartial) {
	    	minPerHeap.offer(r);
	        if (minPerHeap.size() > 1 ) {
	        	minPerHeap.poll();
	        }
	    }
	    Iterator<Rectangle> i = minPerHeap.iterator();
	    while (i.hasNext()) {
	    	rectSet.add(i.next());
	    }
	}

	private static void getRectCombinations(List<Point> nearestPoints, int k, Set<Rectangle> rectSetPartial,
			Point center) {
		
		nearestPoints.remove(center);
		int size = nearestPoints.size();
		for (int i=0; i<size; i++){
			Point p = nearestPoints.remove(i);
			nearestPoints.add(center);
			Rectangle rect = getRectangleFromPoints(nearestPoints);
			rectSetPartial.add(rect);
			nearestPoints.remove(k-1);
			nearestPoints.add(p);
		}
	}

	private static Rectangle getRectangleFromPoints(List<Point> pointList) {
		int minX = pointList.get(0).getX();
        int maxX = pointList.get(0).getX();
        int minY = pointList.get(0).getY();
        int maxY = pointList.get(0).getY();
		for (Point p: pointList){
			if(p.getX() > maxX)
                maxX = p.getX();
            if(p.getX() < minX)
                minX = p.getX();
            if(p.getY() > maxY)
                maxY = p.getY();
            if(p.getY() < minY)
                minY = p.getY();
		}
		return new Rectangle(minX, minY, maxX, maxY);
	}

	private static List<Point> getNearestPoints(final Point center, int k) {
		List<Point> ans = new ArrayList<>();
	    PriorityQueue<Point> maxHeap = new PriorityQueue<>(k + 1, new Comparator<Point>() {
	        @Override
	        public int compare(Point o1, Point o2) {
	            return distance(center, o2) - distance(center, o1);
	        }
	    });
	    for (Point p : pointMasterList) {
	        maxHeap.offer(p);
	        if (maxHeap.size() > k ) {
	            maxHeap.poll();
	        }
	    }
	    Iterator<Point> i = maxHeap.iterator();
	    while (i.hasNext()) {
	        ans.add(i.next());
	    }
	    return ans;
	}
	
	private static int distance(Point p1, Point p2) {
		return (p1.x - p2.x)*(p1.x - p2.x) + (p1.y - p2.y)*(p1.y - p2.y);
	}
	
	public static void main(String args[]){
		int[][] input = {{2,1},
				{2,1},
				{2,1},
				{3,1},
				{5,1},
				{5,1},
				{2,2},
				{4,2},
				{4,2},
				{1,5},
				{2,5},
				{3,5},
				{4,5},
				{5,5}};
		
//		int[][] input = new int[5000][2];
//		for (int i = 1; i < 5000; i++) {
//		    	input[i][0] = (int)(Math.random()*2499) +1;
//		    	input[i][1] = (int)(Math.random()*2499) +1;
//		}
		System.out.println(minPerimeter(6,7,7,4,input));
	}
	
	
}
