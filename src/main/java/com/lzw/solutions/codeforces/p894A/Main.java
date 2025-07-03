package com.lzw.solutions.codeforces.p894A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

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

    char ch(int p) {
        return "QAQ".charAt(p);
    }

    int dp(String s, int i, int p) {
        int n = s.length();
        if (p == 3) {
            return 1;
        }
        if (i == n) {
            return 0;
        }
        int cache = map[i][p];
        if (cache != -1) {
            return cache;
        }
        char ch = ch(p);
        int c = 0;
        for (int j = i; j < n; j++) {
            if (s.charAt(j) == ch) {
                c += dp(s, j + 1, p + 1);
            }
        }
        map[i][p] = c;
        return c;
    }

    int maxn = 101;
    int[][] map;

    void solve() throws IOException {
        String s = in.readLine();
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == 'Q' || c == 'A') {
                sb.append(c);
            }
        }
        String ns = sb.toString();
        map = new int[maxn][3];
        for (int i = 0; i < maxn; i++) {
            Arrays.fill(map[i], -1);
        }
        int cnt = dp(ns, 0, 0);
        out.append(String.format("%d\n", cnt));
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }
}
