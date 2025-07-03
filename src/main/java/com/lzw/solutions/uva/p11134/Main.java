package com.lzw.solutions.uva.p11134;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int n;

    class Edge implements Comparable<Edge> {
        int l, r, id;

        Edge(int l, int r) {
            this.l = l;
            this.r = r;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(r, o.r);
        }
    }

    Edge[] xs, ys;

    boolean select(Edge[] es, int[] ids) {
        Arrays.sort(es);
        boolean[] vis = new boolean[n];
        for (int i = 0; i < n; i++) {
            Edge e = es[i];
            boolean ok = false;
            for (int j = e.l; j <= e.r; j++) {
                if (!vis[j]) {
                    vis[j] = true;
                    ids[e.id] = j;
                    ok = true;
                    break;
                }
            }
            if (!ok) {
                return false;
            }
        }
        return true;
    }

    void solve() throws IOException {
        while (true) {
            n = Integer.parseInt(in.readLine());
            if (n == 0) {
                break;
            }
            xs = new Edge[n];
            ys = new Edge[n];

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                int xl = Integer.parseInt(st.nextToken()) - 1;
                int yl = Integer.parseInt(st.nextToken()) - 1;
                int xr = Integer.parseInt(st.nextToken()) - 1;
                int yr = Integer.parseInt(st.nextToken()) - 1;
                xs[i] = new Edge(xl, xr);
                ys[i] = new Edge(yl, yr);
                xs[i].id = i;
                ys[i].id = i;
            }

            Arrays.sort(xs);
            Arrays.sort(ys);

            int[] idx = new int[n];
            int[] idy = new int[n];
            if (select(xs, idx) && select(ys, idy)) {
                for (int i = 0; i < n; i++) {
                    out.append(String.format("%d %d\n", idx[i] + 1, idy[i] + 1));
                }
            } else {
                out.append("IMPOSSIBLE\n");
            }
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