package com.lzw.solutions.codeforces.p118A;

import java.io.*;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    boolean vowel(char ch) {
        String vs = "AOYEUI";
        char c = Character.toUpperCase(ch);
        return vs.indexOf(c) >= 0;
    }

    void solve() throws IOException {
        String s = in.readLine();
        StringBuilder sb = new StringBuilder();
        for (char ch : s.toCharArray()) {
            if (!vowel(ch)) {
                sb.append('.');
                char lch = Character.toLowerCase(ch);
                sb.append(lch);
            }
        }
        out.append(String.format("%s\n", sb.toString()));
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
