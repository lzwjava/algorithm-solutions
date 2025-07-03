package com.lzw.solutions.codeforces.p266A;

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

    void solve() throws IOException {
        int n = Integer.parseInt(in.readLine());
        String s = in.readLine();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (i == 0) {
                sb.append(ch);
            } else {
                if (ch == s.charAt(i - 1)) {
                    continue;
                } else {
                    sb.append(ch);
                }
            }
        }
        int cnt = s.length() - sb.toString().length();
        out.append(String.format("%d\n", cnt));
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
        Main main = new Main();
        main.solve();
        main.close();
    }
}
