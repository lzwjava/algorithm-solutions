package com.algorithm.solutions.codeforces.p1335D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void close() throws IOException {
        in.close();
        out.flush();
        out.close();
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            int n = 9;
            int[][] grid = new int[n][n];
            for (int i = 0; i < n; i++) {
                String s = in.readLine();
                for (int j = 0; j < n; j++) {
                    int v = s.charAt(j) - '0';
                    grid[i][j] = v;
                }
            }
            int[] cols = new int[]{0, 3, 6, 1, 4, 7, 2, 5, 8};

            for (int i = 0; i < cols.length; i++) {
                int v = grid[i][cols[i]];
                grid[i][cols[i]] = v % n + 1;
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    out.append(String.format("%d", grid[i][j]));
                }
                out.append('\n');
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}