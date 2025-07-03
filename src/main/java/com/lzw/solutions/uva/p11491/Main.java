package com.lzw.solutions.uva.p11491;

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

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

    void solve() throws IOException {
        while (true) {
            int n = Integer.parseInt(in.readLine());
            int d = Integer.parseInt(in.readLine());
            if (n == 0 && d == 0) {
                break;
            }
        }
    }

}