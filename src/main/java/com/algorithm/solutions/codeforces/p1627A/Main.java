package com.algorithm.solutions.codeforces.p1627A;

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
            int m = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            char[][] grid = new char[n][m];
            boolean allWhite = true;
            for (int i = 0; i < n; i++) {
                String s = in.readLine();
                for (int j = 0; j < m; j++) {
                    char ch = s.charAt(j);
                    grid[i][j] = ch;
                    if (ch == 'B') {
                        allWhite = false;
                    }
                }
            }
            int ans;
            if (allWhite) {
                ans = -1;
            } else {
                if (grid[r][c] == 'B') {
                    ans = 0;
                } else {
                    boolean rowBlack = false;
                    for (int i = 0; i < n; i++) {
                        if (grid[i][c] == 'B') {
                            rowBlack = true;
                            break;
                        }
                    }
                    boolean columnBlack = false;
                    for (int j = 0; j < m; j++) {
                        if (grid[r][j] == 'B') {
                            columnBlack = true;
                            break;
                        }
                    }
                    if (rowBlack || columnBlack) {
                        ans = 1;
                    } else {
                        ans = 2;
                    }
                }
            }
            out.append(String.format("%d\n", ans));
        }
    }
}