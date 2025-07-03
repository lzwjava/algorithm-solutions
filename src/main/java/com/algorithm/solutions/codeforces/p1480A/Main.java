package com.algorithm.solutions.codeforces.p1480A;

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
            int n = s.length();
            boolean alice = true;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                char nc;
                if (alice) {
                    nc = small(c);
                } else {
                    nc = large(c);
                }
                sb.append(nc);
                alice = !alice;
            }
            out.append(String.format("%s\n", sb));
        }
    }

    char small(char c) {
        if (c != 'a') {
            return 'a';
        } else {
            return 'b';
        }
    }

    char large(char c) {
        if (c != 'z') {
            return 'z';
        } else {
            return 'y';
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}