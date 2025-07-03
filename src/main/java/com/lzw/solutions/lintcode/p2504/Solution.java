package com.lzw.solutions.lintcode.p2504;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class Solution {

    public static void createLog() throws Exception {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        ArrayList<Future<?>> list = new ArrayList<>();
        for (int i = 1; i < 17; i++) {
            final int temp = i;
            Thread thread = new Thread(() -> {
                Main.parseLog(temp);
            });

            Future<?> future = executor.submit(thread);
            list.add(future);
        }
        for (Future<?> future : list) {
            future.get();
        }
        executor.shutdown();
    }
}
