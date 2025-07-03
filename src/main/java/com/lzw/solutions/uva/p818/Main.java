package com.lzw.solutions.uva.p818;

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

    int n;
    int maxn = 16;
    boolean[][] g;
    int[] gmask;

    boolean dfs(boolean[] vis, int u, int p, int open) {
        vis[u] = true;
        for (int i = 0; i < n; i++) {
            if (((open >> i) & 1) == 1) {
                continue;
            }
            if (!g[u][i] || i == p) {
                continue;
            }
            if (vis[i] || dfs(vis, i, u, open)) {
                return true;
            }
        }
        return false;
    }

    boolean judge(int open) {
        for (int i = 0; i < n; i++) {
            if (((open >> i) & 1) == 1) {
                continue;
            }
            int t = gmask[i] ^ (gmask[i] & open);
            int degree = Integer.bitCount(t);
            if (degree > 2) {
                return false;
            }
        }
        int op = Integer.bitCount(open);
        int comp = 0;
        boolean[] vis = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (((open >> i) & 1) == 1) {
                continue;
            }
            if (!vis[i]) {
                if (dfs(vis, i, -1, open)) {
                    return false;
                }
                comp++;
            }
        }
        return op >= comp - 1;
    }

    void solve() throws IOException {
        int caseNum = 1;
        while (true) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            n = Integer.parseInt(st.nextToken());
            if (n == 0) {
                break;
            }
            g = new boolean[maxn][maxn];
            gmask = new int[maxn];
            while (st.hasMoreTokens()) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                if (a == -1 && b == -1) {
                    break;
                }
                a--;
                b--;
                g[a][b] = g[b][a] = true;
                gmask[a] |= 1 << b;
                gmask[b] |= 1 << a;
            }
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < 1 << n; i++) {
                int op = Integer.bitCount(i);
                if (op >= min) {
                    continue;
                }
                if (judge(i)) {
                    min = Integer.min(min, op);
                }
            }
            out.append(String.format("Set %d: Minimum links to open is %d\n", caseNum, min));
            caseNum++;
        }
    }

    void close() throws IOException {
        in.close();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }
}
