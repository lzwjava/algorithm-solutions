package com.algorithm.solutions.codeforces.p1618C;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class Gen {
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter out = new PrintWriter("2.in");
        out.append(String.format("%d\n", 1));
        int n = 100;
        out.append(String.format("%d\n", n));
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            if (i != 0) {
                out.append(' ');
            }
            out.append(String.valueOf((long) (random.nextDouble() * 1e18)));
        }
        out.append('\n');
        out.flush();
        out.close();
    }
}