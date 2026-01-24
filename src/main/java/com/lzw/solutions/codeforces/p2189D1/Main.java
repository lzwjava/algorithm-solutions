// grok-4.1-thinking
package com.lzw.solutions.codeforces.p2189D1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {
    private static final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static final PrintWriter out = new PrintWriter(System.out, true);
    private static final long MOD = 1000000007L;

    private static void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        for (int test = 0; test < t; test++) {
            String[] nc = in.readLine().split("\\s+");
            int n = Integer.parseInt(nc[0]);
            long c = Long.parseLong(nc[1]);
            String s = in.readLine().trim();

            boolean possible = (s.charAt(n - 1) == '1');
            long res = 1L;
            long chk = 1L;

            for (int k = 1; k < n; k++) {
                char ch = s.charAt(k - 1);
                long mul = (ch == '1' ? 2L : (long) k - 1L);

                if (mul == 0L) {
                    possible = false;
                }

                res = (res * mul) % MOD;

                if (c != 0) {
                    chk = (chk * mul) % c;
                }
            }

            if (!possible || chk == 0L) {
                out.println(-1);
            } else {
                out.println(res);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        solve();
        out.close();
    }
}
