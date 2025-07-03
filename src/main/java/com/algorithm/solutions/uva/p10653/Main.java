package com.algorithm.solutions.uva.p10653;

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

    boolean[][] grid;
    int[][] dists;
    int r;
    int c;

    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};

    class State {
        int x, y, dist;

        State(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
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
            int xs = Integer.parseInt(st.nextToken());
            int ys = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(in.readLine());
            int xe = Integer.parseInt(st.nextToken());
            int ye = Integer.parseInt(st.nextToken());
            dists = new int[r][c];
            for (int i = 0; i < r; i++) {
                Arrays.fill(dists[i], -1);
            }

            boolean found = false;
            int ans = 0;
            ArrayBlockingQueue<State> queue = new ArrayBlockingQueue<State>(r * c);
            queue.add(new State(xs, ys, 0));
            while (!queue.isEmpty()) {
                State state = queue.poll();
                for (int i = 0; i < dx.length; i++) {
                    int nx = state.x + dx[i];
                    int ny = state.y + dy[i];
                    if (nx >= 0 && nx < r && ny >= 0 && ny < c && !grid[nx][ny] && dists[nx][ny] == -1) {
                        if (nx == xe && ny == ye) {
                            found = true;
                            ans = state.dist + 1;
                            break;
                        }
                        dists[nx][ny] = state.dist + 1;
                        queue.add(new State(nx, ny, state.dist + 1));
                    }
                }
                if (found) {
                    break;
                }
            }
//            dfs(rs, cs, re, ce, 0);
            out.append(String.format("%d\n", ans));
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
