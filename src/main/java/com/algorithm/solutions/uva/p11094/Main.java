package com.algorithm.solutions.uva.p11094;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int m, n;
    char[][] grid;

    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    boolean[][] vis;
    int total;

    void dfs(int i, int j) {
        total++;
        vis[i][j] = true;
        for (int d = 0; d < 4; d++) {
            int ni = i + dx[d];
            int nj = (j + dy[d] + n) % n;
            if (ni >= 0 && ni < m && !vis[ni][nj] && grid[ni][nj] == land) {
                dfs(ni, nj);
            }
        }
    }

    char land;

    void solve() throws IOException {
        while (true) {
            String line = in.readLine();
            if (line == null) {
                break;
            }
            StringTokenizer st = new StringTokenizer(line);
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            grid = new char[m][n];
            for (int i = 0; i < m; i++) {
                String s = in.readLine();
                for (int j = 0; j < s.length(); j++) {
                    grid[i][j] = s.charAt(j);
                }
            }
            st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            vis = new boolean[m][n];
            total = 0;
            land = grid[x][y];
            dfs(x, y);

            int max = 0;

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (!vis[i][j] && grid[i][j] == land) {
                        total = 0;
                        dfs(i, j);
                        if (total > max) {
                            max = total;
                        }
                    }
                }
            }
            out.append(String.format("%d\n", max));
            in.readLine();
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
            inStream = new FileInputStream("2.in");
            outStream = new PrintStream("1.out");
            System.setIn(inStream);
            System.setOut(outStream);
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
