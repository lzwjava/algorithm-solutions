package com.algorithm.solutions.uva.p11953;

import java.io.*;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    char[][] grid;
    int n;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    boolean[][] vis;

    void dfs(int i, int j) {
        for (int d = 0; d < dx.length; d++) {
            int ni = dx[d] + i;
            int nj = dy[d] + j;
            if (ni >= 0 && ni < n && nj >= 0 && nj < n && !vis[ni][nj] && grid[ni][nj] != '.') {
                vis[ni][nj] = true;
                dfs(ni, nj);
            }
        }
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        for (int u = 0; u < t; u++) {
            n = Integer.parseInt(in.readLine());
            grid = new char[n][n];
            for (int i = 0; i < n; i++) {
                String s = in.readLine();
                for (int j = 0; j < n; j++) {
                    grid[i][j] = s.charAt(j);
                }
            }
            int count = 0;
            vis = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 'x' && !vis[i][j]) {
                        vis[i][j] = true;
                        dfs(i, j);
                        count++;
                    }
                }
            }
            out.append(String.format("Case %d: %d\n", u + 1, count));
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
        FileInputStream inStream = null;
        PrintStream outStream = null;
        boolean isLocal = System.getenv("LOCAL_JUDGE") != null;
        if (isLocal) {
            inStream = new FileInputStream("1.in");
            // outStream = new PrintStream("1.out");
            System.setIn(inStream);
            // System.setOut(outStream);
        }

        Main main = new Main();
        main.solve();
        main.close();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
