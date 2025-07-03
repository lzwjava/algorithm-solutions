package com.lzw.solutions.uva.p12569;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.concurrent.ArrayBlockingQueue;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int n, m, s, t;
    int[] os;
    boolean[][] grid;
    int ans;
    int fs, fp;
    boolean[][] vis;

    class Move {
        int a, b;

        Move(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    class State {
        int s;
        int p;
        int dist;

        State(int s, int p, int dist) {
            this.s = s;
            this.p = p;
            this.dist = dist;
        }
    }

    class Parent {
        int s;
        int p;

        Parent(int s, int p) {
            this.s = s;
            this.p = p;
        }
    }

    Parent[][] parents;

    void bfs() {
        Queue<State> queue = new ArrayBlockingQueue<>(10000);
        int p = 0;
        for (int i = 0; i < os.length; i++) {
            p |= (1 << os[i]);
        }
        p |= (1 << s);
        queue.add(new State(s, p, 0));
        ans = -1;
        vis = new boolean[n][1 << (n + 1)];
        vis[s][p] = true;
        parents = new Parent[n][1 << (n + 1)];
        parents[s][p] = null;
        fs = fp = -1;
        while (!queue.isEmpty()) {
            State state = queue.poll();
            for (int i = 0; i < n; i++) {
                if ((state.p & (1 << i)) > 0) {
                    for (int j = 0; j < n; j++) {
                        if (grid[i][j] && (state.p & (1 << j)) == 0) {
                            int ns = state.s;
                            int np = (state.p | (1 << j)) ^ (1 << i);
                            if (ns == i) {
                                ns = j;
                            }
                            if (!vis[ns][np]) {
                                vis[ns][np] = true;
                                parents[ns][np] = new Parent(state.s, state.p);
                                if (ns == t) {
                                    ans = state.dist + 1;
                                    fs = ns;
                                    fp = np;
                                    break;
                                }
                                queue.add(new State(ns, np, state.dist + 1));
                            }
                        }
                    }
                    if (ans != -1) {
                        break;
                    }
                }
            }
            if (ans != -1) {
                break;
            }
        }
    }

    void solve() throws IOException {
        int tt = Integer.parseInt(in.readLine());
        for (int p = 0; p < tt; p++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken()) - 1;
            t = Integer.parseInt(st.nextToken()) - 1;
            os = new int[m];
            st = new StringTokenizer(in.readLine());
            for (int i = 0; i < m; i++) {
                os[i] = Integer.parseInt(st.nextToken()) - 1;
            }
            grid = new boolean[n][n];
            for (int i = 0; i < n - 1; i++) {
                st = new StringTokenizer(in.readLine());
                int u = Integer.parseInt(st.nextToken()) - 1;
                int v = Integer.parseInt(st.nextToken()) - 1;
                grid[u][v] = grid[v][u] = true;
            }
            bfs();
            out.append(String.format("Case %d: %d\n", p + 1, ans));
            if (ans != -1) {
                print(fs, fp);
            }
            out.append('\n');
        }
    }

    void print(int s, int p) {
        Parent parent = parents[s][p];
        if (parents[parent.s][parent.p] != null) {
            print(parent.s, parent.p);
        }
        int fromp = parent.p;
        int top = p;
        int a, b;
        a = fromp ^ (fromp & top);
        b = top ^ (fromp & top);
        int u = 0, k = 0;
        for (int i = 0; i < n; i++) {
            if (((1 << i) & a) > 0) {
                u = i;
            }
            if (((1 << i) & b) > 0) {
                k = i;
            }
        }
        out.append(String.format("%d %d\n", u + 1, k + 1));
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