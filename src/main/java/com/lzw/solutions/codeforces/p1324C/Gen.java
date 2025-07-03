package com.lzw.solutions.codeforces.p1324C;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Gen {
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter out = new PrintWriter("2.in");
        int t = 1;
        out.append(String.format("%d\n", t));
        int n = 200000;
        for (int i = 0; i < n; i++) {
            out.append(Math.random() < 0.99999 ? 'L' : 'R');
        }
        out.append('\n');
        out.flush();
        out.close();
    }
}
