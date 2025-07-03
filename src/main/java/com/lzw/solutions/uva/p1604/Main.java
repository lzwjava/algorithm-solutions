package com.lzw.solutions.uva.p1604;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    enum Dir {
        TOP,
        BOTTOM,
        LEFT,
        RIGHT
    }

    // BWRE
    // 1230
    class Cube {
        int top;
        int front;

        Cube(int top, int front) {
            this.top = top;
            this.front = front;
        }

        int left() {
            return 6 - top - front;
        }

        Cube turn(Dir d) {
            int ntop, nfront;
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
    }

    // top, bottom, left, right
    int[] dx = new int[] {-1, 1, 0, 0};
    int[] dy = new int[] {0, 0, -1, 1};

    int[][] grid;
    int ans;

    int cubeInt(char c) {
        if (c == 'B') {
            return 1;
        } else if (c == 'W') {
            return 2;
        } else if (c == 'R') {
            return 3;
        } else if (c == 'E') {
            return 0;
        }
        return -1;
    }

    int differ(Cube[][] cubes) {
        int cnt = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Cube c = cubes[i][j];
                if (c.top != grid[i][j]) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    void dfs(Cube[][] cubes, int dist, int x, int y, int px, int py) {
        int diff = differ(cubes);
        if (diff == 0) {
            if (dist < ans) {
                ans = dist;
            }
            return;
        }
        if (dist + diff > ans) {
            return;
        }
        for (int d = 0; d < dx.length; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (nx < 0 || nx >= 3 || ny < 0 || ny >= 3 || (nx == px && ny == py)) {
                continue;
            }
            Dir dir = Dir.values()[d];
            Cube ocube = cubes[nx][ny];
            Cube ncube = changeCube(cubes, dir, nx, ny);
            cubes[nx][ny] = new Cube(0, 0);
            cubes[x][y] = ncube;
            dfs(cubes, dist + 1, nx, ny, x, y);
            cubes[nx][ny] = ocube;
            cubes[x][y] = new Cube(0, 0);
        }
    }

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
            grid = new int[3][3];
            for (int i = 0; i < 3; i++) {
                st = new StringTokenizer(in.readLine());
                for (int j = 0; j < 3; j++) {
                    char c = st.nextToken().charAt(0);
                    grid[i][j] = cubeInt(c);
                }
            }
            Cube[][] cubes = new Cube[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    Cube c = new Cube(2, 3);
                    if (i == x && j == y) {
                        c = new Cube(0, 0);
                    }
                    cubes[i][j] = c;
                }
            }
            ans = 31;
            dfs(cubes, 0, x, y, -1, -1);
            if (ans == 31) {
                out.append("-1\n");
            } else {
                out.append(String.format("%d\n", ans));
            }
        }
    }

    Cube changeCube(Cube[][] cubes, Dir d, int nx, int ny) {
        Cube c;
        if (d == Dir.LEFT) {
            c = cubes[nx][ny].turn(Dir.RIGHT);
        } else if (d == Dir.RIGHT) {
            c = cubes[nx][ny].turn(Dir.LEFT);
        } else if (d == Dir.TOP) {
            c = cubes[nx][ny].turn(Dir.BOTTOM);
        } else {
            c = cubes[nx][ny].turn(Dir.TOP);
        }
        return c;
    }

    void close() throws IOException {
        in.close();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }
}
