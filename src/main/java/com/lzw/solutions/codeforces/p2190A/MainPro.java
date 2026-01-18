package com.lzw.solutions.codeforces.p2190A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MainPro {

    BufferedReader in;
    PrintWriter out;

    MainPro() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            String s = in.readLine().trim();
            int i;
            int zero = 0, one = 0;
            for (i = 0; i < n; i++) {
                if (s.charAt(i) == '0') {
                    zero++;
                } else {
                    one++;
                }
            }
            if (zero == n || one == n) {
                out.println("Bob");
            } else {
                int leftOne = 0, rightZero = 0;
                for (i = 0; i < n; i++) {
                    if (s.charAt(i) == '1') {
                        leftOne = i;
                        break;
                    }
                }
                for (i = n - 1; i >= 0; i--) {
                    if (s.charAt(i) == '0') {
                        rightZero = i;
                        break;
                    }
                }

                if (rightZero < leftOne) {
                    out.println("Bob");
                } else {
                    out.println("Alice");
                    out.println(2);
                    out.println(String.format("%d %d", leftOne + 1, rightZero + 1));
                }
            }
        }
    }

    void close() throws IOException {
        if (in != null) {
            in.close();
        }
        if (out != null) {
            out.flush();
            out.close();
        }
    }

    public static void main(String[] args) throws Exception {
        MainPro main = new MainPro();
        main.solve();
        main.close();
    }
}
