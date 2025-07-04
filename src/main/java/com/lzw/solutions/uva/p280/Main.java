package com.lzw.solutions.uva.p280;

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

    void solve() throws IOException {
        while (true) {
            String s = in.readLine();
            int n = Integer.parseInt(s);
            if (n == 0) {
                break;
            }
            boolean[][] grid = new boolean[n][n];
            while (true) {
                s = in.readLine();
                StringTokenizer st = new StringTokenizer(s);
                int count = st.countTokens();
                if (count == 1) {
                    Integer nums = Integer.parseInt(st.nextToken());
                    assert (nums == 0);
                    break;
                }
                int i = Integer.parseInt(st.nextToken());
                while (st.hasMoreTokens()) {
                    int j = Integer.parseInt(st.nextToken());
                    if (j == 0) {
                        break;
                    }
                    grid[i - 1][j - 1] = true;
                }
            }
            s = in.readLine();
            StringTokenizer st = new StringTokenizer(s);
            int count = Integer.parseInt(st.nextToken());
            for (int i = 0; i < count; i++) {
                int vi = Integer.parseInt(st.nextToken());
                boolean[] vis = new boolean[n];
                dfs(grid, vis, vi - 1, n);
                int unVisCount = 0;
                for (int j = 0; j < n; j++) {
                    if (!vis[j]) {
                        unVisCount++;
                    }
                }
                out.append(String.format("%d", unVisCount));
                for (int j = 0; j < n; j++) {
                    if (!vis[j]) {
                        out.append(String.format(" %d", j + 1));
                    }
                }
                out.append('\n');
            }
        }
    }

    void dfs(boolean[][] grid, boolean[] vis, int i, int n) {
        // vis[i] = true;
        for (int j = 0; j < n; j++) {
            if (grid[i][j] && !vis[j]) {
                vis[j] = true;
                dfs(grid, vis, j, n);
            }
        }
    }

    void close() throws IOException {
        if (in != null) {
            in.close();
        }
        if (out != null) {
            out.flush();
            out.close();
        }
    }

    public static void main(String[] args) throws Exception {

        Main main = new Main();
        main.solve();
        main.close();
    }
}
