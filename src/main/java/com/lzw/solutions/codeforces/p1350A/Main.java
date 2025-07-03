package com.lzw.solutions.codeforces.p1350A;

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

    int f(int x) {
        for (int y = 2; y <= x; y++) {
            if (x % y == 0) {
                return y;
            }
        }
        return 0;
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            StringTokenizer st = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int i;
            for (i = 0; i < k; i++) {
                n = f(n) + n;
                if (n % 2 == 0) {
                    break;
                }
            }
            int ans = n + (k - i - 1) * 2;
            out.append(String.format("%d\n", ans));
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }
}
