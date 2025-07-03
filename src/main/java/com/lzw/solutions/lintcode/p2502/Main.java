package com.lzw.solutions.lintcode.p2502;

import java.io.*;
import java.util.*;

public class Main {
    private static PrintStream printStream;

    public static void main(String[] args) {
        try {
            String inputPath = args[0];
            String outputPath = args[1];
            Scanner in = new Scanner(new FileReader(inputPath));
            printStream = new PrintStream(outputPath);
            List<Integer> appendOperationList = new LinkedList<Integer>();
            List<Integer> getOperationList = new LinkedList<Integer>();
            Map<Integer, Integer> map = new Hashtable<Integer, Integer>();
            while (in.hasNext()) {
                String operation = in.nextLine();
                operation = operation.substring(0, operation.length() - 1);
                String[] operations = operation.split("\\(");
                if (operations[0].equals("append")) {
                    int x = Integer.parseInt(operations[1]);
                    appendOperationList.add(x);
                    if (map.containsKey(x)) {
                        map.replace(x, map.get(x) + 1);
                    } else {
                        map.put(x, 1);
                    }
                } else if (operations[0].equals("get")) {
                    getOperationList.add(Integer.parseInt(operations[1]));
                }
            }
            final ThreadSafeArrayList array = new ThreadSafeArrayList();
            List<Integer[]> getResultList = new LinkedList<Integer[]>();
            Thread[] appendThreads = new Thread[15];
            Thread[] getThreads = new Thread[5];
            for (int i = 0; i < 5; i++) {
                appendThreads[i] = null;
                getThreads[i] = null;
            }
            for (int i = 5; i < 15; i++) {
                appendThreads[i] = null;
            }
            for (int i = 0; i < 15; i++) {
                final int temp = i;
                appendThreads[i] = new Thread(() -> {
                    try {
                        for (int j = 0; j < appendOperationList.size(); j++) {
                            if (j % 15 == temp) array.append(appendOperationList.get(j));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                appendThreads[i].start();
            }
            for (int i = 0; i < 5; i++) {
                final int temp = i;
                getThreads[i] = new Thread(() -> {
                    try {
                        for (int j = 0; j < getOperationList.size(); j++) {
                            if (j % 5 == temp) {
                                int x = array.get(getOperationList.get(j));
                                getResultList.add(new Integer[] {getOperationList.get(j), x});
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                getThreads[i].start();
            }
            for (int i = 0; i < 15; i++) {
                if (appendThreads[i] != null) appendThreads[i].join();
                if (i < 5 && getThreads[i] != null) getThreads[i].join();
            }
            // test code
            int totalCounts = 0;
            Map<Integer, Integer> solutionDataMap = new Hashtable<Integer, Integer>();
            for (int i = 0; i < array.size(); i++) {
                int x = array.get(i);
                if (solutionDataMap.containsKey(x)) {
                    solutionDataMap.replace(x, solutionDataMap.get(x) + 1);
                } else {
                    solutionDataMap.put(x, 1);
                }
            }
            for (Integer i : map.keySet()) {
                totalCounts += map.get(i);
            }
            if (totalCounts != array.size()) {
                printStream.write("Your array ignored some datas:\n".getBytes("Utf-8"));
                printStream.write(("Your array's size is" + String.valueOf(array.size()) + "\n").getBytes("Utf-8"));
                printStream.write(("The real array's size is" + String.valueOf(totalCounts) + "\n").getBytes("Utf-8"));

                String str = "(";
                for (int i = 0; i < array.size(); i++) {
                    str += String.valueOf(array.get(i));
                    if (i != array.size() - 1) str += ", ";
                }
                str += ")";
                printStream.write(str.getBytes("Utf-8"));
            } else {
                boolean flag = true;
                for (Integer i : solutionDataMap.keySet()) {
                    if (solutionDataMap.get(i) != map.get(i)) {
                        flag = false;
                        printStream.write(("Your number-" + String.valueOf(i) + " counts is "
                                        + String.valueOf(solutionDataMap.get(i)) + "\n")
                                .getBytes("Utf-8"));
                        printStream.write(("The real number-" + String.valueOf(i) + " counts is " + map.get(i) + "\n")
                                .getBytes("Utf-8"));
                        break;
                    }
                }
                if (flag) {
                    printStream.write("accepted".getBytes("Utf-8"));
                } else {
                    printStream.write("Your array isn't correct:\n".getBytes("Utf-8"));
                    String str = "(";
                    for (int i = 0; i < array.size(); i++) {
                        str += String.valueOf(array.get(i));
                        if (i != array.size() - 1) str += ", ";
                    }
                    str += ")";
                    printStream.write(str.getBytes("Utf-8"));
                }
            }
            printStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
