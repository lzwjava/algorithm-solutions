package com.lzw.solutions.uva.p12554;

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
        String[] names = new String[n];
        for (int i = 0; i < n; i++) {
            names[i] = in.readLine();
        }
        String[] words = new String[] {
            "Happy", "birthday", "to", "you",
            "Happy", "birthday", "to", "you",
            "Happy", "birthday", "to", "Rujia",
            "Happy", "birthday", "to", "you"
        };
        int len = 16;
        while (len < n) {
            len += 16;
        }
        for (int i = 0; i < len; i++) {
            String name = names[i % n];
            String word = words[i % 16];
            out.append(String.format("%s: %s\n", name, word));
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
