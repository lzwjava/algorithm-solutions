package com.lzw.solutions.codeforces.p1607A;

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
            String a = in.readLine();
            String b = in.readLine();
            int ans = 0;
            for (int i = 0; i < b.length() - 1; i++) {
                char c1 = b.charAt(i);
                int i1 = a.indexOf(c1);

                char c2 = b.charAt(i + 1);
                int i2 = a.indexOf(c2);
                ans += Math.abs(i1 - i2);
            }
            out.append(String.format("%d\n", ans));
            t--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}