package com.sorting;

/**
 *  Java program for implementation of Insertion Sort
 * @author kunwardeeps
 *
 */
class InsertionSort
{
    /*Function to sort array using insertion sort*/
    void sort(int arr[])
    {
        int n = arr.length;
        for (int i=1; i<n; ++i) //First element is assumed as fully sorted
        {
            int key = arr[i];
            int j = i-1; //To be compared with immediate left element
 
            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j>=0 && arr[j] > key)
            {
                arr[j+1] = arr[j]; //Right shift the array until we find appropriate place for the key (key > curr)
                j = j-1;
            }
            arr[j+1] = key;// j+1 since j is decremented in the last statement. 
        }
    }
 
    /* A utility function to print array of size n*/
    static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i] + " ");
 
        System.out.println();
    }
 
    // Driver method
    public static void main(String args[])
    {        
        int arr[] = {12, 11, 13, 5, 6};
 
        InsertionSort ob = new InsertionSort();        
        ob.sort(arr);
         
        printArray(arr);
    }
}