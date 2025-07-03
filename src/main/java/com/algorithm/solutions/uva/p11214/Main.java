package com.algorithm.solutions.uva.p11214;

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

    char[][] grid;
    int maxd;
    int n;
    int m;

    boolean guard(int[] cols, int row) {
        char[][] g = new char[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(g[i], '.');
        }
        for (int i = 0; i < row; i++) {
            if (cols[i] != -1) {
                for (int r = 0; r < n; r++) {
                    g[r][cols[i]] = 'X';
                }
                for (int c = 0; c < m; c++) {
                    g[i][c] = 'X';
                }
                for (int r = 0; r < n; r++) {
                    for (int c = 0; c < m; c++) {
                        if (r + c == i + cols[i] || r - c == i - cols[i]) {
                            g[r][c] = 'X';
                        }
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 'X' && g[i][j] != 'X') {
                    return false;
                }
            }
        }
        return true;
    }

    boolean dfs(int[] cols, int row, int d) {
        if (d >= maxd) {
            if (guard(cols, row)) {
                return true;
            }
            return false;
        }
        if (row == n) {
            return false;
        }
        cols[row] = -1;
        if (dfs(cols, row + 1, d)) {
            return true;
        }

        for (int i = 0; i < m; i++) {
            int c = 0;
            for (int j = 0; j < row; j++) {
                if (cols[j] == i) {
                    c++;
                }
            }
            if (c > 0) {
                continue;
            }
            cols[row] = i;
            if (dfs(cols, row + 1, d + 1)) {
                return true;
            }
        }
        return false;
    }

    void solve() throws IOException {
        int caseNum = 1;
        while (true) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            n = Integer.parseInt(st.nextToken());
            if (n == 0) {
                break;
            }
            m = Integer.parseInt(st.nextToken());
            grid = new char[n][m];
            for (int i = 0; i < n; i++) {
                String s = in.readLine();
                for (int j = 0; j < m; j++) {
                    grid[i][j] = s.charAt(j);
                }
            }
            for (int i = 0; i <= 5; i++) {
                maxd = i;
                int[] cols = new int[n];
                if (dfs(cols, 0, 0)) {
                    break;
                }
            }
            out.append(String.format("Case %d: %d\n", caseNum, maxd));
            caseNum++;
        }
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
}