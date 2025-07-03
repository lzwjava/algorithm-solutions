package com.algorithm.solutions.uva.p202;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {

    private static void work(){
        Scanner sc = new Scanner(System.in);

        for (;;) {
            if (!sc.hasNextInt()) {
                break;
            }
            int numerator = sc.nextInt();
            int denominator = sc.nextInt();
            int size = 3000;
            int nums[] = new int[size];
            int positive = (int) (numerator / denominator);
            int res = numerator % denominator;
            int i;
            // System.out.println(positive);
            for (i = 0; i < size; i++) {
                if (res == 0) {
                    break;
                }
                nums[i] = (int) (res * 10 / denominator);
                res = (res * 10) % denominator;
            }         

            if (i < size) {
                System.out.print(String.format("%d/%d = %d.", numerator, denominator, positive));                
                for (int j = 0; j < i; j++) {
                    System.out.print(nums[j]);
                }
                System.out.println("(0)");
                System.out.println("   1 = number of digits in repeating cycle");
            } else {
                boolean found = false;
                int start = 0, end = 0;

                for (end = 1; end < size; end++) {
                    for (start = 0; start < end; start++) {
                        // System.out.println("start " + start +" end " + end);                        
                        boolean ok = true;
                        int len = end - start;
                        for (int j = start + len; j < size; j++) {
                            if (nums[j] != nums[start + (j - start) % len]) {
                                ok = false;
                                break;
                            }
                        }
                        if (ok) {
                            found = true;
                            break;
                        }
                    }
                    if (found) {
                        break;
                    }
                }
                if (found) {
                    int len = end - start;
                    System.out.print(String.format("%d/%d = %d.", numerator, denominator, positive));
                    int j;
                    for (j = 0; j < 50; j++) {
                        if (j < start) {
                            System.out.print(nums[j]);
                        } else if (j == start) {
                            System.out.print("(");
                            System.out.print(nums[j]);
                        } else if (j < start + len) {
                            System.out.print(nums[j]);
                        } else if (j == start + len) {
                            System.out.print(")");
                            break;
                        }
                    }
                    if (j == 50) {
                        System.out.print("...)");
                    }
                    System.out.println();
                    System.out.println(String.format("   %d = number of digits in repeating cycle", len));
                }
            }
            System.out.println();
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {
        FileInputStream inStream = null;
        PrintStream outStream = null;
        boolean isLocal = System.getProperty("os.name").equals("Mac OS X");        
        if (isLocal) {
            inStream = new FileInputStream("1.in");
            outStream = new PrintStream("1.out");
            System.setIn(inStream);
            // System.setOut(outStream);
        }

        work();

        if (isLocal) {
            inStream.close();
            outStream.close();
        }
    }
}
