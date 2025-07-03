package com.lzw.solutions.uva.p352;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void solve() throws IOException {
        int caseNum = 1;
        while (true) {
            String line = in.readLine();
            if (line == null) {
                break;
            }
            int n = Integer.parseInt(line);
            int[][] grid = new int[n][n];
            for (int i = 0; i < n; i++) {
                line = in.readLine();
                for (int j = 0; j < line.length(); j++) {
                    grid[i][j] = line.charAt(j) - '0';
                }
            }
            boolean[][] vis = new boolean[n][n];
            int ans = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1 && !vis[i][j]) {
                        ans++;
                        dfs(n, grid, vis, i, j);
                    }
                }
            }
            out.append(String.format("Image number %d contains %d war eagles.\n", caseNum, ans));
            caseNum++;
        }
    }

    int[] dx = new int[] {-1, 0, 1, -1, 1, -1, 0, 1};
    int[] dy = new int[] {-1, -1, -1, 0, 0, 1, 1, 1};

    private void dfs(int n, int[][] grid, boolean[][] vis, int x, int y) {
        vis[x][y] = true;
        for (int d = 0; d < 8; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (nx >= 0 && nx < n && ny >= 0 && ny < n && !vis[nx][ny] && grid[nx][ny] == 1) {
                dfs(n, grid, vis, nx, ny);
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
        FileInputStream inStream = null;
        PrintStream outStream = null;
        boolean isLocal = System.getProperty("os.name").equals("Mac OS X");
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
