package com.lzw.solutions.uva.p291;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int[][] map = {
        {0, 1, 1, 0, 1},
        {1, 0, 1, 0, 1},
        {1, 1, 0, 1, 1},
        {0, 0, 1, 0, 1},
        {1, 1, 1, 1, 0}
    };

    boolean[][] vis;

    void dfs(int nums[], int idx, int cur) {
        if (cur == 9) {
            for (int i = 0; i < 9; i++) {
                out.append(String.valueOf(nums[i] + 1));
            }
            out.append('\n');
            return;
        }
        for (int i = 0; i < 5; i++) {
            if (!vis[idx][i] && map[idx][i] == 1) {
                vis[idx][i] = vis[i][idx] = true;
                nums[cur] = i;
                dfs(nums, i, cur + 1);
                vis[idx][i] = vis[i][idx] = false;
            }
        }
    }

    void solve() throws IOException {
        int[] nums = new int[9];
        vis = new boolean[5][5];
        nums[0] = 0;
        dfs(nums, 0, 1);
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
