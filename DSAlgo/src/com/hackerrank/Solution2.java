package com.hackerrank;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.stream.Collectors;

public class Solution2 {
	
	static Map<Integer, TreeSet<Integer>> dateMap = new HashMap<>();
	static Map<Integer, TreeSet<Integer>> productMap = new HashMap<>();
	static Map<Integer, HashMap<Integer, TreeSet<Integer>>> productCategoryMap = new HashMap<>();
	static Map<Integer, TreeSet<Integer>> locationMap = new HashMap<>();
	static Map<Integer, HashMap<Integer, TreeSet<Integer>>> stateRegionMap = new HashMap<>();

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		s.nextLine();
		List<String> input = new ArrayList<String>(n);
		for (int i=0; i<n; i++){
			input.add(s.nextLine());
		}
		processStart(input);

//		    	List<String> input = new TreeSet<String>(100);
//		    	input.add("S 1 1.3 2.5");
//		    	input.add("S 1 1.2 2.6");
//		    	input.add("S 2 2 3.5");
//		    	input.add("S 1 1.2 3.4");
//		    	input.add("Q 1 1 -1");
//		    	processStart(input);
	}

	private static void processStart(List<String> input){
		
		int i = 0;
		for (String currLine: input){
			if (currLine.charAt(0) == 'S'){
				parseSale(currLine,i++);
			}
			else {
				processQuery(currLine);
			}
		}
	}

	private static void parseSale(String currLine, int index) {
		String[] arr = currLine.split(" ");

		Integer date = Integer.parseInt(arr[1]);

		String[] qProdInfo = arr[2].split("\\.");
		Integer prodId = qProdInfo.length > 1 ? Integer.parseInt(qProdInfo[0]):Integer.parseInt(arr[2]);
		Integer catId = qProdInfo.length > 1 ? Integer.parseInt(qProdInfo[1]):0;

		String[] qRegionInfo = arr[3].split("\\.");
		Integer stateId = qRegionInfo.length > 1 ? Integer.parseInt(qRegionInfo[0]):Integer.parseInt(arr[3]);
		Integer regionId = qRegionInfo.length > 1 ? Integer.parseInt(qRegionInfo[1]):0;
		
		TreeSet<Integer> productList = productMap.get(prodId);
		if (productList == null || productList.isEmpty()){
			productList = new TreeSet<Integer>();
		}
		productList.add(index);
		productMap.put(prodId, productList);
		if (catId > 0){
			HashMap<Integer, TreeSet<Integer>> categoryMap = productCategoryMap.get(prodId);
			if (categoryMap == null || categoryMap.isEmpty()){
				categoryMap = new HashMap<>();
			}
			TreeSet<Integer> categoryList = categoryMap.get(catId);
			if (categoryList == null || categoryList.isEmpty()){
				categoryList = new TreeSet<Integer>();
			}
			categoryList.add(index);
			categoryMap.put(catId, categoryList);
			productCategoryMap.put(prodId, categoryMap);
		}
		
		TreeSet<Integer> locationList = locationMap.get(stateId);
		if (locationList == null || locationList.isEmpty()){
			locationList = new TreeSet<Integer>();
		}
		locationList.add(index);
		locationMap.put(stateId, locationList);
		if (regionId > 0){
			HashMap<Integer, TreeSet<Integer>> regionMap = stateRegionMap.get(stateId);
			if (regionMap == null || regionMap.isEmpty()){
				regionMap = new HashMap<>();
			}
			TreeSet<Integer> regionList = regionMap.get(regionId);
			if (regionList == null || regionList.isEmpty()){
				regionList = new TreeSet<Integer>();
			}
			regionList.add(index);
			regionMap.put(regionId, regionList);
			stateRegionMap.put(stateId, regionMap);
		}
		
		TreeSet<Integer> dateList = dateMap.get(date);
		if (dateList == null || dateList.isEmpty()){
			dateList = new TreeSet<Integer>();
		}
		dateList.add(index);
		dateMap.put(date, dateList);
	}

	private static void processQuery(String query) {
		String[] arr = query.split(" ");

		String[] dateArr = arr[1].split("\\.");
		Integer startDate = dateArr.length > 1 ? Integer.parseInt(dateArr[0]):Integer.parseInt(arr[1]);
		Integer endDate = dateArr.length > 1 ? Integer.parseInt(dateArr[1]):0;

		String[] qProdInfo = arr[2].split("\\.");
		Integer prodId = qProdInfo.length > 1 ? Integer.parseInt(qProdInfo[0]):Integer.parseInt(arr[2]);
		Integer catId = qProdInfo.length > 1 ? Integer.parseInt(qProdInfo[1]):0;

		String[] qRegionInfo = arr[3].split("\\.");
		Integer stateId = qRegionInfo.length > 1 ? Integer.parseInt(qRegionInfo[0]):Integer.parseInt(arr[3]);
		Integer regionId = qRegionInfo.length > 1 ? Integer.parseInt(qRegionInfo[1]):0;
		
		
		TreeSet<Integer> productFilteredList = new TreeSet<>();
		if (catId>0){
			HashMap<Integer, TreeSet<Integer>> productFilteredMap = productCategoryMap.get(prodId);
			if (productFilteredMap != null){
				productFilteredList = productFilteredMap.get(catId);
			}
		}
		else if (!prodId.equals(-1)){
			productFilteredList = productMap.get(prodId);
		}
		
		TreeSet<Integer> locationFilteredList = new TreeSet<>();
		if (regionId>0){
			HashMap<Integer, TreeSet<Integer>> locationFilteredMap = stateRegionMap.get(stateId);
			if (locationFilteredMap != null){
				locationFilteredList = locationFilteredMap.get(regionId);
			}
		}
		else if (!stateId.equals(-1)){
			locationFilteredList = locationMap.get(stateId);
		}
		
		TreeSet<Integer> dateFilteredList = getDateFilteredList(startDate,endDate);
		
		if (prodId.equals(-1) && stateId.equals(-1)){
			System.out.println(dateFilteredList == null? "0":dateFilteredList.size());
		}
		else if(prodId.equals(-1)){
			if (dateFilteredList == null || locationFilteredList == null || dateFilteredList.isEmpty() || locationFilteredList.isEmpty()){
				System.out.println("0");
				return;
			}
			Set<Integer> resultSet = new TreeSet<>(locationFilteredList);
			resultSet.retainAll(dateFilteredList);
			System.out.println(resultSet.size());
		}
		else if(stateId.equals(-1)){
			if (productFilteredList == null || dateFilteredList == null || productFilteredList.isEmpty() || dateFilteredList.isEmpty()){
				System.out.println("0");
				return;
			}
			Set<Integer> resultSet = new TreeSet<>(productFilteredList);
			resultSet.retainAll(dateFilteredList);
			System.out.println(resultSet.size());
		}
		else {
			if (productFilteredList == null || dateFilteredList == null || locationFilteredList == null || productFilteredList.isEmpty() || dateFilteredList.isEmpty() || locationFilteredList.isEmpty()){
				System.out.println("0");
				return;
			}
			TreeSet<Integer> resultSet = new TreeSet<>(locationFilteredList);
			resultSet.retainAll(productFilteredList);
			resultSet.retainAll(dateFilteredList);
			System.out.println(resultSet.size());
		}
	}

	private static TreeSet<Integer> getDateFilteredList(Integer startDate, Integer endDate) {
		TreeSet<Integer> dateFilteredList = new TreeSet<>();
		if (endDate>0){
			for (int i = startDate; i <= endDate; i++){
				TreeSet<Integer> list = dateMap.get(i);
				if (list != null){
					dateFilteredList.addAll(list);
				}
			}
		}
		else {
			return dateMap.get(startDate);
		}
		return dateFilteredList;
	}

	

}
