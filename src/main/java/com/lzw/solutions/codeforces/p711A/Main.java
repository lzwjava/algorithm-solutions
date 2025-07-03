package com.lzw.solutions.codeforces.p711A;

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
        int n = Integer.parseInt(in.readLine());
        boolean ok = false;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            String s = in.readLine();
            if (ok) {
                sb.append(String.format("%s\n", s));
            } else {
                if (s.contains("OO")) {
                    ok = true;
                    s = s.replaceFirst("OO", "++");
                    sb.append(String.format("%s\n", s));
                } else {
                    sb.append(String.format("%s\n", s));
                }
            }
        }
        if (ok) {
            out.append("YES\n");
            out.append(String.format("%s\n", sb));
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