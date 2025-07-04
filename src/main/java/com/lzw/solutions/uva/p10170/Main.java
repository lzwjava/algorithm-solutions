package com.lzw.solutions.uva.p10170;

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
            String line = in.readLine();
            if (line == null) {
                break;
            }
            StringTokenizer st = new StringTokenizer(line);
            long s = Long.parseLong(st.nextToken());
            long d = Long.parseLong(st.nextToken());
            long x = s;
            while (true) {
                long len = x - s + 1;
                long sum = (s + x) * len / 2;
                if (sum >= d) {
                    break;
                } else {
                    x++;
                }
            }
            out.append(String.format("%d\n", x));
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
