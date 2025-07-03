package com.lzw.solutions.codeforces.p734B;

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
        int k2 = Integer.parseInt(st.nextToken());
        int k3 = Integer.parseInt(st.nextToken());
        int k5 = Integer.parseInt(st.nextToken());
        int k6 = Integer.parseInt(st.nextToken());
        int min = Integer.min(k2, k5);
        min = Integer.min(min, k6);
        int s = 0;
        if (min > 0) {
            k2 -= min;
            k5 -= min;
            k6 -= min;
            s += min * 256;
        }
        int max = Integer.min(k2, k3);
        s += max * 32;
        out.append(String.format("%d\n", s));
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}