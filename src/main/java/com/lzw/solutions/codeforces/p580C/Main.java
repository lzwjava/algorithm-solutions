package com.lzw.solutions.codeforces.p580C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
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

    int[] a;
    int n, m;
    List<Integer>[] adjNodes;
    int total;

    void dfs(boolean[] vis, int x, int u) {
        vis[x] = true;
        if (u <= m) {
            if (x != 0 && adjNodes[x].size() == 1) {
                total++;
            }
        } else {
            return;
        }
        for (int i = 0; i < adjNodes[x].size(); i++) {
            int y = adjNodes[x].get(i);
            if (!vis[y]) {
                int nu;
                if (a[y] == 1) {
                    nu = u + a[y];
                } else {
                    nu = 0;
                }
                dfs(vis, y, nu);
            }
        }
    }

    void solve() throws IOException {
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        a = new int[n];
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        adjNodes = new List[n];
        for (int i = 0; i < n; i++) {
            adjNodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            adjNodes[x].add(y);
            adjNodes[y].add(x);
        }

        boolean[] vis = new boolean[n];
        total = 0;
        dfs(vis, 0, a[0]);
        out.append(String.format("%d\n", total));
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }
}
