package com.lzw.solutions.lintcode.p2454;

import java.util.*;
import java.util.function.Consumer;
import java.lang.*;
import java.io.*;

public class Main {
    private static PrintStream printStream;

    public static void main(String[] args) {
        try {
            String inputPath = args[0];
            String outputPath = args[1];
            Scanner in = new Scanner(new FileReader(inputPath));
            int n = Integer.parseInt(in.nextLine());
            int m = Integer.parseInt(in.nextLine());
            printStream = new PrintStream(outputPath);
            FindNumWithMostFactorsConcurrently.setMainThreadName(Thread.currentThread().getName());
            Solution solution = new Solution();
            int mostFactorsNum = solution.findNumWithMostFactors(n, m);
            if (FindNumWithMostFactorsConcurrently.getCallCount() != 0)
                printStream.write(String.valueOf(mostFactorsNum).getBytes("Utf-8"));
            else
                printStream.write("You must call function FindNumWithMostFactorsConcurrently.searchRange.".getBytes("Utf-8"));
            printStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}