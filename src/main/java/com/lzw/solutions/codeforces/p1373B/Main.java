package com.lzw.solutions.codeforces.p1373B;

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
            String s = in.readLine();
            int c0 = 0, c1 = 0;
            for (char c : s.toCharArray()) {
                if (c == '0') {
                    c0++;
                } else {
                    c1++;
                }
            }
            int min = Math.min(c0, c1);
            if (min % 2 == 1) {
                out.append("DA\n");
            } else {
                out.append("NET\n");
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
