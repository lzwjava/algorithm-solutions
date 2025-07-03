package com.lzw.solutions.codeforces.p1391B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

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

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            StringTokenizer st = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            char[][] grid = new char[n][m];
            for (int i = 0; i < n; i++) {
                String s = in.readLine();
                for (int j = 0; j < m; j++) {
                    grid[i][j] = s.charAt(j);
                }
            }
            int c = 0;
            for (int i = 0; i < n; i++) {
                if (grid[i][m - 1] == 'R') {
                    c++;
                }
            }
            for (int j = 0; j < m; j++) {
                if (grid[n - 1][j] == 'D') {
                    c++;
                }
            }
            out.append(String.format("%d\n", c));
        }
    }
}
