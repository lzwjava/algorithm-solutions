package com.lzw.solutions.codeforces.p2198C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {
    private static final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static final PrintWriter out = new PrintWriter(System.out, true);

    private static void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        for (int test = 0; test < t; test++) {
            int n = Integer.parseInt(in.readLine());
            int[] p = new int[n];
            // Place 1 at the last position
            p[n - 1] = 1;
            // For positions corresponding to i = 2 to n-1, set p[i-1] = i ^ 1
            for (int pos = 1; pos < n - 1; pos++) {
                int i = pos + 1;
                p[pos] = i ^ 1;
            }
            // The missing number goes to the first position
            if (n % 2 == 0) {
                p[0] = n;
            } else {
                p[0] = n - 1;
            }
            // Output the permutation
            for (int i = 0; i < n; i++) {
                out.print(p[i]);
                if (i < n - 1) out.print(" ");
            }
            out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        solve();
        out.close();
    }
}