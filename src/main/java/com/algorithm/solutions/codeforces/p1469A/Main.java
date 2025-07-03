package com.algorithm.solutions.codeforces.p1469A;

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

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

    int maxn = 101;

    int[][] map;

    boolean dp(String s, int i, int level) {
        int cache = map[i][level];
        if (cache != -1) {
            return cache == 1;
        }
        boolean ans = false;
        if (i == s.length()) {
            ans = level == 0;
        } else {
            char c = s.charAt(i);
            if (c == '(') {
                ans = dp(s, i + 1, level + 1);
            } else if (c == ')') {
                if (level == 0) {
                    ans = false;
                } else {
                    ans = dp(s, i + 1, level - 1);
                }
            } else if (c == '?') {
                // (
                boolean ok = dp(s, i + 1, level + 1);
                if (ok) {
                    ans = true;
                } else {
                    if (level > 0) {
                        // )
                        ok = dp(s, i + 1, level - 1);
                        if (ok) {
                            ans = true;
                        }
                    }
                }
            }
        }
        int v = ans ? 1 : 0;
        map[i][level] = v;
        return ans;
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            String s = in.readLine();
            map = new int[maxn][maxn];
            for (int i = 0; i < maxn; i++) {
                Arrays.fill(map[i], -1);
            }
            boolean ans = dp(s, 0, 0);
            if (ans) {
                out.append("YES\n");
            } else {
                out.append("NO\n");
            }
        }
    }

}