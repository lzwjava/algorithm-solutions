package com.algorithm.solutions.codeforces.p1328C;

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
            int n = Integer.parseInt(in.readLine());
            String x = in.readLine();
            StringBuilder sb = new StringBuilder();
            int i;
            boolean one = false;
            for (i = 0; i < x.length(); i++) {
                if (one) {
                    sb.append('0');
                    continue;
                }
                char c = x.charAt(i);
                if (c == '2') {
                    sb.append('1');
                } else if (c == '0') {
                    sb.append('0');
                } else if (c == '1') {
                    sb.append('1');
                    one = true;
                }
            }
            String a = sb.toString();
            StringBuilder sb2 = new StringBuilder();
            for (int j = 0; j < x.length(); j++) {
                char c = (char) (x.charAt(j) - a.charAt(j) + '0');
                sb2.append(c);
            }
            String b = sb2.toString();
            out.append(String.format("%s\n%s\n", a, b));
            t--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}