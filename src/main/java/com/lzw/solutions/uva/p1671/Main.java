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
            int an = Integer.parseInt(in.readLine());
            int[][] ax = new int[an][t];
            int[] af = new int[an];
            int i, j;
            for (i = 0; i < an; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                af[i] = Integer.parseInt(st.nextToken());
                for (j = 0; j < t; j++) {
                    ax[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int bn = Integer.parseInt(in.readLine());
            int[][] bx = new int[bn][t];
            int[] bf = new int[bn];
            for (i = 0; i < bn; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                bf[i] = Integer.parseInt(st.nextToken());
                for (j = 0; j < t; j++) {
                    bx[i][j] = Integer.parseInt(st.nextToken());
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        solve();
        out.close();
    }
}
