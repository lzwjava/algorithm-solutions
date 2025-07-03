package com.lzw.solutions.codeforces.p1409B;

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

    long cal1(int a, int b, int x, int y, int n) {
        int m1 = Integer.min(n, b - y);
        b -= m1;
        n -= m1;
        if (n > 0) {
            int m2 = Integer.min(n, a - x);
            a -= m2;
            n -= m2;
        }
        return (long) a * b;
    }

    long cal2(int a, int b, int x, int y, int n) {
        int m1 = Integer.min(n, a - x);
        a -= m1;
        n -= m1;
        if (n > 0) {
            int m2 = Integer.min(n, b - y);
            b -= m2;
            n -= m2;
        }
        return (long) a * b;
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            long s1 = cal1(a, b, x, y, n);
            long s2 = cal2(a, b, x, y, n);
            long s = Math.min(s1, s2);
            out.append(String.format("%d\n", s));
            t--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}