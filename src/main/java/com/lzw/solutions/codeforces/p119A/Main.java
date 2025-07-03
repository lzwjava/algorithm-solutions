package com.lzw.solutions.codeforces.p119A;

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

    int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

    void solve() throws IOException {
        StringTokenizer st = new StringTokenizer(in.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        boolean simon = true;
        boolean simonWin;
        while (true) {
            if (simon) {
                int v = gcd(a, n);
                if (v <= n) {
                    n -= v;
                } else {
                    simonWin = false;
                    break;
                }
            } else {
                int v = gcd(b, n);
                if (v <= n) {
                    n -= v;
                } else {
                    simonWin = true;
                    break;
                }
            }
            simon = !simon;
        }
        if (simonWin) {
            out.append("0\n");
        } else {
            out.append("1\n");
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}