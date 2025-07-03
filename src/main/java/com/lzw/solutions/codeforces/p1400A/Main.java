package com.lzw.solutions.codeforces.p1400A;

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

    boolean similar(String a, String b) {
        int len = a.length();
        for (int i = 0; i < len; i++) {
            if (a.charAt(i) == b.charAt(i)) {
                return true;
            }
        }
        return false;
    }

    boolean found;

    void permutation(char[] chs, int cur, int n) {
        if (found) {
            return;
        }
        if (cur == n) {
            String ns = new String(chs);
            boolean ok = true;
            for (int i = 0; i < n; i++) {
                String sub = s.substring(i, i + n);
                if (!similar(sub, ns)) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                found = true;
                out.append(String.format("%s\n", ns));
            }
            return;
        }
        for (int i = 0; i < 2; i++) {
            chs[cur] = (char) ('0' + i);
            permutation(chs, cur + 1, n);
        }
    }

    String s;

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            int n = Integer.parseInt(in.readLine());
            s = in.readLine();
            char[] chs = new char[n];
            found = false;
            permutation(chs, 0, n);
            t--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}