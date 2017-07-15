package com.techgig.codegladiators;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Sudoku2 {
	
	private static void printArray(int n, int[][] arr) {
		int i,j;
		System.out.println("======================");
		for (i = 0; i< n*n; i++){
			for (j = 0; j < n*n; j++){
				if (j == n*n -1){
					System.out.println(arr[i][j]+ " ");
				}
				else {
					System.out.print(arr[i][j]+" ");
				}
			}
		}
	}

	private static boolean isValueValid(int n, int val, int[][] arr, int x, int y) {
		// Check column
		for (int i = 0; i< n*n; i++){
			if (i != x && arr[i][y] == val){
				return false;
			}
		}
		// Check row
		for (int j = 0; j< n*n; j++){
			if (j != y && arr[x][j] == val){
				return false;
			}
		}
		// Check box
		int box_i = x/n;
		int box_j = y/n;
		for (int i = n*box_i; i< n*(box_i+1); i++){
			for (int j = n*box_j; j < n*(box_j+1); j++){
				if (j != y && i != x && arr[i][j] == val){
					return false;
				}
			}
		}
		return true;
	}
	
	public static int solve(int n,int i, int j, int[][] arr)
    {
		if (arr[i][j] != 0){
			if (i==n*n-1 && j==n*n-1){
				//Possible Solution
				if (swapAndValidateArray(n, arr)){
					//Found Magic Square!");
					printArray(n,arr);
					throw new RuntimeException("1");
				}
				return 1;
			}
			if (j==n*n-1){
				return solve(n, i+1, 0, arr);
			}else {
				return solve(n, i, j+1, arr);
			}
		}
		
		for (int k = 1; k<= n*n; k++){
			if (isValueValid(n, k, arr, i, j)){
				arr[i][j] = k;
			}
			else {
				if (k == n*n){
					arr[i][j] = 0;
					return 0;
				}
				continue;
			}
			if (i==n*n-1 && j==n*n-1){
				//Possible Solution
				if (swapAndValidateArray(n, arr)){
					//Found Magic Square!");
					printArray(n,arr);
					throw new RuntimeException("1");
				}
				arr[i][j] = 0;
				return 0;
			}
			if (j==n*n-1){
				solve(n, i+1, 0, arr);
			}else {
				solve(n, i, j+1, arr);
			}
		}
		arr[i][j] = 0;
		return 0;
    }

	public static void main(String[] args){
//		int[][] arr =  {{1,2,5,0,0,0,0,0,0},
//						{0,0,0,1,2,5,0,0,0},
//						{0,0,0,0,0,0,1,2,5},
//						{5,1,2,0,0,0,0,0,0},
//						{0,0,0,5,1,2,0,0,0},
//						{0,0,0,0,0,0,5,1,2},
//						{2,5,1,0,0,0,0,0,0},
//						{0,0,0,2,5,1,0,0,0},
//						{0,0,0,0,0,0,2,5,1}};
		
//		int[][] arr =  {{1,0,0,0,0,0,0,0,0},
//						{0,0,0,0,0,0,0,0,0},
//						{0,0,0,0,0,0,0,0,0},
//						{0,0,0,0,0,0,0,0,0},
//						{0,0,0,0,0,0,0,0,0},
//						{0,0,0,0,0,0,0,0,0},
//						{0,0,0,0,0,0,0,0,0},
//						{0,0,0,0,0,0,0,0,0},
//						{0,0,0,0,0,0,0,0,0}};
		
//		int[][] arr =  {{1,2,3,4,0,0,0,0,0,0,0,0,0,0,0,0},
//						{0,0,0,0,1,2,3,4,0,0,0,0,0,0,0,0},
//						{0,0,0,0,0,0,0,0,1,2,3,4,0,0,0,0},
//						{0,0,0,0,0,0,0,0,0,0,0,0,1,2,3,4},
//						{3,4,1,2,0,0,0,0,0,0,0,0,0,0,0,0},
//						{0,0,0,0,2,1,4,3,0,0,0,0,0,0,0,0},
//						{0,0,0,0,0,0,0,0,2,1,4,3,0,0,0,0},
//						{0,0,0,0,0,0,0,0,0,0,0,0,2,1,4,3},
//						{3,1,2,4,0,0,0,0,0,0,0,0,0,0,0,0},
//						{0,0,0,0,3,1,2,4,0,0,0,0,0,0,0,0},
//						{0,0,0,0,0,0,0,0,3,1,2,4,0,0,0,0},
//						{0,0,0,0,0,0,0,0,0,0,0,0,3,1,2,4},
//						{4,3,2,1,0,0,0,0,0,0,0,0,0,0,0,0},
//						{0,0,0,0,4,3,2,1,0,0,0,0,0,0,0,0},
//						{0,0,0,0,0,0,0,0,4,3,2,1,0,0,0,0},
//						{0,0,0,0,0,0,0,0,0,0,0,0,4,3,2,1}};
		int[][] arr =  {{1,0,0,0},
						{0,0,0,0},
						{0,0,0,0},
						{0,0,4,0}};
		
//		int[][] arr ={{1,0,6,0,7,0,4,0,3},
//					  {0,0,0,4,0,0,2,0,0},
//					  {0,7,0,0,2,3,0,1,0},
//					  {5,0,0,0,0,0,1,0,0},
//					  {0,4,0,2,0,8,0,6,0},
//					  {0,0,3,0,0,0,0,0,5},
//					  {0,3,0,7,0,0,0,5,0},
//					  {0,0,7,0,0,5,0,0,0},
//					  {4,0,5,0,1,0,7,0,8}};
		System.out.println("=======Input========");
		System.out.println(SolveMagicSquare(arr));
		
		//swapArray(2, arr);
		
		
		
//		Scanner in = new Scanner(System.in);
//        int output = 0;
//        int ip1_rows = 0;
//        int ip1_cols = 0;
//        ip1_rows = Integer.parseInt(in.nextLine().trim());
//        ip1_cols = Integer.parseInt(in.nextLine().trim());
//        
//        int[][] ip1 = new int[ip1_rows][ip1_cols];
//        for(int ip1_i=0; ip1_i<ip1_rows; ip1_i++) {
//            for(int ip1_j=0; ip1_j<ip1_cols; ip1_j++) {
//                ip1[ip1_i][ip1_j] = in.nextInt();
//                
//            }
//        }
//        output = SolveMagicSquare(ip1);
//        System.out.println(String.valueOf(output));
    }

	private static int SolveMagicSquare(int[][] ip1) {
		int n = (int) Math.sqrt(ip1[0].length);
		printArray(n,ip1);
		if (ip1[0][0] == 0){
			ip1[0][0] = 1;
		}
		String output = "0";
		Map<String, Set<Integer>> validRowNos = new HashMap<String, Set<Integer>>();
		Map<String, Set<Integer>> markers = new HashMap<String, Set<Integer>>();
		try {
			if (ip1[0][0] != 1){
				//exit
			}
			else if (isEmpty(ip1,n)){
				output = "1";
			}
			else {
				for (int i=0; i<n ;i++){
					for (int j=0;j<n*n;j+=n){
						precheck(n,i,j,ip1,validRowNos);
					}
				}
				for (int i=0; i<n*n ;i++){
					for (int j=0;j<n*n;j++){
						setMarkers(n,i,j,ip1,markers,validRowNos);
					}
				}
				printArray(n, ip1);
				String[] priorityCell = sortMap(markers);
				solve(n, ip1, priorityCell, 0, markers);
			}
		}
		catch (Exception e){
			output = e.getMessage();
			e.printStackTrace();
		}
		return Integer.parseInt(output);
	}

	private static void solve(int n, int[][] arr, String[] priorityCell, int l, Map<String, Set<Integer>> markers) {
		if (l==priorityCell.length){
			//Possible Solution
			//printArray(n,arr);
			if (swapAndValidateArray(n, arr)){
				//Found Magic Square!");
				printArray(n,arr);
				//throw new RuntimeException("1");
			}
			return;
		}
		
		int i = Integer.parseInt(priorityCell[l].substring(0, 2));
		int j = Integer.parseInt(priorityCell[l].substring(2));
		
		if (arr[i][j] != 0){
			solve(n, arr, priorityCell, l+1, markers);
			return;
		}
		
		Set<Integer> validNums = markers.get(priorityCell[l]);
		Iterator<Integer> itr = validNums.iterator();
		while(itr.hasNext()){
			int k = itr.next();
			if (isValueValid(n, k, arr, i, j)){
				arr[i][j] = k;
			}
			else {
				if (!itr.hasNext()){
					arr[i][j] = 0;
					return;
				}
				continue;
			}
			if (l==priorityCell.length){
				//Possible Solution
				//printArray(n,arr);
				if (swapAndValidateArray(n, arr)){
					//Found Magic Square!");
					printArray(n,arr);
					//throw new RuntimeException("1");
				}
				arr[i][j] = 0;
				return;
			}
			solve(n, arr, priorityCell, l+1, markers);
		}
		arr[i][j] = 0;
		return;
	}

	private static String[] sortMap(Map<String, Set<Integer>> markers) {
		Set<Entry<String, Set<Integer>>> set = markers.entrySet();
        List<Entry<String, Set<Integer>>> list = new ArrayList<Entry<String, Set<Integer>>>(set);
        Collections.sort( list, new Comparator<Map.Entry<String, Set<Integer>>>()
        {
            public int compare( Map.Entry<String, Set<Integer>> o1, Map.Entry<String, Set<Integer>> o2 )
            {
                return (Integer.valueOf(o1.getValue().size())).compareTo(Integer.valueOf(o2.getValue().size()));
            }
        } );
        String[] arr = new String[markers.size()];
        int i = 0;
        for(Map.Entry<String, Set<Integer>> entry:list){
            arr[i] = entry.getKey();
            i++;
        }
        return arr;
	}

	private static void setMarkers(int n, int i, int j, int[][] ip1, Map<String, Set<Integer>> markers, Map<String, Set<Integer>> validRowNos) {
		Set<Integer> set = new HashSet<>();
		if (ip1[i][j]!=0){
			markers.put(getKey(i,j), set);
			return;
		}
		Set<Integer> rowset = validRowNos.get(getKey(i%n,(j/n)*n));
		for (int k = 1; k<= n*n; k++){
			if (isValueValid(n, k, ip1, i, j)){
				set.add(k);
			}
		}
		if (rowset.size() == n){
			set.retainAll(rowset);
			if (set.isEmpty()){
				throw new RuntimeException("0");
			}
			if (set.size() == 1){
				ip1[i][j] = (Integer)set.toArray()[0];
			}
		}
		markers.put(getKey(i,j), set);
	}

	private static void precheck(int n, int x, int y, int[][] ip1, Map<String, Set<Integer>> validNos) {
		Set<Integer> set = new HashSet<>();
		for (int i = x; i < n*n; i+=n){
			for (int j = y; j < y+n;j++){
				if (j < n*n && ip1[i][j]!=0){
					set.add(ip1[i][j]);
				}
			}
		}
		validNos.put(getKey(x,y),set);
		if (set.size() > n){
			throw new RuntimeException("0");
		}
	}

	private static String getKey(int x, int y) {
		String str = Integer.toString(x);
		if (str.length()==1){
			str = "0"+str;
		}
		String str2 = Integer.toString(y);
		if (str2.length()==1){
			str2 = "0"+str2;
		}
		return str + str2;
	}

	private static boolean isEmpty(int[][] ip1, int n) {
		boolean isEmpty = true;
		for (int i = 0; i< n; i++){
			for (int j = 0; j<n; j++){
				if (ip1[i][j] != 0){
					isEmpty = false;
					break;
				}
			}
		}
		return isEmpty;
	}

	private static boolean swapAndValidateArray(int n, int[][] origArr) {
		//Save original copy of array
		int[][] arr = new int[n*n][n*n];
		saveOriginalArray(n, arr, origArr);
		
		for (int i = 0; i< n; i++){
			for (int j = 0; j<n; j++){
				if (j!=n-1){
					swapRight(n, arr, i, j);
					if (!validateArray(n,arr)){
						return false;
					}
				}
				saveOriginalArray(n, arr, origArr);
				if (i!=n-1){
					swapDown(n, arr, i, j);
					if (!validateArray(n,arr)){
						return false;
					}
				}
				saveOriginalArray(n, arr, origArr);
			}
		}
		return true;
	}

	private static boolean validateArray(int n, int[][] arr) {
		//System.out.println("======================");
		for (int i = 0; i< n*n; i++){
			for (int j = 0; j < n*n; j++){
				if (arr[i][j] != 0){
					if (!isValueValid(n, arr[i][j], arr, i, j)){
						//printArray(n,arr);
						return false;
					}
				}
				else {
					//printArray(n,arr);
					return false;//Should not reach this line
				}
			}
		}
		return true;
	}
	
	private static void saveOriginalArray(int n, int[][] arr, int[][] origArr) {
		for (int i = 0; i< n*n; i++){
			for (int j = 0; j<n*n; j++){
				arr[i][j] = origArr[i][j];
			}
		}
	}

	private static void swapRight(int n, int[][] arr, int x, int y) {
		int temp = 0;
		for (int i = x*n; i<(x+1)*n; i++){
			for (int j = y*n; j<(y+1)*n; j++){
				temp = arr[i][j+n];
				arr[i][j+n] = arr[i][j];
				arr[i][j] = temp;
			}
		}
//		System.out.println("=====Right Swap======");
//		printArray(n, arr);
	}

	private static void swapDown(int n, int[][] arr, int x, int y) {
		int temp = 0;
		for (int i = x*n; i<(x+1)*n; i++){
			for (int j = y*n; j<(y+1)*n; j++){
				temp = arr[i+n][j];
				arr[i+n][j] = arr[i][j];
				arr[i][j] = temp;
			}
		}
//		System.out.println("=====Swap Down======");
//		printArray(n, arr);
	}
}
