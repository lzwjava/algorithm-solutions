package com.algorithm.solutions.codeforces.p1399C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

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

    int cal(int[] w, int n, int maxs) {
        boolean[] vis = new boolean[n];
        int fc = 0;
        for (int i = 0; i < n; i++) {
            if (vis[i]) {
                continue;
            }
            for (int j = i + 1; j < n; j++) {
                if (vis[j]) {
                    continue;
                }
                int s = w[i] + w[j];
                if (s == maxs) {
                    fc++;
                    vis[i] = vis[j] = true;
                    break;
                }
            }
        }
        return fc;
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            int n = Integer.parseInt(in.readLine());
            int[] w = new int[n];
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                w[i] = Integer.parseInt(st.nextToken());
            }
            Set<Integer> set = new HashSet<>();
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    int s = w[i] + w[j];
                    set.add(s);
                }
            }

            int maxfc = 0;
            for (int s : set) {
                int fc = cal(w, n, s);
                maxfc = Integer.max(maxfc, fc);
            }
            out.append(String.format("%d\n", maxfc));
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}