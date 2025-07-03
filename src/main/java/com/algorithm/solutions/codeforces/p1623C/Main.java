package com.algorithm.solutions.codeforces.p1623C;

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

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

    int[] parseArray(String s) {
        StringTokenizer st = new StringTokenizer(s);
        int n = st.countTokens();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        return a;
    }

    boolean check(int[] h, int n, int x) {
        int[] gives = new int[n];
        for (int i = n - 1; i >= 2; i--) {
            if (h[i] + gives[i] < x) {
                return false;
            }
            int m = h[i] + gives[i] - x;
            if (m >= h[i]) {
                m = h[i];
            }
            int d = m / 3;

            h[i] -= 3 * d;
            gives[i - 1] += d;
            gives[i - 2] += 2 * d;
        }
        for (int i = 0; i < 2; i++) {
            if (h[i] + gives[i] < x) {
                return false;
            }
        }
        return true;
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            int n = Integer.parseInt(in.readLine());
            int[] h = parseArray(in.readLine());
            int left = Integer.MAX_VALUE, right = 0;
            for (int i = 0; i < n; i++) {
                if (h[i] < left) {
                    left = h[i];
                }
                if (h[i] > right) {
                    right = h[i];
                }
            }
            while (left + 1 < right) {
                int mid = (left + right) / 2;
                int[] th = h.clone();
                if (check(th, n, mid)) {
                    left = mid;
                } else {
                    right = mid - 1;
                }
            }
            if (check(h, n, right)) {
                out.append(String.format("%d\n", right));
            } else {
                out.append(String.format("%d\n", left));
            }

        }
    }

}