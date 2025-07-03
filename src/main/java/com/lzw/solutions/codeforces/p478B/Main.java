package com.lzw.solutions.codeforces.p478B;

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

    long max(int n, int m) {
        int d = m - 1;
        int a = n - d;
        return pair(a);
    }

    long pair(long a) {
        return a * (a - 1) / 2;
    }

    long min(int n, int m) {
        int d = n / m;
        if (n % m == 0) {
            return m * pair(d);
        } else {
            int a = n % m;
            return a * pair(d + 1) + (m - a) * pair(d);
        }
    }

    void solve() throws IOException {
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        long min = min(n, m);
        long max = max(n, m);
        out.append(String.format("%d %d\n", min, max));
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }
}
