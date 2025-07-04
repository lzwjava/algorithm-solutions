package com.lzw.solutions.uva.p10179;

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
        while (true) {
            int n = Integer.parseInt(in.readLine());
            if (n == 0) {
                break;
            }
            int result = n;
            for (int i = 2; i * i <= n; i++) {
                if (n % i == 0) {
                    while (n % i == 0) {
                        n /= i;
                    }
                    result -= result / i;
                }
            }
            if (n > 1) {
                result -= result / n;
            }
            out.append(String.format("%d\n", result));
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
