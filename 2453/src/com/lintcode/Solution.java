package com.lintcode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

public class Solution {
    public void runSumInThread(int n, List<Integer> arr, Consumer<int[]> appendTheRangeAnswer) throws Exception {
        CountDownLatch latch = new CountDownLatch(n);
        int len = (int) Math.ceil(arr.size() / n);
        List<Thread> workers = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            int start = i * len;
            int end = (i + 1) * len;
            if (end > arr.size()) {
                end = arr.size();
            }

            int finalEnd = end;
            Thread thread = new Thread(() -> {
                int sum = 0;
                for (int j = start; j < finalEnd; j++) {
                    sum += arr.get(j);
                }
                appendTheRangeAnswer.accept(new int[]{start, finalEnd, sum});
                latch.countDown();
            });
            workers.add(thread);
        }
        latch.await();
    }
}