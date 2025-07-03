package com.algorithm.solutions.uva.p10102;

import java.io.*;
import java.util.concurrent.ArrayBlockingQueue;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int[][] grid;
    int m;

    class State {
        int x, y, dist;

        State(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    int[] dx = new int[]{-1, 1, 0, 0};
    int[] dy = new int[]{0, 0, -1, 1};

    int calDist(int x, int y) {
        boolean[][] vis = new boolean[m][m];
        ArrayBlockingQueue<State> queue = new ArrayBlockingQueue<State>(m * m);
        queue.add(new State(x, y, 0));
        while (!queue.isEmpty()) {
            State st = queue.poll();
            for (int d = 0; d < dx.length; d++) {
                int nx = st.x + dx[d];
                int ny = st.y + dy[d];
                if (nx >= 0 && nx < m && ny >= 0 && ny < m && !vis[nx][ny]) {
                    if (grid[nx][ny] == 3) {
                        return st.dist + 1;
                    }
                    vis[nx][ny] = true;
                    queue.add(new State(nx, ny, st.dist + 1));
                }
            }
        }
        return 0;
    }

    void solve() throws IOException {
        while (true) {
            String line = in.readLine();
            if (line == null) {
                break;
            }
            m = Integer.parseInt(line);
            grid = new int[m][m];
            for (int i = 0; i < m; i++) {
                String s = in.readLine();
                for (int j = 0; j < m; j++) {
                    grid[i][j] = s.charAt(j) - '0';
                }
            }
            int max = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] == 1) {
                        int dist = calDist(i, j);
                        if (dist > max) {
                            max = dist;
                        }
                    }
                }
            }
            out.append(String.format("%d\n", max));
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
