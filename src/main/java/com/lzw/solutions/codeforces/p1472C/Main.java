package com.lzw.solutions.codeforces.p1472C;

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

    long[] map;

    long dp(int[] a, int i) {
        int n = a.length;
        if (i >= n) {
            return 0;
        }
        long cache = map[i];
        if (cache != 0) {
            return cache;
        }
        long ans = a[i] + dp(a, i + a[i]);
        map[i] = ans;
        return ans;
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }
            map = new long[n];
            long ms = 0;
            for (int i = 0; i < n; i++) {
                long s = dp(a, i);
                if (s > ms) {
                    ms = s;
                }
            }
            out.append(String.format("%d\n", ms));
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }
}
