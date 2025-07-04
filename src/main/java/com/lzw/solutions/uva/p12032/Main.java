package com.lzw.solutions.uva.p12032;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        for (int u = 0; u < t; u++) {
            int n = Integer.parseInt(in.readLine());
            int[] nums = new int[n];
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }
            int ans = 0;
            for (int i = n - 1; i >= 0; i--) {
                int v;
                if (i == 0) {
                    v = 0;
                } else {
                    v = nums[i - 1];
                }
                int d = nums[i] - v;
                if (d > ans) {
                    ans = d;
                } else if (d == ans) {
                    ans = d + 1;
                }
            }
            out.append(String.format("Case %d: %d\n", u + 1, ans));
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
