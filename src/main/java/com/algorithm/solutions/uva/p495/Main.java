package com.algorithm.solutions.uva.p495;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.Scanner;

public class Main {
   
    void solve() {
        Scanner sc = new Scanner(System.in);
        int n = 5005;
        BigInteger fs[] = new BigInteger[n];
        fs[0] = new BigInteger("0");
        fs[1] = new BigInteger("1");
        for (int i = 2; i < n; i++) {
            fs[i] = fs[i - 1].add(fs[i - 2]);
        }
        while (sc.hasNextInt()) {
            int num = sc.nextInt();
            System.out.println(String.format("The Fibonacci number for %d is %s", num, fs[num].toString(10)));
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
