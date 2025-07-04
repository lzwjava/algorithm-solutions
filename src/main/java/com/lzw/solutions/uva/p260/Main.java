package com.lzw.solutions.uva.p260;

import java.io.*;
import java.util.ArrayList;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    char[][] grid;
    int n;
    boolean[][] vis;

    int[] dx = {-1, -1, 0, 0, 1, 1};
    int[] dy = {-1, 0, -1, 1, 0, 1};

    void dfs(int i, int j, char ch, ArrayList<Point> points) {
        vis[i][j] = true;
        points.add(new Point(i, j));
        for (int d = 0; d < dx.length; d++) {
            int ni = i + dx[d];
            int nj = j + dy[d];
            if (ni >= 0 && ni < n && nj >= 0 && nj < n && grid[ni][nj] == ch && !vis[ni][nj]) {
                dfs(ni, nj, ch, points);
            }
        }
    }

    class Point {
        int i, j;

        Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    void solve() throws IOException {
        int caseNum = 1;
        while (true) {
            n = Integer.parseInt(in.readLine());
            if (n == 0) {
                break;
            }
            grid = new char[n][n];
            for (int i = 0; i < n; i++) {
                String s = in.readLine();
                for (int j = 0; j < n; j++) {
                    grid[i][j] = s.charAt(j);
                }
            }
            vis = new boolean[n][n];

            char win = ' ';
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 'w' && !vis[i][j]) {
                        ArrayList<Point> points = new ArrayList<Point>();
                        dfs(i, j, 'w', points);
                        boolean[] cols = new boolean[n];
                        for (Point point : points) {
                            cols[point.j] = true;
                        }
                        boolean ok = true;
                        for (int u = 0; u < n; u++) {
                            if (!cols[u]) {
                                ok = false;
                                break;
                            }
                        }
                        if (ok) {
                            win = 'W';
                            break;
                        }
                    }
                }
                if (win != ' ') {
                    break;
                }
            }

            if (win == ' ') {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (grid[i][j] == 'b' && !vis[i][j]) {
                            ArrayList<Point> points = new ArrayList<Point>();
                            dfs(i, j, 'b', points);
                            boolean[] rows = new boolean[n];
                            for (Point point : points) {
                                rows[point.i] = true;
                            }
                            boolean ok = true;
                            for (int u = 0; u < n; u++) {
                                if (!rows[u]) {
                                    ok = false;
                                    break;
                                }
                            }
                            if (ok) {
                                win = 'B';
                                break;
                            }
                        }
                    }
                    if (win != ' ') {
                        break;
                    }
                }
            }

            out.append(String.format("%d %c\n", caseNum, win));
            caseNum++;
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
