package com.lzw.solutions.codeforces.p1285A;

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
        String s = in.readLine();
        int l = 0, r = 0;
        for (char c : s.toCharArray()) {
            if (c == 'L') {
                l++;
            } else if (c == 'R') {
                r++;
            }
        }
        out.append(String.format("%d\n", l + r + 1));
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}