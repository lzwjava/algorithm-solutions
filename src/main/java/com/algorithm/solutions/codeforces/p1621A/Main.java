package com.algorithm.solutions.codeforces.p1621A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

    void close() {
        out.flush();
        out.close();
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            StringTokenizer st = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            char[][] grid = new char[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(grid[i], '.');
            }
            int max;
            if (n % 2 == 0) {
                max = n / 2;
            } else {
                max = n / 2 + 1;
            }
            if (k <= max) {
                int min = Integer.min(k, max);
                for (int i = 0; i < min; i++) {
                    int x = 2 * i;
                    int y = 2 * i;
                    grid[x][y] = 'R';
                }
                for (int x = 0; x < n; x++) {
                    for (int y = 0; y < n; y++) {
                        out.append(grid[x][y]);
                    }
                    out.append('\n');
                }
            } else {
                out.append("-1\n");
            }
        }
    }
}