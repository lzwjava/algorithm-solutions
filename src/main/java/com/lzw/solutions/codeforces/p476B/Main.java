package com.lzw.solutions.codeforces.p476B;

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

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

    void solve() throws IOException {
        String a = in.readLine();
        String b = in.readLine();
        int p = 0;
        for (char c : a.toCharArray()) {
            if (c == '+') {
                p++;
            } else if (c == '-') {
                p--;
            }
        }
        int u = 0;
        int q = 0;
        for (char c : b.toCharArray()) {
            if (c == '+') {
                q++;
            } else if (c == '-') {
                q--;
            } else if (c == '?') {
                u++;
            }
        }
        int d = p - q;
        double ans = 0;
        if (d >= -u && d <= u) {
            boolean found = false;
            for (int i = 0; i <= u; i++) {
                int v = i - (u - i);
                // 2i-u = d
                // i = (u+d)/2
                if (v == d) {
                    // C(u, i)
                    int cnt = combination(u, i);
                    int m = 1 << u;
                    ans = cnt * 1.0 / m;
                    found = true;
                    break;
                }
            }
            if (!found) {
                ans = 0;
            }
        } else {
            ans = 0;
        }
        out.append(String.format("%.9f\n", ans));
    }

    int combination(int n, int k) {
        // n!/k!/(n-k)!
        long p = 1;
        for (int i = n; i >= n - k + 1; i--) {
            p *= i;
        }
        for (int i = 1; i <= k; i++) {
            p /= i;
        }
        return (int) p;
    }
}
