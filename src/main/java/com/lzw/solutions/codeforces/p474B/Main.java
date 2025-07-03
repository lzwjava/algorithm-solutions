package com.lzw.solutions.codeforces.p474B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
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
        int n = Integer.parseInt(in.readLine());
        int[] a = new int[n];
        StringTokenizer st = new StringTokenizer(in.readLine());
        int[] s = new int[n];
        int p = 0;
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
            p += a[i];
            s[i] = p;
        }
        int m = Integer.parseInt(in.readLine());
        st = new StringTokenizer(in.readLine());
        while (m > 0) {
            int q = Integer.parseInt(st.nextToken());
            int i = Arrays.binarySearch(s, q);
            if (i < 0) {
                i = -(i + 1);
            }
            out.append(String.format("%d\n", i + 1));
            m--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }
}
