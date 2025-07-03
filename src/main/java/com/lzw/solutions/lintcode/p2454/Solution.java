package com.lzw.solutions.lintcode.p2454;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Solution {

    class Worker implements Runnable {
        private List<Integer[]> list;
        private CountDownLatch countDownLatch;
        private int start;
        private int end;

        Worker(List<Integer[]> list, CountDownLatch countDownLatch, int start, int end) {
            this.list = list;
            this.countDownLatch = countDownLatch;
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            try {
                Integer[] integers = FindNumWithMostFactorsConcurrently.searchRange(start, end);
                list.add(integers);
                countDownLatch.countDown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int findNumWithMostFactors(int n, int m) throws Exception {
        CountDownLatch latch = new CountDownLatch(n);
        int len = (int) Math.ceil(m * 1.0 / n);
        List<Thread> workers = new ArrayList<>();
        List<Integer[]> list = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < n; i++) {
            int start = i * len + 1;
            int end = (i + 1) * len;
            if (end > m) {
                end = m;
            }
            workers.add(new Thread(new Worker(list, latch, start, end)));
        }
        workers.forEach(Thread::start);
        latch.await();
        int count = -1;
        int num = 0;
        for (Integer[] result : list) {
            if (result[0] > count || (result[0] == count && result[1] > num)) {
                count = result[0];
                num = result[1];
            }
        }
        return num;
    }
}