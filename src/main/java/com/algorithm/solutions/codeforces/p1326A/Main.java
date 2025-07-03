package com.algorithm.solutions.codeforces.p1326A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Random;

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
        Random random = new Random();
        while (t > 0) {
            int n = Integer.parseInt(in.readLine());
            if (n == 1) {
                out.append("-1\n");
            } else {
                StringBuilder sb = new StringBuilder();
                int s = 3;
                for (int i = 0; i < n - 2; i++) {
                    sb.append(String.format("%d", 2));
                    s += 2;
                }
                if ((s + 2) % 3 == 0) {
                    sb.append(String.format("3"));
                } else {
                    sb.append(String.format("2"));
                }
                sb.append("3");
                out.append(String.format("%s\n", sb));
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