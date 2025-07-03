package com.lzw.solutions.lintcode.p2454;

import java.lang.*;

public class FindNumWithMostFactorsConcurrently {
    private static String mainThreadName;
    private static boolean initialFlag = true;
    private static int callCount = 0;

    public static int getCallCount() {
        return callCount;
    }

    public static final void setMainThreadName(String mainThreadName) {
        if (initialFlag) {
            FindNumWithMostFactorsConcurrently.mainThreadName = mainThreadName;
            initialFlag = false;
        }
    }

    public static Integer[] searchRange(int start, int end) throws Exception {
        if (mainThreadName == Thread.currentThread().getName()) {
            Exception exception = new Exception("You should call this method in a sub thread.");
            throw exception;
        }
        callCount++;
        Integer[] factors = new Integer[2];
        factors[0] = 0;
        factors[1] = 0;
        for (int i = start; i <= end; i++) {
            int factorsCount = 0;
            for (int j = 1; j <= i; j++) {
                if (i % j == 0)
                    factorsCount++;
            }
            if (factorsCount > factors[0] || factorsCount == factors[0] && i > factors[1]) {
                factors[0] = factorsCount;
                factors[1] = i;
            }
        }
        return factors;
    }
}