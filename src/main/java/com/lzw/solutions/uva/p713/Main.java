package com.lzw.solutions.uva.p713;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    String reverse(String s) {
        StringBuilder sb = new StringBuilder();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            sb.append(s.charAt(len - 1 - i));
        }
        return sb.toString();
    }

    void solve() throws IOException {
        String ns = in.readLine();
        int n = Integer.parseInt(ns);
        for (int i = 0; i < n; i++) {
            String s = in.readLine();
            StringTokenizer st = new StringTokenizer(s);
            String a = reverse(st.nextToken());
            String b = reverse(st.nextToken());
            BigInteger c = new BigInteger(a).add(new BigInteger(b));
            String rs = reverse(c.toString());
            String rss = new BigInteger(rs).toString();
            out.append(rss).append('\n');
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

        Main main = new Main();
        main.solve();
        main.close();
    }
}
