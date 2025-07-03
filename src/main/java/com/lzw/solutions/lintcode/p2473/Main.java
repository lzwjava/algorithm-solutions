package com.lzw.solutions.lintcode.p2473;

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    private static PrintStream printStream;

    private static Map<Integer, Integer> addMapData(Map<Integer, Integer> map, int x) {
        if (map.containsKey(x)) {
            map.replace(x, map.get(x) + 1);
        } else {
            map.put(x, 1);
        }
        return map;
    }

    private static Map<Integer, Integer> addMapData(Map<Integer, Integer> map, int x, int y) {
        if (map.containsKey(x)) {
            map.replace(x, y);
        } else {
            map.put(x, y);
        }
        return map;
    }

    public static void main(String[] args) {
        try {
            String inputPath = args[0];
            String outputPath = args[1];
            Scanner in = new Scanner(new FileReader(inputPath));
            printStream = new PrintStream(outputPath);
            List<Integer[]> putOperationList = new LinkedList<Integer[]>();
            List<Integer> getOperationList = new LinkedList<Integer>();
            Map<Integer, Integer> map = new Hashtable<Integer, Integer>();
            Map<Integer, Integer> getResultValueMap = new Hashtable<Integer, Integer>();
            int baseSize = 0;
            while (in.hasNext()) {
                String operation = in.nextLine();
                operation = operation.substring(0, operation.length() - 1);
                String[] operations = operation.split("\\(");
                if (operations[0].equals("put")) {
                    String[] values = operations[1].split(", ");
                    int key = Integer.parseInt(values[0]);
                    int value = Integer.parseInt(values[1]);
                    putOperationList.add(new Integer[]{key, value});
                    addMapData(map, key);
                } else if (operations[0].equals("get")) {
                    getOperationList.add(Integer.parseInt(operations[1]));
                } else if (operations[0].equals("ThreadSafeHashMap")) {
                    baseSize = Integer.parseInt(operations[1]);
                }
            }
            final AbstractHashMap hashMap = new ThreadSafeHashMap(baseSize);
            final int threadSize = 5;
            Thread[] putThreads = new Thread[threadSize];
            Thread[] getThreads = new Thread[threadSize];
            for (int i = 0; i < threadSize; i++) {
                putThreads[i] = null;
                getThreads[i] = null;
            }
            for (int i = 0; i < threadSize; i++) {
                final int temp = i;
                putThreads[i] = new Thread(() -> {
                    try {
                        for (int j = 0; j < putOperationList.size(); j++) {
                            if (j % threadSize == temp) {
                                hashMap.put(putOperationList.get(j)[0], putOperationList.get(j)[1]);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                putThreads[i].start();
            }
            for (int i = 0; i < threadSize; i++) {
                final int temp = i;
                getThreads[i] = new Thread(() -> {
                    try {
                        for (int j = 0; j < getOperationList.size(); j++) {
                            if (j % threadSize == temp) {
                                addMapData(getResultValueMap, (int) getOperationList.get(j), (int) hashMap.get(getOperationList.get(j)));
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                getThreads[i].start();
            }
            for (int i = 0; i < 5; i++) {
                putThreads[i].join();
                getThreads[i].join();
            }
            //test code
            Set<Integer> keySet = hashMap.keySet();
            if (keySet.equals(map.keySet())) {
                printStream.write("accepted".getBytes("Utf-8"));
            } else {
                String mapString = "It's your hash table that missed something:\n";
                for (Integer i : keySet) {
                    mapString += "<";
                    mapString += String.valueOf(i);
                    mapString += ", ";
                    mapString += String.valueOf(hashMap.get(i));
                    mapString += "> ";
                }
                printStream.write(mapString.getBytes("Utf-8"));
            }
            printStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}