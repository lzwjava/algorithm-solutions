package com.algorithm.solutions.codeforces.p433B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

    int n;
    List<Integer> v;
    long[] sums;
    long[] sortedSums;

    void solve() throws IOException {
        n = Integer.parseInt(in.readLine());
        v = new ArrayList<>();
        long s = 0;
        StringTokenizer st = new StringTokenizer(in.readLine());
        sums = new long[n];
        for (int i = 0; i < n; i++) {
            int a = Integer.parseInt(st.nextToken());
            v.add(a);
            s += a;
            sums[i] = s;
        }
        int m = Integer.parseInt(in.readLine());
        List<Integer> sorted = new ArrayList<>(v);
        Collections.sort(sorted);
        s = 0;
        sortedSums = new long[n];
        for (int i = 0; i < n; i++) {
            s += sorted.get(i);
            sortedSums[i] = s;
        }
        while (m > 0) {
            m--;
            st = new StringTokenizer(in.readLine());
            int t = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken()) - 1;
            int r = Integer.parseInt(st.nextToken()) - 1;
            long ans;
            if (t == 1) {
                ans = sum(l, r);
            } else {
                ans = sortedSum(l, r);
            }
            out.append(String.format("%d\n", ans));
        }
    }

    long sortedSum(int l, int r) {
        if (l == 0) {
            return sortedSums[r];
        } else {
            return sortedSums[r] - sortedSums[l - 1];
        }
    }

    long sum(int l, int r) {
        if (l == 0) {
            return sums[r];
        } else {
            return sums[r] - sums[l - 1];
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}