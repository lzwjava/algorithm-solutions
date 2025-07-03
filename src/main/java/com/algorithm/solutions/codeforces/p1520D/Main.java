package com.algorithm.solutions.codeforces.p1520D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
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

    Map<Integer, Integer> map;

    void count(int v) {
        Integer c = map.get(v);
        if (c == null) {
            c = 0;
        }
        c++;
        map.put(v, c);
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }
            map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                a[i] = a[i] - i;
                count(a[i]);
            }
            long ans = 0;
            for (int key : map.keySet()) {
                int c = map.get(key);
                if (c >= 2) {
                    ans += (long) c * (c - 1) / 2;
                }
            }
            out.append(String.format("%d\n", ans));
            t--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}