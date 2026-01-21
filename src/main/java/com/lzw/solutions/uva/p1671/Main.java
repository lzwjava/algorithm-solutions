package com.lzw.solutions.uva.p1671;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {
    private static final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static final PrintWriter out = new PrintWriter(System.out, true);

    private static void solve() throws IOException {
        while (true) {
            int t = Integer.parseInt(in.readLine());
            if (t == 0) {
                break;
            }
            int n = Integer.parseInt(in.readLine());
            int[][] x = new int[n][t];
            int[] f = new int[n];
            int i, j;
            for (i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                f[i] = Integer.parseInt(st.nextToken());
                for (j = 0; j < t; j++) {
                    x[i][j] = Integer.parseInt(st.nextToken());
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        solve();
        out.close();
    }
}