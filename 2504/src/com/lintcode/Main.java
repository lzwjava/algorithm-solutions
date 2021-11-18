package com.lintcode;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.lang.*;
import java.io.*;

public class Main {
    private static String mainThreadName;
    private static PrintStream printStream;
    private static Map<Integer, String> results = new ConcurrentHashMap<Integer, String>();

    public static void parseLog(int x) {
        try {
            Thread.sleep(1000);
            if (mainThreadName == Thread.currentThread().getName()) {
                Exception exception = new Exception("You should call this method in a sub thread.");
                throw exception;
            }
            results.put(x, "Log " + String.valueOf(x) + " has been recorded.\n");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            String outputPath = args[1];
            printStream = new PrintStream(outputPath);
            mainThreadName = Thread.currentThread().getName();
            Solution.createLog();
            for(Integer i= 1; i < 17; i++) {
                System.out.print((String)results.get(i));
            }
            for(Integer i= 1; i < 17; i++) {
                printStream.write(((String)results.get(i)).getBytes("Utf-8"));
            }
            printStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}