package com.lzw.solutions.uva.p10116;

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

    char[][] grid;
    int n, m;

    int[] dx = new int[] {-1, 1, 0, 0};
    int[] dy = new int[] {0, 0, 1, -1};

    void dfs(int sx, int sy, int[][] ds, int dist) {
        ds[sx][sy] = dist;
        String dirs = "NSEW";
        char ch = grid[sx][sy];
        int d = dirs.indexOf(ch);
        int nx = sx + dx[d];
        int ny = sy + dy[d];
        if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
            if (ds[nx][ny] == -1) {
                dfs(nx, ny, ds, dist + 1);
            } else {
                out.append(
                        String.format("%d step(s) before a loop of %d step(s)\n", ds[nx][ny], (dist + 1) - ds[nx][ny]));
            }
        } else {
            out.append(String.format("%d step(s) to exit\n", dist + 1));
        }
    }

    void solve() throws IOException {
        while (true) {
            int sx, sy;
            StringTokenizer st = new StringTokenizer(in.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            sy = Integer.parseInt(st.nextToken());
            if (n == 0 && m == 0 && sy == 0) {
                break;
            }
            grid = new char[n][m];
            for (int i = 0; i < n; i++) {
                String s = in.readLine();
                for (int j = 0; j < m; j++) {
                    grid[i][j] = s.charAt(j);
                }
            }
            sy--;
            sx = 0;
            int[][] ds = new int[n][m];
            for (int i = 0; i < n; i++) {
                Arrays.fill(ds[i], -1);
            }
            dfs(sx, sy, ds, 0);
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
            // outStream = new PrintStream("1.out");
            System.setIn(inStream);
            // System.setOut(outStream);
        }

        Main main = new Main();
        main.solve();
        main.close();
    }
}
