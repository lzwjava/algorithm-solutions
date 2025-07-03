package com.algorithm.solutions.codeforces.p1611A;

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

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            int n = Integer.parseInt(in.readLine());
            int ans;
            if (n % 2 == 0) {
                ans = 0;
            } else {
                boolean hasEven = false;
                while (n >= 10) {
                    int d = n % 10;
                    n /= 10;
                    if (d % 2 == 0) {
                        hasEven = true;
                    }
                }
                if (n % 2 == 0) {
                    ans = 1;
                } else {
                    if (hasEven) {
                        ans = 2;
                    } else {
                        ans = -1;
                    }
                }
            }
            out.append(String.format("%d\n", ans));
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}