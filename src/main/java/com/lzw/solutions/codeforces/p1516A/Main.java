package com.lzw.solutions.codeforces.p1516A;

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

    int[] parseArray(String s) {
        StringTokenizer st = new StringTokenizer(s);
        int n = st.countTokens();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        return a;
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            StringTokenizer st = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int[] a = parseArray(in.readLine());
            int p = 0, q = n - 1;
            for (int i = 0; i < k; i++) {
                if (a[p] == 0) {
                    while (a[p] == 0 && p != q) {
                        p++;
                    }
                    if (p == q) {
                        break;
                    }
                }
                a[p]--;
                a[q]++;
            }
            for (int i = 0; i < n; i++) {
                if (i != 0) {
                    out.append(' ');
                }
                out.append(String.format("%d", a[i]));
            }
            out.append('\n');
        }
    }
}
