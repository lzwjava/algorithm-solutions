package com.lzw.solutions.codeforces.p1538A;

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
        int tt = Integer.parseInt(in.readLine());
        while (tt > 0) {
            int n = Integer.parseInt(in.readLine());
            StringTokenizer st = new StringTokenizer(in.readLine());
            int[] a = new int[n];
            int max = 0, min = n;
            int maxi = 0, mini = 0;
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
                if (a[i] > max) {
                    max = a[i];
                    maxi = i;
                }
                if (a[i] < min) {
                    min = a[i];
                    mini = i;
                }
            }
            if (maxi < mini) {
                int t = maxi;
                maxi = mini;
                mini = t;
            }
            // mini, maxi
            int m1 = maxi + 1;
            int m2 = n - mini;
            int m3 = mini + 1 + n - maxi;
            int m = Integer.min(m1, m2);
            m = Integer.min(m, m3);
            out.append(String.format("%d\n", m));
            tt--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}