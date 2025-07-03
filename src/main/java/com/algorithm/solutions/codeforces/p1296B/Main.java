package com.algorithm.solutions.codeforces.p1296B;

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
            int s = Integer.parseInt(in.readLine());
            int p = 0;
            while (s != 0) {
                if (s >= 10) {
                    int spend = s / 10 * 10;
                    p += spend;
                    s -= spend;
                    s += spend / 10;
                } else {
                    p += s;
                    s -= s;
                }
            }
            out.append(String.format("%d\n", p));
            t--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}