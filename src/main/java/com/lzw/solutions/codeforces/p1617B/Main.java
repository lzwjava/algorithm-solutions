package com.lzw.solutions.codeforces.p1617B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

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

    int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

    boolean distinct(int a, int b, int c) {
        return a != b && a != c && b != c;
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            int n = Integer.parseInt(in.readLine());
            int p = 1;
            for (int i = 0; i * p + p < n; i++) {
                int a = i * p;
                int b = n - p - a;
                if (b >= 0 && distinct(a, b, p) && gcd(a, b) == p) {
                    out.append(String.format("%d %d %d\n", a, b, p));
                    break;
                }
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
