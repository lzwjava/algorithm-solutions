package com.lzw.solutions.uva.p10596;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void dfs(int x, int n) {
        vis[x] = true;
        for (int i = 0; i < n; i++) {
            if (grid[x][i] && !vis[i]) {
                dfs(i, n);
            }
        }
    }

    int[] deg;
    boolean[] vis;
    boolean[][] grid;

    void solve() throws IOException {
        while (true) {
            String line = in.readLine();
            if (line == null) {
                break;
            }
            StringTokenizer st = new StringTokenizer(line);
            int n = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            deg = new int[n];
            vis = new boolean[n];
            Arrays.fill(vis, true);
            grid = new boolean[n][n];
            for (int i = 0; i < r; i++) {
                st = new StringTokenizer(in.readLine());
                int c1 = Integer.parseInt(st.nextToken());
                int c2 = Integer.parseInt(st.nextToken());
                deg[c1]++;
                deg[c2]++;
                grid[c1][c2] = grid[c2][c1] = true;
                vis[c1] = vis[c2] = false;
            }
            boolean ok = true;
            for (int i = 0; i < n; i++) {
                if (deg[i] % 2 == 1) {
                    ok = false;
                    break;
                }
            }
            if (!ok) {
                out.append(String.format("Not Possible\n"));
            } else {
                int c = 0;
                for (int i = 0; i < n; i++) {
                    if (!vis[i]) {
                        dfs(i, n);
                        c++;
                        if (c > 1) {
                            break;
                        }
                    }
                }
                if (c == 1) {
                    out.append("Possible\n");
                } else {
                    out.append("Not Possible\n");
                }
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
        boolean isLocal = System.getenv("LOCAL_JUDGE") != null;
        if (isLocal) {
            inStream = new FileInputStream("1.in");
            //            outStream = new PrintStream("2.out");
            System.setIn(inStream);
            //            System.setOut(outStream);
        }

        Main main = new Main();
        main.solve();
        main.close();
    }
}
