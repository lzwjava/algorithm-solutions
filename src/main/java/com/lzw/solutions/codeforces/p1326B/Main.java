package com.lzw.solutions.codeforces.p1326B;

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

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
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
        int n = Integer.parseInt(in.readLine());
        int[] b = parseArray(in.readLine());
        int v;
        int last = 0;
        for (int i = 0; i < n; i++) {
            // a[i] - last = b[i]
            if (i != 0) {
                out.append(' ');
            }
            v = last + b[i];
            last = Integer.max(last, v);
            out.append(String.format("%d", v));
        }
    }
}
