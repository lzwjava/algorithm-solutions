package com.lzw.solutions.codeforces.p1360C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
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
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(a);
            boolean[] vis = new boolean[n];
            int pair = 0;
            for (int i = 0; i < n - 1; i++) {
                if (!vis[i]) {
                    if (a[i] + 1 == a[i + 1]) {
                        vis[i] = vis[i + 1] = true;
                        pair++;
                    }
                }
            }
            int c0 = 0, c1 = 0;
            for (int i = 0; i < n; i++) {
                if (a[i] % 2 == 0) {
                    c0++;
                } else {
                    c1++;
                }
            }
            boolean ok = false;
            for (int i = 0; i <= pair; i++) {
                int nc0 = c0 - i;
                int nc1 = c1 - i;
                if (nc0 % 2 == 0 && nc1 % 2 == 0) {
                    ok = true;
                    break;
                }
            }
            if (ok) {
                out.append("YES\n");
            } else {
                out.append("NO\n");
            }
            t--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}