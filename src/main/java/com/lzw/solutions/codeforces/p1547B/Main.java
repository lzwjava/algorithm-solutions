package com.lzw.solutions.codeforces.p1547B;

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
            char c1 = s.charAt(0);
            char c2 = s.charAt(n - 1);
            char c = (char) Integer.max(c1, c2);
            int p = 0;
            int q = n - 1;
            boolean ok = true;
            int i = 0;
            while (i < n) {
                if (s.charAt(p) == c) {
                    p++;
                    c--;
                    i++;
                } else if (s.charAt(q) == c) {
                    q--;
                    c--;
                    i++;
                } else {
                    ok = false;
                    break;
                }
            }
            if (ok && i == n && c == 'a' - 1) {
                out.append("YES\n");
            } else {
                out.append("NO\n");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}