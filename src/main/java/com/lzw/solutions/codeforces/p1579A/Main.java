package com.lzw.solutions.codeforces.p1579A;

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
            String s = in.readLine();
            int a = 0, b = 0, c = 0;
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (ch == 'A') {
                    a++;
                } else if (ch == 'B') {
                    b++;
                } else if (ch == 'C') {
                    c++;
                }
            }
            if (a + c == b) {
                out.append(String.format("YES\n"));
            } else {
                out.append(String.format("NO\n"));
            }
            t--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}