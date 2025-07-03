package com.lzw.solutions.codeforces.p149A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
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
        int k = Integer.parseInt(in.readLine());
        int n = 12;
        int[] a = new int[n];
        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(a);
        int s = 0;
        int p = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (s >= k) {
                break;
            }
            s += a[i];
            p++;
        }
        if (s >= k) {
            out.append(String.format("%d\n", p));
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
