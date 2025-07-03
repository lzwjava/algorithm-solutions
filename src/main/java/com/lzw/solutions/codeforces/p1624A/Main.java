package com.lzw.solutions.codeforces.p1624A;

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

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

    void close() {
        out.flush();
        out.close();
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            int n = Integer.parseInt(in.readLine());
            StringTokenizer st = new StringTokenizer(in.readLine());
            int min = 0, max = 0;
            for (int i = 0; i < n; i++) {
                int x = Integer.parseInt(st.nextToken());
                if (i == 0) {
                    min = max = x;
                } else {
                    min = Integer.min(min, x);
                    max = Integer.max(max, x);
                }
            }
            System.out.println(max - min);
        }
    }
}