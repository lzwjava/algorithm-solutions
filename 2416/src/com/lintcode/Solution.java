package com.lintcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public int[] quickSortInThreadings(int n, int[] arr) throws Exception {
        // write your code here
        return arr;
    }

    // Put the arrays to be merged into the list
    public int[] mergeNSortedArrays(List<Object> list) throws Exception {
        List<Integer> ls = new ArrayList<>();
        for (Object oj : list) {
            if (oj == null) continue;
            int arr[] = (int[]) oj;
            for (int num : arr) {
                ls.add(num);
            }
        }
        int[] arr = ls.stream().mapToInt(Integer::valueOf).toArray();
        Arrays.sort(arr);
        return arr;
    }
}