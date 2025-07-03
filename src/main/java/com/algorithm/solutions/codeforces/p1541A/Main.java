package com.algorithm.solutions.codeforces.p1541A;

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

    void close() throws IOException {
        in.close();
        out.flush();
        out.close();
    }

    void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    void solve() throws IOException {
        int tt = Integer.parseInt(in.readLine());
        while (tt > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = i;
            }
            if (n % 2 == 0) {
                for (int i = 0; i < n; i += 2) {
                    swap(a, i, i + 1);
                }
            } else {
                a[0] = 2;
                a[1] = 0;
                a[2] = 1;
                for (int i = 3; i < n; i += 2) {
                    swap(a, i, i + 1);
                }
            }
            for (int i = 0; i < n; i++) {
                if (i != 0) {
                    out.append(' ');
                }
                out.append(String.format("%d", a[i] + 1));
            }
            out.append('\n');
            tt--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}