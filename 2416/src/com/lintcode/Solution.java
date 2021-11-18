package com.lintcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class Solution {

    public int[] quickSortInThreadings(int n, int[] arr) throws Exception {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        int len = (int) Math.ceil(arr.length * 1.0 / n);
        ArrayList<Future<?>> futures = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int start = i * len;
            int end = (i + 1) * len;
            if (end > arr.length) {
                end = arr.length;
            }
            int[] arr1 = Arrays.copyOfRange(arr, start, end);
            Future<?> future = executor.submit(() -> {
                try {
                    new QuickSort().sort(arr1, 0, arr1.length - 1);
                    return arr1;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            });
            futures.add(future);
        }
        ArrayList<Object> list = new ArrayList<>();
        for (Future<?> future : futures) {
            Object o = future.get();
            list.add(o);
        }
        executor.shutdown();
        return mergeNSortedArrays(list);
    }

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