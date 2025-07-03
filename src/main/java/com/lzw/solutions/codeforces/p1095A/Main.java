package com.lzw.solutions.codeforces.p1095A;

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

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

    void solve() throws IOException {
        int n = Integer.parseInt(in.readLine());
        String s = in.readLine();
        StringBuilder sb = new StringBuilder();
        int p = 0;
        int q = 1;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (i == p) {
                sb.append(c);
                p += q;
                q++;
            }
        }
        out.append(String.format("%s\n", sb));
    }
}
