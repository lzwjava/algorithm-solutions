package com.lzw.solutions.codeforces.p1324A;

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
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            StringTokenizer st = new StringTokenizer(in.readLine());
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
                min = Integer.min(min, a[i]);
            }
            for (int i = 0; i < n; i++) {
                a[i] -= min;
            }
            boolean ok = true;
            for (int i = 1; i < n; i++) {
                if (a[i] % 2 != a[0] % 2) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                out.append("YES\n");
            } else {
                out.append("NO\n");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}