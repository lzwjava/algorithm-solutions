package com.algorithm.solutions.uva.p211;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    Scanner in;
    PrintWriter out;
    int caseNum;

    Main() {
        in = new Scanner(System.in);
        out = new PrintWriter(System.out);
    }

    int[][] grid;
    int[][] nums;
    Bone[] bones;
    int cnt;
    boolean[] vis;

    class Bone {
        int id;
        int a, b;

        Bone(int id, int a, int b) {
            this.id = id;
            this.a = a;
            this.b = b;
        }
    }

    // top to bottom, left to right
    int[] dx = new int[]{1, 0};
    int[] dy = new int[]{0, 1};

    void print(int[][] g) {
        out.append('\n');
        int n = g.length;
        int m = g[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                out.append(String.format("%4d", g[i][j]));
            }
            out.append('\n');
        }
        out.append('\n');
    }

    void dfs(int cur) {
        if (cur == 56) {
            cnt++;
            print(nums);
            out.flush();
            return;
        }
        int x = cur / 8;
        int y = cur % 8;
        if (nums[x][y] != 0) {
            dfs(cur + 1);
        } else {
            for (int i = 0; i < bones.length; i++) {
                if (vis[i]) {
                    continue;
                }
                Bone bn = bones[i];
                for (int d = 0; d < 2; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];
                    if (nx >= 7 || ny >= 8) {
                        continue;
                    }
                    if (nums[nx][ny] != 0) {
                        continue;
                    }
                    int v = 1;
                    if (bn.a != bn.b) {
                        v = 2;
                    }
                    for (int k = 0; k < v; k++) {
                        int p, q;
                        if (k == 0) {
                            p = bn.a;
                            q = bn.b;
                        } else {
                            p = bn.b;
                            q = bn.a;
                        }
                        if (p == grid[x][y] && q == grid[nx][ny]) {
                            nums[x][y] = nums[nx][ny] = bn.id;
                            vis[i] = true;
                            dfs(cur + 1);
                            vis[i] = false;
                            nums[x][y] = nums[nx][ny] = 0;
                        }
                    }
                }
            }
        }
    }

    void solve() throws IOException {
        int p = 0;
        bones = new Bone[28];
        for (int i = 0; i <= 6; i++) {
            for (int j = i; j <= 6; j++) {
                Bone bone = new Bone(p + 1, i, j);
                bones[p++] = bone;
            }
        }
        caseNum = 1;
        while (in.hasNextInt()) {
            grid = new int[7][8];
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 8; j++) {
                    grid[i][j] = in.nextInt();
                }
            }
            nums = new int[7][8];
            vis = new boolean[28];
            cnt = 0;
            if (caseNum != 1) {
                out.append("\n\n\n\n\n");
            }
            out.append(String.format("Layout #%d:\n\n", caseNum));
            print(grid);
            out.append(String.format("Maps resulting from layout #%d are:\n\n", caseNum));

            dfs(0);

            out.append(String.format("\nThere are %d solution(s) for layout #%d.\n", cnt, caseNum));
            caseNum++;
        }
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