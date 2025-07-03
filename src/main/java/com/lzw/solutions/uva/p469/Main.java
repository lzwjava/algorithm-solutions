package com.lzw.solutions.uva.p469;

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

    int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
    int n, m;
    char[][] grid;
    boolean[][] vis;
    int total;

    void dfs(int i, int j) {
        total++;
        vis[i][j] = true;
        for (int d = 0; d < dx.length; d++) {
            int ni = i + dx[d];
            int nj = j + dy[d];
            if (ni >= 0 && ni < n && nj >= 0 && nj < m && !vis[ni][nj] && grid[ni][nj] == 'W') {
                dfs(ni, nj);
            }
        }
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        in.readLine();
        while (t > 0) {
            ArrayList<String> lines = new ArrayList<String>();
            String line = in.readLine();
            while (true) {
                if (!Character.isAlphabetic(line.charAt(0))) {
                    break;
                }
                lines.add(line);
                line = in.readLine();
            }
            n = lines.size();
            m = lines.get(0).length();
            grid = new char[n][m];
            for (int u = 0; u < n; u++) {
                String s = lines.get(u);
                for (int v = 0; v < s.length(); v++) {
                    grid[u][v] = s.charAt(v);
                }
            }
            while (true) {
                if (line == null || line.isEmpty()) {
                    break;
                }
                StringTokenizer st = new StringTokenizer(line);
                int i = Integer.parseInt(st.nextToken()) - 1;
                int j = Integer.parseInt(st.nextToken()) - 1;
                vis = new boolean[n][m];
                total = 0;
                dfs(i, j);
                out.append(String.format("%d\n", total));
                line = in.readLine();
            }
            t--;
            if (t != 0) {
                out.append('\n');
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
