package com.algorithm.solutions.uva.p1604;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class MainPlus {

    BufferedReader in;
    PrintWriter out;

    MainPlus() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    enum Dir {
        TOP,
        BOTTOM,
        LEFT,
        RIGHT
    }

    class Cube {
        char top;
        char front;

        Cube(char top, char front) {
            this.top = top;
            this.front = front;
        }

        char left() {
            String s = "BWR";
            char[] chs = new char[]{top, front};
            for (char c : s.toCharArray()) {
                if (!Arrays.asList(chs).contains(c)) {
                    return c;
                }
            }
            return ' ';
        }

        Cube turn(Dir d) {
            char ntop, nfront;
            if (d == Dir.RIGHT) {
                ntop = left();
                nfront = front;
            } else if (d == Dir.LEFT) {
                ntop = left();
                nfront = front;
            } else if (d == Dir.TOP) {
                ntop = front;
                nfront = top;
            } else {
                ntop = front;
                nfront = top;
            }
            return new Cube(ntop, nfront);
        }

        @Override
        public String toString() {
            return String.format("%c%c", top, front);
        }
    }

    class State {
        Cube[][] cubes;
        int x, y;
        int dist;

        State(Cube[][] cubes, int x, int y) {
            this.cubes = cubes;
            this.x = x;
            this.y = y;
            this.dist = 0;
        }

        char[][] tops() {
            char[][] ts = new char[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    Cube c = cubes[i][j];
                    char top;
                    if (c != null) {
                        top = c.top;
                    } else {
                        top = 'E';
                    }
                    ts[i][j] = top;
                }
            }
            return ts;
        }

        @Override
        protected State clone() {
            Cube[][] ncubes = new Cube[3][3];
            for (int i = 0; i < 3; i++) {
                ncubes[i] = Arrays.copyOf(cubes[i], 3);
            }
            State ns = new State(ncubes, x, y);
            ns.dist = dist;
            return ns;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    String s;
                    Cube c = cubes[i][j];
                    if (c == null) {
                        s = "EE";
                    } else {
                        s = c.toString();
                    }
                    sb.append(String.format("%s,", s));
                }
            }
            return sb.toString();
        }
    }

    // top, bottom, left, right
    int[] dx = new int[]{-1, 1, 0, 0};
    int[] dy = new int[]{0, 0, -1, 1};

    void solve() throws IOException {
        while (true) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if (x == 0 && y == 0) {
                break;
            }
            x--;
            y--;
            int t = x;
            x = y;
            y = t;
            char[][] grid = new char[3][3];
            for (int i = 0; i < 3; i++) {
                st = new StringTokenizer(in.readLine());
                for (int j = 0; j < 3; j++) {
                    grid[i][j] = st.nextToken().charAt(0);
                }
            }
            Cube[][] cubes = new Cube[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    Cube c = new Cube('W', 'R');
                    if (i == x && j == y) {
                        c = null;
                    }
                    cubes[i][j] = c;
                }
            }
            State init = new State(cubes, x, y);
            Queue<State> queue = new ArrayBlockingQueue<>(1000000);
            Set<String> set = new HashSet<>();
            queue.add(init);
            set.add(init.toString());
            boolean found = false;
            while (!queue.isEmpty()) {
                State s = queue.poll();
                if (s.dist >= 30) {
                    break;
                }
                for (int d = 0; d < dx.length; d++) {
                    int nx = s.x + dx[d];
                    int ny = s.y + dy[d];
                    if (nx < 0 || nx >= 3 || ny < 0 || ny >= 3) {
                        continue;
                    }
                    Dir dir = Dir.values()[d];
                    State ns = changeState(s, dir, nx, ny, s.x, s.y);
                    ns.dist = s.dist + 1;
                    char[][] tops = ns.tops();
                    if (Arrays.deepEquals(tops, grid)) {
                        out.append(String.format("%d\n", ns.dist));
                        out.flush();
                        found = true;
                        break;
                    }
                    String nsStr = ns.toString();
                    if (!set.contains(nsStr)) {
                        set.add(nsStr);
                        queue.add(ns);
                    }
                }
                if (found) {
                    break;
                }
            }
            if (!found) {
                out.append("-1\n");
            }
        }
    }

    void print(State s) {
        char[][] tops = s.tops();
        for (int i = 0; i < tops.length; i++) {
            String str = new String(tops[i]);
            out.append(String.format("%s\n", str));
        }
        out.append('\n');
        out.flush();
    }

    State changeState(State s, Dir d, int nx, int ny, int x, int y) {
        State ns = s.clone();
        Cube c;
        if (d == Dir.LEFT) {
            c = ns.cubes[nx][ny].turn(Dir.RIGHT);
        } else if (d == Dir.RIGHT) {
            c = ns.cubes[nx][ny].turn(Dir.LEFT);
        } else if (d == Dir.TOP) {
            c = ns.cubes[nx][ny].turn(Dir.BOTTOM);
        } else {
            c = ns.cubes[nx][ny].turn(Dir.TOP);
        }
        ns.x = nx;
        ns.y = ny;
        ns.cubes[x][y] = c;
        ns.cubes[nx][ny] = null;
        return ns;
    }

    void close() throws IOException {
        in.close();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        MainPlus m = new MainPlus();
        m.solve();
        m.close();
    }
}