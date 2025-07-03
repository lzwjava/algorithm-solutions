package com.algorithm.solutions.uva.p10452;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    char[][] grid;
    int m;
    int n;

    // forth, ,left, right
    int[] dx = new int[]{-1, 1, 0, 0};
    int[] dy = new int[]{0, 0, -1, 1};

    boolean dfs(int sx, int sy, int tx, int ty, int p, ArrayList<Integer> path) {
        if (sx == tx && sy == ty) {
            return true;
        }
        for (int d = 0; d < dx.length; d++) {
            int nx = sx + dx[d];
            int ny = sy + dy[d];
            if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                if (grid[nx][ny] == "IEHOVA#".charAt(p)) {
                    path.add(d);
                    boolean ok = dfs(nx, ny, tx, ty, p + 1, path);
                    if (ok) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            grid = new char[m][n];
            int sx = 0, sy = 0, tx = 0, ty = 0;
            for (int i = 0; i < m; i++) {
                String s = in.readLine();
                for (int j = 0; j < n; j++) {
                    char ch = s.charAt(j);
                    grid[i][j] = ch;
                    if (ch == '@') {
                        sx = i;
                        sy = j;
                    } else if (ch == '#') {
                        tx = i;
                        ty = j;
                    }
                }
            }
            ArrayList<Integer> path = new ArrayList<Integer>();
            dfs(sx, sy, tx, ty, 0, path);
            for (int i = 0; i < path.size(); i++) {
                String[] strs = new String[]{"forth", "", "left", "right"};
                String str = strs[path.get(i)];
                if (i != 0) {
                    out.append(' ');
                }
                out.append(str);
            }
            out.append('\n');
            t--;
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
