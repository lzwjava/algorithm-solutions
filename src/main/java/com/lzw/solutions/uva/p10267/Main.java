package com.lzw.solutions.uva.p10267;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.concurrent.ArrayBlockingQueue;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void solve() throws IOException {
        int m = 0, n = 0;
        char[][] grid = new char[m][n];
        while (true) {
            String line = in.readLine();
            if (line == null) {
                break;
            }
            StringTokenizer st = new StringTokenizer(line);
            String opStr = st.nextToken();
            char op = opStr.charAt(0);
            if (op == 'I') {
                m = Integer.parseInt(st.nextToken());
                n = Integer.parseInt(st.nextToken());
                grid = new char[n][m];
                for (int i = 0; i < n; i++) {
                    Arrays.fill(grid[i], 'O');
                }
            } else if (op == 'C') {
                for (int i = 0; i < n; i++) {
                    Arrays.fill(grid[i], 'O');
                }
            } else if (op == 'L') {
                int x = Integer.parseInt(st.nextToken()) - 1;
                int y = Integer.parseInt(st.nextToken()) - 1;
                char c = st.nextToken().charAt(0);
                grid[y][x] = c;
            } else if (op == 'V') {
                int x = Integer.parseInt(st.nextToken()) - 1;
                int y1 = Integer.parseInt(st.nextToken()) - 1;
                int y2 = Integer.parseInt(st.nextToken()) - 1;
                char c = st.nextToken().charAt(0);
                if (y2 < y1) {
                    int t = y1;
                    y1 = y2;
                    y2 = t;
                }
                for (int y = y1; y <= y2; y++) {
                    grid[y][x] = c;
                }
            } else if (op == 'H') {
                int x1 = Integer.parseInt(st.nextToken()) - 1;
                int x2 = Integer.parseInt(st.nextToken()) - 1;
                int y = Integer.parseInt(st.nextToken()) - 1;
                char c = st.nextToken().charAt(0);
                if (x2 < x1) {
                    int t = x1;
                    x1 = x2;
                    x2 = t;
                }
                for (int x = x1; x <= x2; x++) {
                    grid[y][x] = c;
                }
            } else if (op == 'K') {
                int x1 = Integer.parseInt(st.nextToken()) - 1;
                int y1 = Integer.parseInt(st.nextToken()) - 1;
                int x2 = Integer.parseInt(st.nextToken()) - 1;
                int y2 = Integer.parseInt(st.nextToken()) - 1;
                if (x2 < x1) {
                    int t = x1;
                    x1 = x2;
                    x2 = t;
                }
                if (y2 < y1) {
                    int t = y1;
                    y1 = y2;
                    y2 = t;
                }
                char c = st.nextToken().charAt(0);
                for (int x = x1; x <= x2; x++) {
                    for (int y = y1; y <= y2; y++) {
                        grid[y][x] = c;
                    }
                }
            } else if (op == 'F') {
                int x = Integer.parseInt(st.nextToken()) - 1;
                int y = Integer.parseInt(st.nextToken()) - 1;
                char c = st.nextToken().charAt(0);
                char oc = grid[y][x];
                boolean[][] vis = new boolean[n][m];
                dfs(grid, vis, m, n, y, x, oc, c);
            } else if (op == 'S') {
                String name = st.nextToken();
                out.append(String.format("%s\n", name));
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        out.append(grid[i][j]);
                    }
                    out.append('\n');
                }
            } else if (op == 'X') {
                m = 0;
                n = 0;
                grid = new char[n][m];
                break;
            }
        }
    }

    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    class Point {
        int y, x;

        Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    void dfs(char[][] grid, boolean[][] vis, int m, int n, int y, int x, char oc, char tc) {
        grid[y][x] = tc;
        vis[y][x] = true;
        ArrayBlockingQueue<Point> queue = new ArrayBlockingQueue<Point>(m * n);
        queue.add(new Point(y, x));
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            for (int d = 0; d < 4; d++) {
                int ny = p.y + dy[d];
                int nx = p.x + dx[d];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[ny][nx] == oc && !vis[ny][nx]) {
                    vis[ny][nx] = true;
                    grid[ny][nx] = tc;
                    queue.add(new Point(ny, nx));
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
