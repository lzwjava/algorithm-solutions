package com.algorithm.solutions.codeforces.p265A;

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
        String t = in.readLine();
        int p = 0;
        for (char c : t.toCharArray()) {
            if (c == s.charAt(p)) {
                p++;
            }
        }
        out.append(String.format("%d\n", p + 1));
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}