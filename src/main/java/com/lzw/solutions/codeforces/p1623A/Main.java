package com.lzw.solutions.codeforces.p1623A;

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

    void solve() throws IOException {
        int tt = Integer.parseInt(in.readLine());
        while (tt > 0) {
            tt--;
            StringTokenizer st = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int rb = Integer.parseInt(st.nextToken());
            int cb = Integer.parseInt(st.nextToken());
            int rd = Integer.parseInt(st.nextToken());
            int cd = Integer.parseInt(st.nextToken());

            int t = 0;
            int dr = 1, dc = 1;
            while (true) {
                if (rb == rd || cb == cd) {
                    break;
                }

                if (rb + dr > n || rb + dr < 1) {
                    dr *= -1;
                }
                if (cb + dc > m || cb + dc < 1) {
                    dc *= -1;
                }
                rb += dr;
                cb += dc;
                t++;
            }
            out.append(String.format("%d\n", t));
        }
    }

}