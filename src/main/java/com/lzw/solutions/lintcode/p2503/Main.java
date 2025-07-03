package com.lzw.solutions.lintcode.p2503;

import java.io.*;
import java.util.*;

public class Main {
    private static PrintStream printStream;
    private static int correctCounter = 0;
    private static int safeCounter = 0;

    public static int incr() {
        safeCounter++;
        return safeCounter;
    }

    public static int decr() {
        safeCounter--;
        return safeCounter;
    }

    public static void main(String[] args) {
        try {
            String inputPath = args[0];
            String outputPath = args[1];
            Scanner in = new Scanner(new FileReader(inputPath));
            printStream = new PrintStream(outputPath);
            List<String> operationList = new LinkedList<String>();
            while (in.hasNext()) {
                String operation = in.nextLine();
                operationList.add(operation.substring(0, operation.length() - 2));
            }
            final ThreadSafeCounter counter = new ThreadSafeCounter();
            if (operationList.get(0).getBytes().equals("ThreadSafeCounter".getBytes())) {
                printStream.write(operationList.get(0).getBytes("Utf-8"));
                printStream.write("\nInput is error.".getBytes("Utf-8"));
                return;
            }
            Thread[] threads = new Thread[2];
            int increaseCount = 0;
            int decreaseCount = 0;
            for (int i = 1; i < operationList.size(); i++) {
                if (operationList.get(i).equals("incr")) {
                    increaseCount++;
                } else if (operationList.get(i).equals("decr")) {
                    decreaseCount++;
                }
            }
            final int inCount = increaseCount;
            final int deCount = decreaseCount;
            threads[0] = new Thread(() -> {
                for (int i = 0; i < inCount; i++) {
                    counter.incr();
                }
            });
            threads[0].start();
            threads[1] = new Thread(() -> {
                for (int i = 0; i < deCount; i++) {
                    counter.decr();
                }
            });
            threads[1].start();
            for (int i = 0; i < 2; i++) {
                threads[i].join();
            }
            correctCounter = increaseCount - decreaseCount;
            if (correctCounter != safeCounter || safeCounter != counter.getCount()) {
                printStream.write("Your counter isn't a thread safe counter.".getBytes("Utf-8"));
            }
            printStream.write(String.valueOf(safeCounter).getBytes("Utf-8"));
            printStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
