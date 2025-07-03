package com.lzw.solutions.codeforces.p1526A;

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

    void permutation(int[] nums, boolean[] vis, int cur) {
        if (found) {
            return;
        }
        if (cur == m) {
            if (checkEqual(nums, 0) || checkEqual(nums, m - 1)) {
                return;
            }
            for (int i = 0; i < m; i++) {
                if (i != 0) {
                    out.append(' ');
                }
                out.append(String.format("%d", nums[i]));
            }
            out.append('\n');
            found = true;
            return;
        }
        for (int i = 0; i < m; i++) {
            if (!vis[i]) {
                vis[i] = true;
                nums[cur] = a[i];
                if (check(nums, cur)) {
                    permutation(nums, vis, cur + 1);
                }
                vis[i] = false;
            }
        }
    }

    boolean checkEqual(int[] nums, int i) {
        int left = i - 1;
        if (left == -1) {
            left = m - 1;
        }
        int right = i + 1;
        if (right == m) {
            right = 0;
        }
        return nums[left] + nums[right] == 2 * nums[i];
    }

    boolean check(int[] nums, int cur) {
        for (int i = 1; i < cur; i++) {
            if (checkEqual(nums, i)) {
                return false;
            }
        }
        return true;
    }

    int n;
    int m;
    int[] a;
    boolean found;

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            n = Integer.parseInt(in.readLine());
            m = 2 * n;
            a = new int[m];
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int i = 0; i < m; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }
            int[] nums = new int[m];
            boolean[] vis = new boolean[m];
            found = false;
            permutation(nums, vis, 0);
        }
    }
}