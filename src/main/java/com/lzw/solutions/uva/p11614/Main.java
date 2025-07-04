package com.lzw.solutions.uva.p11614;

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

    long cal(long n) {
        long r = (long) Math.sqrt(2 * n) - 1;
        while (true) {
            long a = r * (r + 1) / 2;
            long b = (r + 1) * (r + 2) / 2;
            if (n >= a && n < b) {
                return r;
            }
            r++;
        }
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            long n = Long.parseLong(in.readLine());
            long ans = cal(n);
            out.append(String.format("%d\n", ans));
            t--;
        }
    }

    void close() throws IOException {
        if (in != null) {
            in.close();
        }
        if (out != null) {
            out.flush();
            out.close();
        }
    }

    public static void main(String[] args) throws Exception {

        Main main = new Main();
        main.solve();
        main.close();
    }
}
