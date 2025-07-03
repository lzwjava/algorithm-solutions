package com.algorithm.solutions.codeforces.p706B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    void solve() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        int[] x = new int[n];
        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; i++) {
            x[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(x);
        int q = Integer.parseInt(in.readLine());
        Map<Integer, Integer> map = new HashMap<>();
        while (q > 0) {
            int m = Integer.parseInt(in.readLine());
            if (m > x[n - 1]) {
                m = x[n - 1];
            }
            int ans;
            Integer c = map.get(m);
            if (c != null) {
                ans = c;
            } else {
                int i = Arrays.binarySearch(x, m);
                if (i >= 0) {
                    while (i + 1 < n && x[i + 1] == x[i]) {
                        i++;
                    }
                    ans = i + 1;
                } else {
                    i = -(i + 1);
                    ans = i;
                }
                map.put(m, ans);
            }
            out.append(String.format("%d\n", ans));
            q--;
        }
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Main().solve();
    }

}
