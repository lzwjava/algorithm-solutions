package com.lzw.solutions.codeforces.p1196A;

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
        int q = Integer.parseInt(in.readLine());
        while (q > 0) {
            long a, b, c;
            StringTokenizer st = new StringTokenizer(in.readLine());
            a = Long.parseLong(st.nextToken());
            b = Long.parseLong(st.nextToken());
            c = Long.parseLong(st.nextToken());
            long s = a + b + c;
            long ans;
            if (s % 2 == 0) {
                ans = s / 2;
            } else {
                ans = (s - 1) / 2;
            }
            out.append(String.format("%d\n", ans));
            q--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}