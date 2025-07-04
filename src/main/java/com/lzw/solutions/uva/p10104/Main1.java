package com.lzw.solutions.uva.p10104;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main1 {

    BufferedReader in;
    PrintWriter out;

    Main1() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void solve() throws IOException {
        while (true) {
            String line = in.readLine();
            if (line == null) {
                break;
            }
            StringTokenizer st = new StringTokenizer(line);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d;
            int min = Math.min(a, b);
            for (d = min; d >= 1; d--) {
                if (a % d == 0 && b % d == 0) {
                    break;
                }
            }
            int max = Math.max(a, b);
            int fx = Integer.MAX_VALUE / 2, fy = Integer.MAX_VALUE / 2;
            for (int x = -max; x <= max; x++) {
                int m = d - a * x;
                if (m % b == 0) {
                    int y = m / b;
                    if (Math.abs(x) + Math.abs(y) < Math.abs(fx) + Math.abs(fy)
                            || (Math.abs(x) + Math.abs(y) == Math.abs(fx) + Math.abs(fy) && x <= y)) {
                        fx = x;
                        fy = y;
                    }
                }
            }
            out.append(String.format("%d %d %d\n", fx, fy, d));
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

        Main1 main = new Main1();
        main.solve();
        main.close();
    }
}
