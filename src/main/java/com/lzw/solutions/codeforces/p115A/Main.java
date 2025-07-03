package com.lzw.solutions.codeforces.p115A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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

    List<Integer>[] adjNodes;
    int max = 0;

    void dfs(int x, boolean[] vis, int dist) {
        if (dist > max) {
            max = dist;
        }
        vis[x] = true;
        for (int y : adjNodes[x]) {
            if (!vis[y]) {
                dfs(y, vis, dist + 1);
            }
        }
    }

    void solve() throws IOException {
        int n = Integer.parseInt(in.readLine());
        int[] p = new int[n];
        adjNodes = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjNodes[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            int v = Integer.parseInt(in.readLine());
            if (v != -1) {
                v--;
            }
            p[i] = v;
            if (v != -1) {
                adjNodes[v].add(i);
            }
        }
        max = 0;
        boolean[] vis = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (p[i] == -1) {
                dfs(i, vis, 0);
            }
        }
        out.append(String.format("%d\n", max + 1));
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }
}
