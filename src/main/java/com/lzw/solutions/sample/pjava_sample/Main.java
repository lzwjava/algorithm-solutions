package com.lzw.solutions.sample.pjava_sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {

    private final BufferedReader in;
    private final PrintWriter out;

    public Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    private void close() throws IOException {
        in.close();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
    }

    private void solve() {}
}
