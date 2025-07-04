package com.lzw.solutions.uva.p118;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    int gx;
    int gy;
    boolean vis[][];

    class Pos {
        int x;
        int y;
        char dir;

        char[] dirs = new char[] {'E', 'S', 'W', 'N'};
        int[] dx = new int[] {1, 0, -1, 0};
        int[] dy = new int[] {0, -1, 0, 1};

        Pos() {}

        Pos(int x, int y, char dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }

        int indexOfDir() {
            int i;
            for (i = 0; i < dirs.length; i++) {
                if (dirs[i] == dir) {
                    break;
                }
            }
            return i;
        }

        void turnRight() {
            int i = indexOfDir();
            int ni = (i + 1) % 4;
            this.dir = dirs[ni];
        }

        void turnLeft() {
            int i = indexOfDir();
            int ni = (i - 1 + 4) % 4;
            this.dir = dirs[ni];
        }

        boolean forward() {
            int nx, ny;
            int i = indexOfDir();
            nx = x + dx[i];
            ny = y + dy[i];
            if (nx >= 0 && nx <= gx && ny >= 0 && ny <= gy) {
                x = nx;
                y = ny;
                return true;
            } else {
                if (!vis[x][y]) {
                    vis[x][y] = true;
                    return false;
                } else {
                    return true;
                }
            }
        }

        boolean exec(char instruction) {
            if (instruction == 'R') {
                turnRight();
            } else if (instruction == 'L') {
                turnLeft();
            } else {
                boolean res = forward();
                if (!res) {
                    return false;
                }
            }
            return true;
        }
    }

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void solve() throws IOException {
        String s = in.readLine();
        StringTokenizer st = new StringTokenizer(s);
        gx = Integer.parseInt(st.nextToken());
        gy = Integer.parseInt(st.nextToken());
        vis = new boolean[gx + 1][gy + 1];
        while (true) {
            s = in.readLine();
            if (s == null) {
                break;
            }
            st = new StringTokenizer(s);
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            String dir = st.nextToken();
            char d = dir.charAt(0);
            Pos pos = new Pos(x1, y1, d);
            String instructions = in.readLine();
            boolean ok = true;
            for (int i = 0; i < instructions.length(); i++) {
                char ch = instructions.charAt(i);
                boolean res = pos.exec(ch);
                if (!res) {
                    ok = false;
                    break;
                }
                // out.append(String.format("%d %d %c\n", pos.x, pos.y, pos.dir));
            }
            if (ok) {
                out.append(String.format("%d %d %c\n", pos.x, pos.y, pos.dir));
            } else {
                out.append(String.format("%d %d %c LOST\n", pos.x, pos.y, pos.dir));
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

        Main main = new Main();
        main.solve();
        main.close();
    }
}
