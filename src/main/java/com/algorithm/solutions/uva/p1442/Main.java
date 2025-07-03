package com.algorithm.solutions.uva.p1442;

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

    boolean check(int[] p, int[] s, int[] v) {
        int n = p.length;
        for (int i = 0; i < n; i++) {
            if (p[i] + v[i] > s[i]) {
                return false;
            }
        }
        return true;
    }

    void solve() throws IOException {
        int z = Integer.parseInt(in.readLine());
        while (z > 0) {
            z--;
            int n = Integer.parseInt(in.readLine());
            int[] p = parseArray(in.readLine());
            int[] s = parseArray(in.readLine());
            int[] v = new int[n];
            boolean ok = check(p, s, v);
        }
    }
}