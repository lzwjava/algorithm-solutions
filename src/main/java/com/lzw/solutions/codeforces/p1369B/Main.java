package com.lzw.solutions.codeforces.p1369B;

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

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

    void close() {
        out.flush();
        out.close();
    }

    String cal(String s) {
        int n = s.length();
        int left1 = 0;
        for (int j = 0; j < n; j++) {
            if (s.charAt(j) == '1') {
                left1++;
            }
        }
        int right0 = 0;
        StringBuilder sb = new StringBuilder(s);
        int i = s.length() - 1;
        while (true) {
            int n1 = sb.length();
            if (i < 0) {
                return sb.toString();
            }
            if (i == n1 - 1 || sb.charAt(i) == '0' || (sb.charAt(i) == '1' && sb.charAt(i + 1) == '1')) {
                char c = sb.charAt(i);
                if (c == '1') {
                    left1--;
                } else {
                    right0++;
                }
                i--;
            } else {
                // 1, 0
                int sleft1 = left1 - 1;
                int sright0 = right0 - 1;
                if (sleft1 < sright0) {
                    // remove 0
                    right0--;
                    sb.deleteCharAt(i + 1);
                } else {
                    // remove 1
                    left1--;
                    sb.deleteCharAt(i);
                    i--;
                }
            }
        }
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            int n = Integer.parseInt(in.readLine());
            String s = in.readLine();
            String ans = cal(s);
            out.append(String.format("%s\n", ans));
        }
    }
}
