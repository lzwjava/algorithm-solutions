package com.algorithm.solutions.codeforces.p439A;

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
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int[] t = parseArray(in.readLine());
        int s = 0;
        for (int i = 0; i < n; i++) {
            s += t[i];
        }
        int min = s + (n - 1) * 10;
        int ans;
        if (d < min) {
            ans = -1;
        } else {
            ans = (n - 1) * 2;
            int rest = d - min;
            ans += rest / 5;
        }
        out.append(String.format("%d\n", ans));
    }
}