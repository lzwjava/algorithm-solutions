package com.lzw.solutions.codeforces.p255A;

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

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

    void close() {
        out.flush();
        out.close();
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
        int[] times = new int[3];
        for (int i = 0; i < n; i++) {
            times[i % 3] += a[i];
        }
        if (times[0] > times[1] && times[0] > times[2]) {
            out.append("chest\n");
        } else if (times[1] > times[0] && times[1] > times[2]) {
            out.append("biceps\n");
        } else {
            out.append("back\n");
        }
    }
}
