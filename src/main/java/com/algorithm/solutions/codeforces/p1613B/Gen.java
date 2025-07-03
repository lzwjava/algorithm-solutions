package com.algorithm.solutions.codeforces.p1613B;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Random;

public class Gen {

    void solve() throws IOException {
        PrintStream outStream = new PrintStream("2.in");
        System.setOut(outStream);
        PrintWriter out = new PrintWriter(System.out);
        int t = 100;
        System.out.println(t);
        Random random = new Random();
        for (int i = 0; i < t; i++) {
            int n = 2000;
            System.out.println(n);
            for (int j = 0; j < n; j++) {
                if (j != 0) {
                    System.out.print(' ');
                }
                System.out.print(random.nextInt(10000) + 1);
            }
            System.out.println();
        }
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Gen().solve();
    }

}
