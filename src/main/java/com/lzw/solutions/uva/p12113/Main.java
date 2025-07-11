package com.lzw.solutions.uva.p12113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    Set<String> set;

    void draw(char[][] g, int v) {
        char[][] gt = new char[][] {
            {' ', '_', ' ', '_', ' '},
            {'|', ' ', ' ', ' ', '|'},
            {'|', '_', ' ', '_', '|'}
        };
        int ox = v / 3;
        int oy = (v % 3) * 2;
        for (int x = 0; x < gt.length; x++) {
            for (int y = 0; y < gt[0].length; y++) {
                if (x == 0 && (y == 0 || y == 2 || y == 4)) {
                    continue;
                }
                int nx = ox + x;
                int ny = oy + y;
                g[nx][ny] = gt[x][y];
            }
        }
    }

    void permutation(int[] nums, boolean[] vis, int cur, int n) {
        if (cur == n) {
            char[][] g = new char[5][9];
            for (int i = 0; i < 5; i++) {
                Arrays.fill(g[i], ' ');
            }
            for (int i = 0; i < n; i++) {
                draw(g, nums[i]);
            }
            String str = gridToStr(g);
            set.add(str);
            return;
        }
        for (int i = 0; i < 9; i++) {
            if (!vis[i]) {
                nums[cur] = i;
                permutation(nums, vis, cur + 1, n);
            }
        }
    }

    String gridToStr(char[][] grid) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < grid.length; i++) {
            sb.append(new String(grid[i]));
        }
        return sb.toString();
    }

    void solve() throws IOException {
        set = new HashSet<>();
        for (int p = 1; p <= 6; p++) {
            int[] nums = new int[p];
            boolean[] vis = new boolean[9];
            permutation(nums, vis, 0, p);
        }

        int caseNum = 1;
        while (true) {
            String line = in.readLine();
            if (line.charAt(0) == '0') {
                break;
            }
            int i = 0;
            char[][] grid = new char[5][9];
            do {
                for (int j = 0; j < 9; j++) {
                    grid[i][j] = line.charAt(j);
                }
                i++;
                if (i != 5) {
                    line = in.readLine();
                }
            } while (i < 5);
            String s = gridToStr(grid);
            boolean ok = set.contains(s);
            out.append(String.format("Case %d: ", caseNum));
            if (ok) {
                out.append("Yes\n");
            } else {
                out.append("No\n");
            }
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
