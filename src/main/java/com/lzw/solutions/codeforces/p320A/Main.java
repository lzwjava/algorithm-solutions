package com.lzw.solutions.codeforces.p320A;

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
        String s = in.readLine();
        int p = 0;
        int n = s.length();
        while (p < n) {
            if (p + 3 <= n) {
                String s3 = s.substring(p, p + 3);
                if (s3.equals("144")) {
                    p += 3;
                    continue;
                }
            }
            if (p + 2 <= n) {
                String s2 = s.substring(p, p + 2);
                if (s2.equals("14")) {
                    p += 2;
                    continue;
                }
            }
            if (p + 1 <= n) {
                String s1 = s.substring(p, p + 1);
                if (s1.equals("1")) {
                    p += 1;
                    continue;
                }
            }
            break;
        }
        if (p == s.length()) {
            out.append("YES\n");
        } else {
            out.append("NO\n");
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}