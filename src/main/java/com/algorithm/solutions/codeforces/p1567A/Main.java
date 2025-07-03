package com.algorithm.solutions.codeforces.p1567A;

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
            int n = Integer.parseInt(in.readLine());
            String s = in.readLine();
            StringBuilder sb = new StringBuilder();
            for (char c : s.toCharArray()) {
                char nc = ' ';
                if (c == 'U') {
                    nc = 'D';
                } else if (c == 'D') {
                    nc = 'U';
                } else if (c == 'L' || c == 'R') {
                    nc = c;
                }
                sb.append(nc);
            }
            out.append(String.format("%s\n", sb));
            t--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}