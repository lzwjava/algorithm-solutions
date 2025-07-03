package com.algorithm.solutions.uva.p10653;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main1 {

    BufferedReader in;
    PrintWriter out;

    Main1() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    boolean[][] grid;
    boolean[][] vis;
    int[][] dists;
    int r;
    int c;

    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};
    int minDist = 0;

    void dfs(int rs, int cs, int re, int ce, int dist) {
        if (rs == re && cs == ce) {
            if (dist < minDist) {
                minDist = dist;
            }
            return;
        }
        for (int i = 0; i < dx.length; i++) {
            int rn = rs + dx[i];
            int cn = cs + dy[i];
            if (rn >= 0 && rn < r && cn >= 0 && cn < c && !vis[rn][cn] && !grid[rn][cn]
                && dists[rn][cn] > dist + 1) {
                vis[rn][cn] = true;
                dists[rn][cn] = dist + 1;
                dfs(rn, cn, re, ce, dist + 1);
                vis[rn][cn] = false;
            }
        }
    }

    void solve() throws IOException {
        while (true) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            if (r == 0 && c == 0) {
                break;
            }
            int n = Integer.parseInt(in.readLine());
            grid = new boolean[r][c];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(in.readLine());
                int r1 = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());
                for (int j = 0; j < k; j++) {
                    int c1 = Integer.parseInt(st.nextToken());
                    grid[r1][c1] = true;
                }
            }
            st = new StringTokenizer(in.readLine());
            int rs = Integer.parseInt(st.nextToken());
            int cs = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(in.readLine());
            int re = Integer.parseInt(st.nextToken());
            int ce = Integer.parseInt(st.nextToken());
            vis = new boolean[r][c];
            minDist = Integer.MAX_VALUE;
            dists = new int[r][c];
            for (int i = 0; i < r; i++) {
                Arrays.fill(dists[i], Integer.MAX_VALUE);
            }

            dfs(rs, cs, re, ce, 0);

            out.append(String.format("%d\n", minDist));
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

        Main1 main = new Main1();
        main.solve();
        main.close();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
