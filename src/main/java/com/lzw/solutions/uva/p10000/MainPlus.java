package com.lzw.solutions.uva.p10000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.concurrent.ArrayBlockingQueue;

public class MainPlus {

    BufferedReader in;
    PrintWriter out;

    MainPlus() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    class State {
        int p;
        int dist;

        State() {}

        State(int p, int dist) {
            this.p = p;
            this.dist = dist;
        }
    }

    void solve() throws IOException {
        int caseNum = 1;
        while (true) {
            int n = Integer.parseInt(in.readLine());
            if (n == 0) {
                break;
            }
            int s = Integer.parseInt(in.readLine()) - 1;

            boolean[][] grid = new boolean[n][n];
            while (true) {
                String line = in.readLine();
                StringTokenizer st = new StringTokenizer(line);
                int p = Integer.parseInt(st.nextToken());
                int q = Integer.parseInt(st.nextToken());
                if (p == 0 && q == 0) {
                    break;
                }
                grid[p - 1][q - 1] = true;
            }

            ArrayBlockingQueue<State> queue = new ArrayBlockingQueue<>(1000000000);
            // boolean[] vis = new boolean[n];
            State startState = new State(s, 0);
            queue.add(startState);
            // vis[s] = true;
            State maxState = startState;
            while (!queue.isEmpty()) {
                State st = queue.poll();
                if (st.dist > maxState.dist || (st.dist == maxState.dist && st.p < maxState.p)) {
                    maxState = st;
                }
                for (int i = 0; i < n; i++) {
                    if (grid[st.p][i]) {
                        State newState = new State(i, st.dist + 1);
                        queue.add(newState);
                    }
                }
            }
            out.append(String.format(
                    "Case %d: The longest path from %d has length %d, finishing at %d.\n",
                    caseNum, s + 1, maxState.dist, maxState.p + 1));
            out.append('\n');
            caseNum++;
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
        boolean isLocal = System.getProperty("os.name").equals("Mac OS X");
        if (isLocal) {
            inStream = new FileInputStream("2.in");
            outStream = new PrintStream("1.out");
            System.setIn(inStream);
            System.setOut(outStream);
        }

        MainPlus main = new MainPlus();
        main.solve();
        main.close();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
