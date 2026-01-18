package com.lzw.solutions.codeforces.p2190B1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {
    private static final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static final PrintWriter out = new PrintWriter(System.out, true);

    // (()(()))
    // (())
    // ((()))
    // (()())

    private static void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            String s = in.readLine();
            assert (s.length() == n);
            out.println(s);
        }
    }

    public static void main(String[] args) throws IOException {
        solve();
        out.close();
    }
}
