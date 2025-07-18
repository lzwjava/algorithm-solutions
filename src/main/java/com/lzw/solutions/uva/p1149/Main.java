package com.lzw.solutions.uva.p1149;

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

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            in.readLine();
            int n = Integer.parseInt(in.readLine());
            int l = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(in.readLine());
            }
            Arrays.sort(a);
            int p = 0, q = n - 1;
            int ans = 0;
            while (p < q) {
                if (a[p] + a[q] <= l) {
                    ans++;
                    p++;
                    q--;
                } else {
                    ans++;
                    q--;
                }
            }
            if (p == q) {
                ans++;
            }
            out.append(String.format("%d\n", ans));
            if (t != 0) {
                out.append('\n');
            }
        }
    }
}
