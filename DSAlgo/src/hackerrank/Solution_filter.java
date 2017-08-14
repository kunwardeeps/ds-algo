package hackerrank;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.stream.Collectors;

public class Solution_filter {

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
//		    	input.add("S 1 1.3 2.5");
//		    	input.add("S 1 1.2 2.6");
//		    	input.add("S 2 2 3.5");
//		    	input.add("S 1 1.2 3.4");
//		    	input.add("S 3 2.3 6.7");
//		    	input.add("S 3 2.4 6.8");
//		    	input.add("S 2 2 7.8");
//		    	input.add("Q 1.3 2 -1");
//		    	processStart(input);
	}

	private static void processStart(List<String> input){
		Map<Integer, String> map = new HashMap<>();
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
		String[] arr = query.split(" ");

		String[] qDateArr = arr[1].split("\\.");
		final Integer qStartDate = qDateArr.length > 1 ? Integer.parseInt(qDateArr[0]):Integer.parseInt(arr[1]);
		final Integer qEndDate = qDateArr.length > 1 ? Integer.parseInt(qDateArr[1]):0;

		String[] qProdInfo = arr[2].split("\\.");
		final String qPid = qProdInfo.length > 1 ? qProdInfo[0]:arr[2];
		final String qCid = qProdInfo.length > 1 ? qProdInfo[1]:"";

		String[] qRegionInfo = arr[3].split("\\.");
		final String qSid = qRegionInfo.length > 1 ? qRegionInfo[0]:arr[3];
		final String qRid = qRegionInfo.length > 1 ? qRegionInfo[1]:"";
		
		

		System.out.println(sales.stream()
			.filter(s -> filterByDate(s, qStartDate, qEndDate) && filterByProduct(s, qPid, qCid) && filterByRegion(s, qSid, qRid))
			.collect(Collectors.counting()));
	}

	private static boolean filterByRegion(String str, String qSid, String qRid) {
		if (qSid.equals("-1")){
			return true;
		}
		String currSid = "";
		String currRid = "";
		String regionInfo = str.split(" ")[3];
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
				return false;
			}
		}
		else {
			if (!qSid.equals(currSid)){
				return false;
			}
		}
		return true;
	}

	private static boolean filterByDate(String str, int qStartDate, int qEndDate) {

		int currDate = Integer.parseInt(str.split(" ")[1]);

		if (qEndDate > 0){
			if (currDate < qStartDate || currDate > qEndDate){
				return false;
			}
		}
		else {
			if (currDate != qStartDate){
				return false;
			}
		}
		return true;
	}

	private static boolean filterByProduct(String str, String qPid, String qCid) {
		if (qPid.equals("-1")){
			return true;
		}
		String currPid = "";
		String currCid = "";
		String prod = str.split(" ")[2];
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
				return false;
			}
		}
		else {
			if (!qPid.equals(currPid)){
				return false;
			}
		}
		return true;
	}

}
