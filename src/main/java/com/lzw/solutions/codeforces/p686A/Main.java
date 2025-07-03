package com.lzw.solutions.codeforces.p686A;

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

    void close() throws IOException {
        in.close();
        out.flush();
        out.close();
    }

    void solve() throws IOException {
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        long x = Integer.parseInt(st.nextToken());
        int d = 0;
        while (n > 0) {
            st = new StringTokenizer(in.readLine());
            char sign = st.nextToken().charAt(0);
            long v = Integer.parseInt(st.nextToken());
            if (sign == '+') {
                x += v;
            } else if (sign == '-') {
                if (x >= v) {
                    x -= v;
                } else {
                    d++;
                }
            }
            n--;
        }
        out.append(String.format("%d %d\n", x, d));
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}