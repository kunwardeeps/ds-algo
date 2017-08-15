package com.hackerrank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main
{

    public static void main(String[] args)
    {

         List<String> list1 = new ArrayList<String>(Arrays.asList("A"));
         List<String> resultSet = new ArrayList<>(list1);
         List<String> list2 = new ArrayList<String>(Arrays.asList("B", "C", "D", "E", "F"));

         resultSet.retainAll(list2) ;

          System.out.println(list1);
          System.out.println(resultSet);
    }
}