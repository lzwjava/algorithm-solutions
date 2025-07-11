package com.lzw.solutions.uva.p10432;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void solve() throws IOException {
        while (true) {
            String s = in.readLine();
            if (s == null || s.isEmpty()) {
                break;
            }
            StringTokenizer st = new StringTokenizer(s);
            double r = Double.parseDouble(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            double area = 1.0 * r * r * Math.sin(2 * Math.PI / n) / 2;
            out.append(String.format("%.3f\n", n * area));
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
