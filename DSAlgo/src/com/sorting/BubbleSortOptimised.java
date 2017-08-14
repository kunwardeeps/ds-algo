package com.sorting;

/**
 * Optimised version of bubble sort. Stop sorting when swapping ceases in a pass.
 * 
 * @author kunwardeeps
 *
 */
class BubbleSortOptimised
{
    void bubbleSort(int arr[])
    {
    	boolean swapped;
        int n = arr.length;
        for (int i = 0; i < n-1; i++){
        	swapped = false;
            for (int j = 0; j < n-i-1; j++){
                if (arr[j] > arr[j+1])
                {
                    // swap arr[i+1] and arr[i]
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    swapped = true;
                }
            }
            if (swapped == false){ //This means rest array is already sorted
            	break;
            }
        }
    }
 
    /* Prints the array */
    void printArray(int arr[])
    {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
 
    // Driver method to test above
    public static void main(String args[])
    {
        BubbleSortOptimised ob = new BubbleSortOptimised();
        int arr[] = {64, 34, 25, 12, 22, 11, 90};
        ob.bubbleSort(arr);
        System.out.println("Sorted array");
        ob.printArray(arr);
    }
}