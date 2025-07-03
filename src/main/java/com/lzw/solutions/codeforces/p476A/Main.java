package com.lzw.solutions.codeforces.p476A;

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
        int m = Integer.parseInt(st.nextToken());
        int a, b;
        if (n % 2 == 1) {
            b = (n - 1) / 2;
            a = b + 1;
        } else {
            b = n / 2;
            a = b;
        }
        if (m <= a + b) {
            if (a % m != 0) {
                for (int i = 0; i < b; i++) {
                    a++;
                    if (a % m == 0) {
                        break;
                    }
                }
            }
            if (a % m != 0) {
                out.append("-1\n");
            } else {
                out.append(String.format("%d\n", a));
            }
        } else {
            out.append("-1\n");
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}