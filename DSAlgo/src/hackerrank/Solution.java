package hackerrank;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.stream.Collectors;

public class Solution {
	
	static Map<Integer, ArrayList<Integer>> dateMap = new HashMap<>();
	static Map<Integer, ArrayList<Integer>> productMap = new HashMap<>();
	static Map<String, ArrayList<Integer>> productCategoryMap = new HashMap<>();
	static Map<Integer, ArrayList<Integer>> locationMap = new HashMap<>();
	static Map<String, ArrayList<Integer>> stateRegionMap = new HashMap<>();

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		s.nextLine();
		List<String> input = new ArrayList<String>(n);
		for (int i=0; i<n; i++){
			input.add(s.nextLine());
		}
		processStart(input);

//		    	List<String> input = new ArrayList<String>(100);
//		    	input.add("S 1 3.6 2.4");
//		    	input.add("S 1 3.6 2.4");
//		    	input.add("Q 1 3.6 2.4");
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
		
		ArrayList<Integer> productList = productMap.get(prodId);
		if (productList == null || productList.isEmpty()){
			productList = new ArrayList<Integer>();
		}
		productList.add(index);
		productMap.put(prodId, productList);
		if (catId > 0){
			ArrayList<Integer> categoryList = productCategoryMap.get(arr[2]);
			if (categoryList == null || categoryList.isEmpty()){
				categoryList = new ArrayList<Integer>();
			}
			categoryList.add(index);
			productCategoryMap.put(arr[2], categoryList);
		}
		
		ArrayList<Integer> locationList = locationMap.get(stateId);
		if (locationList == null || locationList.isEmpty()){
			locationList = new ArrayList<Integer>();
		}
		locationList.add(index);
		locationMap.put(stateId, locationList);
		if (regionId > 0){
			ArrayList<Integer> regionList = stateRegionMap.get(arr[3]);
			if (regionList == null || regionList.isEmpty()){
				regionList = new ArrayList<Integer>();
			}
			regionList.add(index);
			stateRegionMap.put(arr[3], regionList);
		}
		
		ArrayList<Integer> dateList = dateMap.get(date);
		if (dateList == null || dateList.isEmpty()){
			dateList = new ArrayList<Integer>();
		}
		dateList.add(index);
		dateMap.put(date, dateList);
	}

	private static void processQuery(String query) {
		String[] arr = query.split(" ");

		String[] dateArr = arr[1].split("\\.");
		Integer startDate = dateArr.length > 1 ? Integer.parseInt(dateArr[0]):Integer.parseInt(arr[1]);
		Integer endDate = dateArr.length > 1 ? Integer.parseInt(dateArr[1]):0;

		String prodId = arr[2];
		ArrayList<Integer> productFilteredList = new ArrayList<>();
		if (prodId.contains(".")){
			productFilteredList = productCategoryMap.get(prodId);
		}
		else if (!prodId.equals("-1")){
			productFilteredList = productMap.get(Integer.parseInt(prodId));
		}
		
		String stateId = arr[3];
		ArrayList<Integer> locationFilteredList = new ArrayList<>();
		if (stateId.contains(".")){
			locationFilteredList = stateRegionMap.get(stateId);
		}
		else if (!stateId.equals("-1")){
			locationFilteredList = locationMap.get(Integer.parseInt(stateId));
		}
		
		ArrayList<Integer> dateFilteredList = getDateFilteredList(startDate,endDate);
		
		if (prodId.equals("-1") && stateId.equals("-1")){
			System.out.println(dateFilteredList == null? "0":dateFilteredList.size());
		}
		else if(prodId.equals("-1")){
			if (dateFilteredList == null || locationFilteredList == null || dateFilteredList.isEmpty() || locationFilteredList.isEmpty()){
				System.out.println("0");
				return;
			}
			Set<Integer> resultSet = new HashSet<>(locationFilteredList);
			resultSet.retainAll(dateFilteredList);
			System.out.println(resultSet.size());
		}
		else if(stateId.equals("-1")){
			if (productFilteredList == null || dateFilteredList == null || productFilteredList.isEmpty() || dateFilteredList.isEmpty()){
				System.out.println("0");
				return;
			}
			Set<Integer> resultSet = new HashSet<>(productFilteredList);
			resultSet.retainAll(dateFilteredList);
			System.out.println(resultSet.size());
		}
		else {
			if (productFilteredList == null || dateFilteredList == null || locationFilteredList == null || productFilteredList.isEmpty() || dateFilteredList.isEmpty() || locationFilteredList.isEmpty()){
				System.out.println("0");
				return;
			}
			Set<Integer> resultSet = new HashSet<>(locationFilteredList);
			resultSet.retainAll(productFilteredList);
			resultSet.retainAll(dateFilteredList);
			System.out.println(resultSet.size());
		}
	}

	private static ArrayList<Integer> getDateFilteredList(Integer startDate, Integer endDate) {
		ArrayList<Integer> dateFilteredList = new ArrayList<>();
		if (endDate>0){
			for (int i = startDate; i <= endDate; i++){
				ArrayList<Integer> list = dateMap.get(i);
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
