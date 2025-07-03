package com.lzw.solutions.codeforces.p1560B;

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
        int tt = Integer.parseInt(in.readLine());
        while (tt > 0) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (a > b) {
                int t = a;
                a = b;
                b = t;
            }
            // a+n-b = b-a
            // n = 2*b-2*a;
            int n = 2 * b - 2 * a;
            if (b <= n && c <= n) {
                int d = (b - a + c - 1) % n + 1;
                out.append(String.format("%d\n", d));
            } else {
                out.append("-1\n");
            }
            tt--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}