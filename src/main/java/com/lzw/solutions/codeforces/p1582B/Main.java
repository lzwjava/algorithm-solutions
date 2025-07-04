package com.lzw.solutions.codeforces.p1582B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int dp(int cur, int n, long s) {
        if (cur == n) {
            if (s == 0) {
                return 1;
            }
            return 0;
        }
        if (s < 0) {
            return 0;
        }
        if (s < maxn) {
            int cache = map[cur][(int) s];
            if (cache != -1) {
                return cache;
            }
        }
        long remain;
        if (cur == 0) {
            remain = total;
        } else {
            remain = total - sums[cur - 1];
        }
        if (remain < s) {
            return 0;
        }
        int d1 = dp(cur + 1, n, s - nums[cur]);
        int d2 = dp(cur + 1, n, s);
        int ans = d1 + d2;
        ;
        if (s < maxn) {
            map[cur][(int) s] = ans;
        }
        return ans;
    }

    long[] nums;
    long total;
    long[] sums;
    int[][] map;
    int maxn;

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            int n = Integer.parseInt(in.readLine());
            StringTokenizer st = new StringTokenizer(in.readLine());
            nums = new long[n];
            total = 0;
            sums = new long[n];
            for (int i = 0; i < n; i++) {
                nums[i] = Long.parseLong(st.nextToken());
                total += nums[i];
                sums[i] = total;
            }
            maxn = Integer.MAX_VALUE / 1000;
            map = new int[n + 1][maxn];
            for (int i = 0; i < n + 1; i++) {
                Arrays.fill(map[i], -1);
            }
            int count = dp(0, n, total - 1);
            out.append(String.format("%d\n", count));
            t--;
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
