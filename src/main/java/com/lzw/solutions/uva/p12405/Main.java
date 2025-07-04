package com.lzw.solutions.uva.p12405;

import java.io.*;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int dp(String s) {
        boolean allHash = true;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '#') {
                allHash = false;
                break;
            }
        }
        if (allHash) {
            return 0;
        }
        if (s.startsWith("#") || s.endsWith("#")) {
            int start = s.indexOf('.');
            int last = s.lastIndexOf('.');
            String ss = s.substring(start, last + 1);
            return dp(ss);
        } else {
            if (s.length() <= 3) {
                return 1;
            } else {
                return dp(s.substring(0, 3)) + dp(s.substring(3));
            }
        }
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        for (int u = 0; u < t; u++) {
            int n = Integer.parseInt(in.readLine());
            String s = in.readLine();
            int ans = dp(s);
            out.append(String.format("Case %d: %d\n", u + 1, ans));
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
