package com.algorithm.solutions.codeforces.p1616A;

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
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            int n = Integer.parseInt(in.readLine());
            int[] a = parseArray(in.readLine());
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                int v = Math.abs(a[i]);
                Integer c = map.get(v);
                if (c == null) {
                    c = 0;
                }
                c++;
                map.put(v, c);
            }
            int ans = 0;
            for (int key : map.keySet()) {
                int c = map.get(key);
                if (key == 0) {
                    ans++;
                } else {
                    if (c == 1) {
                        ans++;
                    } else {
                        if (c >= 2) {
                            ans += 2;
                        }
                    }
                }
            }
            out.append(String.format("%d\n", ans));
        }
    }
}