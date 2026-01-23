package com.lzw.solutions.codeforces.p2189A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {
    private static final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static final PrintWriter out = new PrintWriter(System.out, true);

    private static void solve() throws IOException {
        String[] first = in.readLine().split(" ");
        int n = Integer.parseInt(first[0]);
        int h = Integer.parseInt(first[1]);
        int l = Integer.parseInt(first[2]);

        String[] line = in.readLine().split(" ");
        int cntRow = 0;
        int cntCol = 0;

        for (String s : line) {
            int x = Integer.parseInt(s);
            if (x <= h) cntRow++;
            if (x <= l) cntCol++;
        }

        int maxPairs = Math.min(cntRow, cntCol);
        maxPairs = Math.min(maxPairs, n / 2);

        out.println(maxPairs);
    }

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            solve();
        }
        out.close();
    }
}