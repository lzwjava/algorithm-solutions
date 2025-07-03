package com.lzw.solutions.codeforces.p1490C;

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

    long cube(long x) {
        return Math.round(Math.pow(x, 1.0 / 3));
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            long x = Long.parseLong(in.readLine());
            long cx = cube(x / 2);
            boolean ok = false;
            for (long a = 1; a <= cx; a++) {
                long b3 = x - a * a * a;
                if (b3 >= 1) {
                    double pow = Math.pow(b3, 1.0 / 3);
                    if (Math.abs(pow - Math.round(pow)) < 1e-10) {
                        ok = true;
                        break;
                    }
                }

            }
            if (ok) {
                out.append("YES\n");
            } else {
                out.append("NO\n");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}