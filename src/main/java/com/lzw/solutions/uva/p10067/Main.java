package com.lzw.solutions.uva.p10067;

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

    class State {
        int[] wheels;
        int dist;

        State(int[] wheels, int dist) {
            this.wheels = wheels;
            this.dist = dist;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return Arrays.equals(wheels, state.wheels);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(wheels);
        }
    }

    private int getKey(int[] wheels) {
        return wheels[0] * 1000 + wheels[1] * 100 + wheels[2] * 10 + wheels[3];
    }

    private boolean isForbidden(int[][] forbids, int[] wheels) {
        for (int[] forbid : forbids) {
            if (Arrays.equals(wheels, forbid)) {
                return true;
            }
        }
        return false;
    }

    int readInt(String str) {
        return Integer.parseInt(str.trim());
    }

    void solve() throws IOException {
        int t = readInt(in.readLine());
        while (t > 0) {
            int[] initials = new int[4];
            int[] targets = new int[4];
            String str;
            do {
                str = in.readLine();
            } while (str.equals(""));
            StringTokenizer st = new StringTokenizer(str);
            for (int i = 0; i < 4; i++) {
                initials[i] = readInt(st.nextToken());
            }
            st = new StringTokenizer(in.readLine());
            for (int i = 0; i < 4; i++) {
                targets[i] = readInt(st.nextToken());
            }
            int fn = readInt(in.readLine());
            int[][] forbids = new int[fn][4];
            for (int i = 0; i < fn; i++) {
                st = new StringTokenizer(in.readLine());
                for (int j = 0; j < 4; j++) {
                    forbids[i][j] = readInt(st.nextToken());
                }
            }
            State targetState = new State(targets, 0);

            boolean[] vis = new boolean[10005];

            ArrayBlockingQueue<State> queue = new ArrayBlockingQueue<State>(10010);
            queue.add(new State(initials, 0));
            vis[getKey(initials)] = true;
            boolean found = false;
            int ans = -1;
            if (isForbidden(forbids, initials)) {
                ans = -1;
            } else {
                while (!queue.isEmpty()) {
                    State state = queue.poll();
                    if (state.equals(targetState)) {
                        found = true;
                        ans = 0;
                        break;
                    }
                    for (int i = 0; i < 4; i++) {
                        for (int j = -1; j <= 1; j++) {
                            if (j == 0) {
                                continue;
                            }
                            int[] wheels = state.wheels.clone();
                            wheels[i] = (wheels[i] + j + 10) % 10;
                            int key = getKey(wheels);
                            if (!vis[key] && !isForbidden(forbids, wheels)) {
                                vis[key] = true;
                                State newState = new State(wheels, state.dist + 1);
                                if (newState.equals(targetState)) {
                                    found = true;
                                    ans = newState.dist;
                                    break;
                                }
                                queue.add(newState);
                            }
                        }
                        if (found) {
                            break;
                        }
                    }
                    if (found) {
                        break;
                    }
                }
            }
            out.append(String.format("%d\n", ans));

            t--;
            if (t != 0) {
                in.readLine();
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
        boolean isLocal = System.getProperty("os.name").equals("Mac OS X");
        if (isLocal) {
            inStream = new FileInputStream("2.in");
            outStream = new PrintStream("2.out");
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
