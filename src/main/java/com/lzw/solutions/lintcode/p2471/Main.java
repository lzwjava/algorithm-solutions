package com.lzw.solutions.lintcode.p2471;

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    private static PrintStream printStream;
    private static String listStr = null;

    private static Map<Integer, Integer> addMapData(Map<Integer, Integer> map, int x) {
        if (map.containsKey(x)) {
            map.replace(x, map.get(x) + 1);
        } else {
            map.put(x, 1);
        }
        return map;
    }

    public static void main(String[] args) {
        try {
            String inputPath = args[0];
            String outputPath = args[1];
            Scanner in = new Scanner(new FileReader(inputPath));
            printStream = new PrintStream(outputPath);
            List<Integer> appendLeftList = new LinkedList<Integer>();
            List<Integer> appendRightList = new LinkedList<Integer>();
            Map<Integer, Integer> resultLeftList = new Hashtable<Integer, Integer>();
            Map<Integer, Integer> resultRightList = new Hashtable<Integer, Integer>();
            while (in.hasNext()) {
                String operation = in.nextLine();
                operation = operation.substring(0, operation.length() - 1);
                String[] operations = operation.split("\\(");
                if (operations[0].equals("append_left")) {
                    int x = Integer.parseInt(operations[1]);
                    appendLeftList.add(x);
                    resultLeftList = addMapData(resultLeftList, x);
                } else if (operations[0].equals("append_right")) {
                    int x = Integer.parseInt(operations[1]);
                    appendRightList.add(Integer.parseInt(operations[1]));
                    resultRightList = addMapData(resultRightList, x);
                }
            }
            final ThreadSafeLinkedList list = new ThreadSafeLinkedList();
            final int threadSize = 5;
            Thread[] appendLeftThreads = new Thread[threadSize];
            Thread[] appendRightThreads = new Thread[threadSize];
            for (int i = 0; i < threadSize; i++) {
                appendLeftThreads[i] = null;
                appendRightThreads[i] = null;
            }
            for (int i = 0; i < threadSize; i++) {
                final int temp = i;
                appendLeftThreads[i] = new Thread(() -> {
                    try {
                        for (int j = 0; j < appendLeftList.size(); j++) {
                            if (j % threadSize == temp) {
                                list.appendLeft(appendLeftList.get(j));
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                appendLeftThreads[i].start();
            }
            for (int i = 0; i < threadSize; i++) {
                final int temp = i;
                appendRightThreads[i] = new Thread(() -> {
                    try {
                        for (int j = 0; j < appendRightList.size(); j++) {
                            if (j % threadSize == temp) {
                                list.appendRight(appendRightList.get(j));
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                appendRightThreads[i].start();
            }
            for (int i = 0; i < 5; i++) {
                appendLeftThreads[i].join();
                appendRightThreads[i].join();
            }
            listStr = ListNode.getListString(list.getLinkedList());
            //test code
            Map<Integer, Integer> solutionLeftMap = new Hashtable<Integer, Integer>();
            Map<Integer, Integer> solutionRightMap = new Hashtable<Integer, Integer>();
            ListNode node = list.getLinkedList();
            for (int i = 0; i < appendLeftList.size(); i++) {
                solutionLeftMap = addMapData(solutionLeftMap, node.getVal());
                node = node.getNext();
            }
            for (int i = 0; i < appendRightList.size(); i++) {
                solutionRightMap = addMapData(solutionRightMap, node.getVal());
                node = node.getNext();
            }
            if (resultLeftList.equals(solutionLeftMap) && resultRightList.equals(solutionRightMap)) {
                printStream.write("accepted".getBytes("Utf-8"));
            } else {
                printStream.write(ListNode.getListString(list.getLinkedList()).getBytes("Utf-8"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                printStream.write(listStr.getBytes("Utf-8"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } finally {
            printStream.close();
        }
    }
}