package com.lzw.solutions.codeforces.p1517A;

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
            String s = in.readLine();
            long n = Long.parseLong(s);
            int num = 2050;
            if (n < num) {
                out.append("-1\n");
                continue;
            }
            int k = (int) Math.log10(n / num);
            int c = 0;
            while (n != 0 && k >= 0) {
                if (n < num) {
                    break;
                }
                long d = (long) (num * Math.pow(10, k));
                if (n >= d) {
                    n -= d;
                    c++;
                } else {
                    k--;
                }
            }
            if (n != 0) {
                out.append("-1\n");
            } else {
                out.append(String.format("%d\n", c));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }
}
