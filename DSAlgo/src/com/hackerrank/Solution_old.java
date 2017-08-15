package com.hackerrank;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution_old {

    public static void main(String[] args) {
    	
    	Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        s.nextLine();
        List<String> input = new ArrayList<String>(n);
        for (int i=0; i<n; i++){
        	input.add(s.nextLine());
        }
        processStart(input);
    	
//    	List<String> input = new ArrayList<String>(100);
//    	input.add("S 1 1.3 2.5");
//    	input.add("S 1 1.2 2.6");
//    	input.add("S 2 2 3.5");
//    	input.add("S 1 1.2 3.4");
//    	input.add("S 3 2.3 6.7");
//    	input.add("S 3 2.4 6.8");
//    	input.add("S 2 2 7.8");
//    	input.add("Q 1.3 2 -1");
//    	processStart(input);
    }
    
    private static void processStart(List<String> input){
    	List<String> sales = new ArrayList<String>(input.size());
    	for (String currLine: input){
    		if (currLine.charAt(0) == 'S'){
        		sales.add(currLine);
        	}
        	else {
        		processQuery(sales,	currLine);
        	}
    	}
    }

	private static void processQuery(List<String> sales, String query) {
		String qStartDate="";
		String qEndDate="";
		String qPid="";
		String qCid="";
		String qSid="";
		String qRid="";
		String[] arr = query.split(" ");
		
		String[] qDateArr = arr[1].split("\\.");
		if (qDateArr.length > 1){
			qStartDate = qDateArr[0];
			qEndDate = qDateArr[1];
		}
		else {
			qStartDate = arr[1];
		}
		
		String[] qProdInfo = arr[2].split("\\.");
		if (qProdInfo.length > 1){
			qPid = qProdInfo[0];
			qCid = qProdInfo[1];
		}
		else {
			qPid = arr[2];
		}
		
		String[] qRegionInfo = arr[3].split("\\.");
		if (qRegionInfo.length > 1){
			qSid = qRegionInfo[0];
			qRid = qRegionInfo[1];
		}
		else {
			qSid = arr[3];
		}
		
		List<String> resultSet = new ArrayList<String>(sales.size());
		copy(resultSet, sales);
		
		filterByProduct(resultSet, qPid, qCid);
		
		filterByDate(resultSet, Integer.parseInt(qStartDate), qEndDate.equals("")?0:Integer.parseInt(qEndDate));
		
		filterByRegion(resultSet, qSid, qRid);
		
		System.out.println(resultSet.size());
	}

	private static void copy(List<String> resultSet, List<String> sales) {
		for (String itr: sales){
			resultSet.add(itr);
		}
		
	}

	private static void filterByRegion(List<String> sales, String qSid, String qRid) {
		if (qSid.equals("-1")){
			return;
		}
		for (int i=0; i<sales.size(); i++){
			String currSid = "";
			String currRid = "";
			String regionInfo = sales.get(i).split(" ")[3];
			String[] regInfoArr = regionInfo.split("\\.");
			if (regInfoArr.length > 1){
				currSid = regInfoArr[0];
				currRid = regInfoArr[1];
			}
			else {
				currSid = regionInfo;
			}
			
			if (!qRid.equals("")){
				if (!qRid.equals(currRid) || !qSid.equals(currSid)){
					sales.remove(i--);
				}
			}
			else {
				if (!qSid.equals(currSid)){
					sales.remove(i--);
				}
			}
		}
	}

	private static void filterByDate(List<String> sales, int qStartDate, int qEndDate) {
		
		for (int i=0; i<sales.size(); i++){
			int currDate = Integer.parseInt(sales.get(i).split(" ")[1]);
			
			if (qEndDate > 0){
				if (currDate < qStartDate || currDate > qEndDate){
					sales.remove(i--);
				}
			}
			else {
				if (currDate != qStartDate){
					sales.remove(i--);
				}
			}
		}
	}

	private static void filterByProduct(List<String> sales, String qPid, String qCid) {
		if (qPid.equals("-1")){
			return;
		}
		for (int i=0; i<sales.size(); i++){
			String currPid = "";
			String currCid = "";
			String prod = sales.get(i).split(" ")[2];
			String[] prodInfo = prod.split("\\.");
			if (prodInfo.length > 1){
				currPid = prodInfo[0];
				currCid = prodInfo[1];
			}
			else {
				currPid = prod;
			}
			
			if (!qCid.equals("")){
				if (!qCid.equals(currCid) || !qPid.equals(currPid)){
					sales.remove(i--);
				}
			}
			else {
				if (!qPid.equals(currPid)){
					sales.remove(i--);
				}
			}
		}
	}
}
