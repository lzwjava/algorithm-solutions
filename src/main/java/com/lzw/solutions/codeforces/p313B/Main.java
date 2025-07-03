package com.lzw.solutions.codeforces.p313B;

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

    int[] sums;

    void solve() throws IOException {
        String s = in.readLine();
        int n = s.length();
        int[] v = new int[n];
        sums = new int[n];
        int d = 0;
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                v[i] = 1;
            }
            d += v[i];
            sums[i] = d;
        }
        int m = Integer.parseInt(in.readLine());
        while (m > 0) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int l = Integer.parseInt(st.nextToken()) - 1;
            int r = Integer.parseInt(st.nextToken()) - 1;
            int cnt = sum(l, r);
            out.append(String.format("%d\n", cnt));
            m--;
        }
    }

    int sum(int l, int r) {
        if (l == 0) {
            return sums[r - 1];
        } else {
            return sums[r - 1] - sums[l - 1];
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }
}
