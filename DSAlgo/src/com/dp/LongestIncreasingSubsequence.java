package com.dp;

public class LongestIncreasingSubsequence {
	
	public static void main(String[] args){
		
		int[] arr = {10, 22, 9, 33, 21, 50, 41, 60, 80};
		
		printLis(arr, arr.length);
	}

	/**
	 * Example:

		A[] = {3, 4, 1, 5}
		
		i=1 , LIS(1) = 1
		i=2 , LIS(2) = 1+ Max(LIS(1)) = 1+1 =2 (4>3)
		i=3 , LIS(3) = 1 (1<3, 1<4)
		i=4 , LIS(4) = 1+ Max(LIS(1),LIS(2), LIS(3))
					 = 1+ Max(1,2,1) = 3
	 */
	private static void printLis(int[] arr, int n) {
		
		int lis[] = new int[n];
        int i,j,max = 0;

        /* Initialize LIS values for all indexes */
         for ( i = 0; i < n; i++ )
            lis[i] = 1;

         /* Compute optimized LIS values in bottom up manner */
         for ( i = 1; i < n; i++ )
            for ( j = 0; j < i; j++ ) 
               if ( arr[i] > arr[j] && lis[i] < lis[j] + 1)
                  lis[i] = lis[j] + 1; //lis[i] = 1 + max(lis(0), lis(1) ...lis(i-1)) if arr[i]>arr[0],arr[1]....

         /* Pick maximum of all LIS values */
         for ( i = 0; i < n; i++ )
            if ( max < lis[i] )
               max = lis[i];

         System.out.println(max);
		
	}
	
	/*
	 * Optimised nlogn Solution:
	 * 
	 * 1. If A[i] is smallest among all end 
		   candidates of active lists, we will start 
		   new active list of length 1.
		2. If A[i] is largest among all end candidates of 
		  active lists, we will clone the largest active 
		  list, and extend it by A[i].
		
		3. If A[i] is in between, we will find a list with 
		  largest end element that is smaller than A[i]. 
		  Clone and extend this list by A[i]. We will discard all
		  other lists of same length as that of this modified list.
	 */
 
    static int LISOptimised(int A[], int size)
    {
        // Add boundary case, when array size is one
 
        int[] tailTable   = new int[size];
        int len; // always points empty slot
 
        tailTable[0] = A[0];
        len = 1;
        for (int i = 1; i < size; i++)
        {
            if (A[i] < tailTable[0])
                // new smallest value
                tailTable[0] = A[i];
 
            else if (A[i] > tailTable[len-1])
                // A[i] wants to extend largest subsequence
                tailTable[len++] = A[i];
 
            else
                // A[i] wants to be current end candidate of an existing
                // subsequence. It will replace ceil value in tailTable
                tailTable[CeilIndex(tailTable, -1, len-1, A[i])] = A[i];
        }
 
        return len;
    }
    
    /**
     * Binary search to find index m in tailTable such that A[m] > current value in A[]
     * Then replace A[m] with current value
     */
    static int CeilIndex(int A[], int l, int r, int key)
    {
        while (r - l > 1)
        {
            int m = l + (r - l)/2;
            if (A[m]>=key)
                r = m;
            else
                l = m;
        }
 
        return r;
    }

}
