package com.algorithm.solutions.codeforces.p1335C;

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

    int count(Map<Integer, Integer> map, int v) {
        Integer c = map.get(v);
        if (c == null) {
            c = 0;
        }
        c++;
        map.put(v, c);
        return c;
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            StringTokenizer st = new StringTokenizer(in.readLine());
            Map<Integer, Integer> map = new HashMap<>();
            int maxc = 0, maxv = 0;
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
                int c = count(map, a[i]);
                if (c > maxc) {
                    maxc = c;
                    maxv = a[i];
                }
            }
            int c1 = 0, c2 = map.get(maxv);
            for (int key : map.keySet()) {
                if (key != maxv) {
                    c1++;
                }
            }
            int ans;
            if (c1 == c2) {
                ans = c1;
            } else if (c1 > c2) {
                ans = c2;
            } else {
                // c1 < c2
                ans = Integer.min(c1 + 1, c2 - 1);
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