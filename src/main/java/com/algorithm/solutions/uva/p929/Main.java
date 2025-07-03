package com.algorithm.solutions.uva.p929;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int[][] grid;
    int n, m;
    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0, -1};
    int[][] costs;

    class State implements Comparable<State> {
        int x, y, cost;

        State(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(State o) {
            return Integer.compare(cost, o.cost);
        }
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            n = Integer.parseInt(in.readLine());
            m = Integer.parseInt(in.readLine());
            grid = new int[n][m];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                for (int j = 0; j < m; j++) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            boolean[][] vis = new boolean[n][m];
            vis[0][0] = true;
            costs = new int[n][m];
            for (int i = 0; i < n; i++) {
                Arrays.fill(costs[i], -1);
            }
            costs[0][0] = grid[0][0];
            PriorityQueue<State> queue = new PriorityQueue<State>(n * m);
            queue.add(new State(0, 0, grid[0][0]));

            while (!queue.isEmpty()) {
                State st = queue.poll();
                for (int d = 0; d < dx.length; d++) {
                    int nx = st.x + dx[d];
                    int ny = st.y + dy[d];
                    if (nx >= 0 && nx < n && ny >= 0 && ny < m &&
                        (costs[nx][ny] == -1 || costs[nx][ny] > costs[st.x][st.y] + grid[nx][ny])) {
                        int c = st.cost + grid[nx][ny];
                        costs[nx][ny] = c;
                        queue.add(new State(nx, ny, c));
                    }
                }
            }
            out.append(String.format("%d\n", costs[n - 1][m - 1]));
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
