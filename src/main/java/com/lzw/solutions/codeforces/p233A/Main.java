package com.lzw.solutions.codeforces.p233A;

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

    void close() throws IOException {
        in.close();
        out.flush();
        out.close();
    }

    boolean found;

    boolean check(int[] nums, int n) {
        for (int i = 0; i < n; i++) {
            if (nums[i] < n) {
                if (nums[nums[i]] == i && nums[i] != i) {
                    continue;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    void permutation(int[] nums, boolean[] vis, int cur, int n) {
        if (!check(nums, cur)) {
            return;
        }
        if (found) {
            return;
        }
        if (cur == n) {
            found = true;
            for (int i = 0; i < n; i++) {
                if (i != 0) {
                    out.append(' ');
                }
                out.append(String.valueOf(nums[i] + 1));
            }
            out.append('\n');
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                vis[i] = true;
                nums[cur] = i;
                permutation(nums, vis, cur + 1, n);
                vis[i] = false;
            }
        }
    }

    void solve() throws IOException {
        int n = Integer.parseInt(in.readLine());
        if (n % 2 != 0) {
            out.append("-1\n");
        } else {
            int[] nums = new int[n];
            boolean[] vis = new boolean[n];
            found = false;
            permutation(nums, vis, 0, n);
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}