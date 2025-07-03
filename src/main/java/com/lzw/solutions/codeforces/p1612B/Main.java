package com.lzw.solutions.codeforces.p1612B;

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

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

    void close() {
        out.flush();
        out.close();
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            StringTokenizer st = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int m = n / 2;
            int[] left = new int[m];
            int[] right = new int[m];
            boolean[] vis = new boolean[n + 1];
            vis[a] = vis[b] = true;
            left[0] = a;
            right[0] = b;
            int lp = 1;
            for (int i = n; i >= 1; i--) {
                if (!vis[i]) {
                    left[lp] = i;
                    lp++;
                    if (lp == m) {
                        break;
                    }
                }
            }
            int rp = 1;
            for (int i = 1; i <= n; i++) {
                if (!vis[i]) {
                    right[rp] = i;
                    rp++;
                    if (rp == m) {
                        break;
                    }
                }
            }
            if (lp == m && rp == m && left[0] <= left[m - 1] && right[0] >= right[m - 1]) {
                for (int i = 0; i < n; i++) {
                    if (i != 0) {
                        out.append(' ');
                    }
                    int v;
                    if (i < m) {
                        v = left[i];
                    } else {
                        v = right[i - m];
                    }
                    out.append(String.format("%d", v));
                }
                out.append('\n');
            } else {
                out.append("-1\n");
            }
        }
    }
}
