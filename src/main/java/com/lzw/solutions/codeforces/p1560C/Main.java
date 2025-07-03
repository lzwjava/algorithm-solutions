package com.lzw.solutions.codeforces.p1560C;

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
            int k = Integer.parseInt(in.readLine());
            int sk = (int) Math.sqrt(k - 1);
            sk++;
            int p = (sk - 1);
            int q = k - p * p;
            int r, c;
            if (q <= sk) {
                r = q;
                c = sk;
            } else {
                r = sk;
                c = sk - (q - sk);
            }
            out.append(String.format("%d %d\n", r, c));
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}