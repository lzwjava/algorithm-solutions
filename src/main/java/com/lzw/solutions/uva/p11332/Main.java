package com.lzw.solutions.uva.p11332;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {

    void work() {
        Scanner sc = new Scanner(System.in);
        for (; ; ) {
            int n = sc.nextInt();
            if (n == 0) {
                break;
            }
            for (; ; ) {
                int sum = 0;
                while (n != 0) {
                    sum += n % 10;
                    n /= 10;
                }
                n = sum;
                if (n < 10) {
                    break;
                }
            }
            System.out.println(n);
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {
        FileInputStream inStream = null;
        PrintStream outStream = null;
        boolean isLocal = System.getProperty("os.name").equals("Mac OS X");
        if (isLocal) {
            inStream = new FileInputStream("1.in");
            // outStream = new PrintStream("1.out");
            System.setIn(inStream);
            // System.setOut(outStream);
        }

        new Main().work();
    }
}
