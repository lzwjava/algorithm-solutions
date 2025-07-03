package com.lzw.solutions.lintcode.p2453;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.Consumer;

public class Solution {
    public void runSumInThread(int n, List<Integer> arr, Consumer<int[]> appendTheRangeAnswer) throws Exception {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        int len = (int) Math.ceil(arr.size() * 1.0 / n);
        ArrayList<Future<?>> futures = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int start = i * len;
            int end = (i + 1) * len;
            if (end > arr.size()) {
                end = arr.size();
            }
            int finalEnd = end;
            Future<?> future = executor.submit(() -> {
                int sum = 0;
                for (int j = start; j < finalEnd; j++) {
                    sum += arr.get(j);
                }
                appendTheRangeAnswer.accept(new int[]{start, finalEnd, sum});
            });
            futures.add(future);
        }
        for (Future future : futures) {
            future.get();
        }
        executor.shutdown();
    }
}