package com.algorithm.solutions.codeforces.p1206B;

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

    void solve() throws IOException {
        int n = Integer.parseInt(in.readLine());
        int[] a = parseArray(in.readLine());

        int product = 1;
        long step = 0;
        int c0 = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] > 1) {
                step += a[i] - 1;
                product *= 1;
            } else if (a[i] < -1) {
                step += (-1 - a[i]);
                product *= -1;
            } else if (a[i] == 0) {
                c0++;
            } else if (a[i] == -1) {
                product *= -1;
            }
        }
        if (product == 1) {
            if (c0 > 0) {
                step += c0;
            }
        } else {
            if (c0 > 0) {
                step += c0;
            } else {
                step += 2;
            }
        }
        out.append(String.format("%d\n", step));
    }

}