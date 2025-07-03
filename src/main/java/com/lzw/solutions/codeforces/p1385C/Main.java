package com.lzw.solutions.codeforces.p1385C;

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

    boolean good(int[] a, int i, int j) {
        int last = 0;
        while (i != j) {
            if (a[i] <= a[j]) {
                if (last <= a[i]) {
                    last = a[i];
                    i++;
                } else {
                    break;
                }
            } else {
                if (last <= a[j]) {
                    last = a[j];
                    j--;
                } else {
                    break;
                }
            }
        }
        return i == j;
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }
            int start = 0;
            for (int i = n - 2; i >= 1; i--) {
                if (a[i] < a[i + 1] && a[i] < a[i - 1]) {
                    start = i;
                    break;
                }
            }
            int i;
            for (i = start; i < n; i++) {
                if (good(a, i, n - 1)) {
                    break;
                }
            }
            out.append(String.format("%d\n", i));
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}