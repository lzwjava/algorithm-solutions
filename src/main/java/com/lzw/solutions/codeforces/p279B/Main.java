package com.lzw.solutions.codeforces.p279B;

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
        int t = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(in.readLine());
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        int max = 0;
        int p = -1, s = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] > t) {
                p = -1;
                s = 0;
            } else {
                if (p == -1) {
                    p = i;
                    s = a[i];
                } else {
                    while (s + a[i] > t) {
                        s -= a[p];
                        p++;
                    }
                    s += a[i];
                }
                int len = i - p + 1;
                if (len > max) {
                    max = len;
                }
            }
        }
        out.append(String.format("%d\n", max));
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}