package com.lzw.solutions.codeforces.p1324C;

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

    boolean possible(String s, int d) {
        int n = s.length();
        int p = -1;
        boolean[] vis = new boolean[n];
        while (p < n) {
            if (p == -1 || s.charAt(p) == 'R') {
                if (p + d >= n) {
                    return true;
                }
                int i;
                for (i = d; i >= 1; i--) {
                    if (s.charAt(p + i) == 'R') {
                        break;
                    }
                }
                if (i == 0) {
                    i = 1;
                }
                if (vis[p + i]) {
                    return false;
                }
                p += i;
                vis[p] = true;
            } else {
                int i;
                for (i = 1; i <= d; i++) {
                    if (p - i >= 0 && s.charAt(p - i) == 'R') {
                        break;
                    }
                }
                int np;
                if (i == d + 1) {
                    int ni = Integer.max(0, p - d);
                    np = ni;
                } else {
                    np = p - i;
                }
                if (vis[np]) {
                    return false;
                }
                vis[np] = true;
                p = np;
            }
        }
        return false;
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            String s = in.readLine();
            int n = s.length();
            int last = -1;
            int max = 0;
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == 'R') {
                    int d = i - last;
                    if (d > max) {
                        max = d;
                    }
                    last = i;
                }
            }
            int d = n - last;
            if (d > max) {
                max = d;
            }
            out.append(String.format("%d\n", max));
        }
    }

    void solve1() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            String s = in.readLine();
            int n = s.length();
            int r = s.indexOf('R');
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == 'R') {
                    out.append(String.format("%d\n", i));
                }
            }
            int ans = 0;
            if (r == -1) {
                ans = n + 1;
            } else {
                for (int d = r + 1; d <= n + 1; d++) {
                    if (possible(s, d)) {
                        ans = d;
                        break;
                    }
                }
            }
            out.append(String.format("%d\n", ans));
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}