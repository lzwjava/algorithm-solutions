package com.lzw.solutions.lintcode.p2496;

import java.io.*;
import java.util.*;

public class Main {
    private static PrintStream printStream;
    private static String mainThreadName;
    private static int i = 0;

    public static int increase(int number) throws Exception {
        Main.i = ++number;
        return Main.i;
    }

    public static int decrease(int number) throws Exception {
        Main.i = --number;
        return Main.i;
    }

    public static void main(String[] args) {
        try {
            String inputPath = args[0];
            String outputPath = args[1];
            Scanner in = new Scanner(new FileReader(inputPath));
            printStream = new PrintStream(outputPath);
            mainThreadName = Thread.currentThread().getName();
            int add1Count = 0;
            int add2Count = 0;
            int sub1Count = 0;
            int sub2Count = 0;
            while (in.hasNext()) {
                String operation = in.nextLine();
                operation = operation.substring(0, operation.length() - 2);
                if (operation.equals("add_1")) {
                    add1Count++;
                } else if (operation.equals("add_2")) {
                    add2Count++;
                } else if (operation.equals("sub_1")) {
                    sub1Count++;
                } else if (operation.equals("sub_2")) {
                    sub2Count++;
                }
            }
            final int add1Countfinal = add1Count;
            final int add2Countfinal = add2Count;
            final int sub1Countfinal = sub1Count;
            final int sub2Countfinal = sub2Count;
            final VariableModification instance = new VariableModification();
            List<Integer[]> getResultList = new LinkedList<Integer[]>();
            Thread[] threads = new Thread[4];
            threads[0] = new Thread(() -> {
                try {
                    for (int i = 0; i < add1Countfinal; i++) {
                        instance.add1();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            threads[1] = new Thread(() -> {
                try {
                    for (int i = 0; i < add2Countfinal; i++) {
                        instance.add2();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            threads[2] = new Thread(() -> {
                try {
                    for (int i = 0; i < sub1Countfinal; i++) {
                        instance.sub1();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            threads[3] = new Thread(() -> {
                try {
                    for (int i = 0; i < sub2Countfinal; i++) {
                        instance.sub2();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            for (int i = 0; i < 4; i++) {
                threads[i].start();
            }
            for (int i = 0; i < 4; i++) {
                threads[i].join();
            }
            if (Main.i != instance.checkI()) {
                Exception exception = new Exception("Don't cheat!");
                throw exception;
            } else {
                printStream.write(String.valueOf(Main.i).getBytes("Utf-8"));
            }
            printStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
