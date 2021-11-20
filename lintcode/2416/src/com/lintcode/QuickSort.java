package com.lintcode;

public class QuickSort {
    public static boolean hasBeenCalledInSubThread = false;

    public void sort(int arr[], int begin, int end) throws Exception {
        sortRange(arr, begin, end);
    }

    public void sortRange(int arr[], int start, int end) throws Exception {
        String name = Thread.currentThread().getName();
        if ("main".equals(name)) {
            throw new Exception("You should call sort_range in a sub thread.");
        }
        hasBeenCalledInSubThread = true;

        if (start >= end) {
            return;
        }
        int num = arr[start], left = start, right = end;
        while (left < right) {
            while (left < right && arr[right] >= num) {
                right--;
            }
            while (left < right && arr[left] <= num) {
                left++;
            }
            if (left < right) {
                int x = arr[left];
                arr[left] = arr[right];
                arr[right] = x;
            }
        }
        arr[start] = arr[left];
        arr[left] = num;
        sortRange(arr, start, left - 1);
        sortRange(arr, left + 1, end);
    }

}