package com.lzw.solutions.codeforces.p1234A;

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

    void solve() throws IOException {
        int q = Integer.parseInt(in.readLine());
        while (q > 0) {
            int n = Integer.parseInt(in.readLine());
            StringTokenizer st = new StringTokenizer(in.readLine());
            int s = 0;
            for (int i = 0; i < n; i++) {
                int v = Integer.parseInt(st.nextToken());
                s += v;
            }
            int ans;
            if (s % n == 0) {
                ans = s / n;
            } else {
                ans = (int) Math.ceil(s * 1.0 / n);
            }
            out.append(String.format("%d\n", ans));
            q--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }
}
