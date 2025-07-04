package com.lzw.solutions.codeforces.p282A;

import java.io.*;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void solve() throws IOException {
        int n = Integer.parseInt(in.readLine());
        int v = 0;
        while (n > 0) {
            String s = in.readLine();
            if (s.contains("++")) {
                v++;
            } else if (s.contains("--")) {
                v--;
            }
            n--;
        }
        out.append(String.format("%d\n", v));
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
