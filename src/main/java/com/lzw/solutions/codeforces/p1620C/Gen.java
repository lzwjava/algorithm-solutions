package com.lzw.solutions.codeforces.p1620C;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class Gen {
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter out = new PrintWriter("1.in");
        int n = 2000;
        int k = 2000;
        long x = (long) 1e18;
        out.append(String.format("%d\n", 1));
        out.append(String.format("%d %d %d\n", n, k, x));
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (random.nextBoolean()) {
                sb.append('*');
            } else {
                sb.append('a');
            }
        }
        out.append(String.format("%s\n", sb));
        out.flush();
        out.close();
    }
}
