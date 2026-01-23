package com.lzw.solutions.codeforces.p2189B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {
    private static final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static final PrintWriter out = new PrintWriter(System.out, true);

    private static void solve() throws IOException {
        String line = in.readLine().trim();
        int t = Integer.parseInt(line);
        for (int test = 0; test < t; test++) {
            line = in.readLine().trim();
            String[] parts = line.split("\\s+");
            int n = Integer.parseInt(parts[0]);
            long x = Long.parseLong(parts[1]);
            long free = 0;
            long maxp = 0;
            for (int i = 0; i < n; i++) {
                line = in.readLine().trim();
                parts = line.split("\\s+");
                long a = Long.parseLong(parts[0]);
                long b = Long.parseLong(parts[1]);
                long c = Long.parseLong(parts[2]);
                free += (b - 1) * a;
                long p = b * a - c;
                maxp = Math.max(maxp, p);
            }
            long ans;
            if (free >= x) {
                ans = 0;
            } else if (maxp <= 0) {
                ans = -1;
            } else {
                long need = x - free;
                ans = (need + maxp - 1) / maxp;
            }
            out.println(ans);
        }
    }

    public static void main(String[] args) throws IOException {
        solve();
        out.close();
    }
}