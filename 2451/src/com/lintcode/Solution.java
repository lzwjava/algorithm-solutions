package com.lintcode;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class Solution {

    public void runSumInThread(int n, Consumer<Long[]> calculateRangeSum) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        int len = (int) Math.ceil(n * 1.0 / 10);
        for (int i = 0; i < 10; i++) {
            int start = i * len + 1;
            int end = (i + 1) * len;
            if (end > n) {
                end = n;
            }
            int finalEnd = end;
            executor.submit(() -> {
                calculateRangeSum.accept(new Long[]{(long) start, (long) finalEnd});
            });
        }
        try {
            executor.shutdown();
            executor.awaitTermination(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}