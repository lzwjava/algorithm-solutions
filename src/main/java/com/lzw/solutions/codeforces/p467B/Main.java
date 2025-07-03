package com.lzw.solutions.codeforces.p467B;

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
        int n, m, k;
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        int[] x = new int[m];
        for (int i = 0; i < m; i++) {
            x[i] = Integer.parseInt(in.readLine());
        }
        int p = Integer.parseInt(in.readLine());
        int ans = 0;
        for (int i = 0; i < m; i++) {
            int c = Integer.bitCount(x[i] ^ p);
            if (c <= k) {
                ans++;
            }
        }
        out.append(String.format("%d\n", ans));
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}