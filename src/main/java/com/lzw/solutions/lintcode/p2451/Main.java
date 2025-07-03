package com.lzw.solutions.lintcode.p2451;

import java.util.*;
import java.io.*;
import java.lang.*;
import java.util.function.Consumer;

public class Main {
    static List<Long> nums = new LinkedList<>();
    private static String mainThreadName;
    private static PrintStream ps;

    public static void main(String[] args) {
        try {
            String inputPath = args[0];
            String outputPath = args[1];
            Scanner in = new Scanner(new FileReader(inputPath));
            int n = Integer.parseInt(in.nextLine());
            ps = new PrintStream(outputPath);
            mainThreadName = Thread.currentThread().getName();
            Consumer<Long[]> calculateRangeSum = (Long[] x) -> {
                try {
                    if (mainThreadName == Thread.currentThread().getName()) {
                        Exception exception = new Exception("You should call this method in a sub thread.");
                        throw exception;
                    }
                    Long total = 0L;
                    for (Long i = x[0]; i <= x[1]; i++) {
                        total += i;
                    }
                    nums.add(total);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.exit(1);
                }
            };
            Solution solution = new Solution();
            solution.runSumInThread(n, calculateRangeSum);
            Long nums_sum = 0L;
            for (int i = 0; i < nums.size(); i++) {
                nums_sum += nums.get(i);
            }
            if (nums_sum != (1 + n) * n / 2 && nums.size() != 10) {
                Exception exception = new Exception("You should use 10 threads to calculate the answer and wait until all threads are finished.");
                throw exception;
            }
            if (nums.size() != 10) {
                Exception exception = new Exception("You should use 10 threads to calculate the answer.");
                throw exception;
            }
            ps.write(String.valueOf(nums_sum).getBytes("Utf-8"));
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}