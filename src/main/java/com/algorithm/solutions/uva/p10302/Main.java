package com.algorithm.solutions.uva.p10302;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
   
    void solve() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            long s = 0;
            for (long i = 1; i <= n; i++) {
                s += i * i * i;
            }
            System.out.println(s);
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

        new Main().solve();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
