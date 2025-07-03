package com.lzw.solutions.uva.p10369;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    class Pos {
        int x, y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    class Edge implements Comparable<Edge> {
        int u, v;
        double d;

        Edge(int u, int v, double d) {
            this.u = u;
            this.v = v;
            this.d = d;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(d, o.d);
        }
    }

    int[] parent;
    int[] rank;

    int find(int x) {
        if (x == parent[x]) {
            return x;
        } else {
            return find(parent[x]);
        }
    }

    void union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px == py) {
            return;
        }
        if (rank[px] < rank[py]) {
            parent[px] = py;
        } else {
            parent[py] = px;
            if (rank[px] == rank[py]) {
                rank[px]++;
            }
        }
    }

    void solve() throws IOException {
        int n = Integer.parseInt(in.readLine());
        while (n > 0) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int s = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            Pos[] ps = new Pos[p];
            for (int i = 0; i < p; i++) {
                st = new StringTokenizer(in.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                ps[i] = new Pos(x, y);
            }
            ArrayList<Edge> edges = new ArrayList<Edge>();
            for (int i = 0; i < p - 1; i++) {
                for (int j = i + 1; j < p; j++) {
                    Pos pi = ps[i];
                    Pos pj = ps[j];
                    double d = Math.hypot(pi.x - pj.x, pi.y - pj.y);
                    edges.add(new Edge(i, j, d));
                }
            }
            Collections.sort(edges);
            parent = new int[p];
            rank = new int[p];
            for (int i = 0; i < p; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
            double ans = 0;
            int idx = 0;
            while (idx < edges.size()) {
                HashSet<Integer> set = new HashSet<Integer>();
                for (int i = 0; i < p; i++) {
                    int pi = find(i);
                    set.add(pi);
                }
                if (set.size() <= s) {
                    break;
                }
                Edge e = edges.get(idx);
                union(e.u, e.v);
                ans = e.d;
                idx++;
            }
            out.append(String.format("%.2f\n", ans));
            n--;
        }
    }

    void close() throws IOException {
        if (in != null) {
            in.close();
        }
        if (out != null) {
            out.flush();
            out.close();
        }
    }

    public static void main(String[] args) throws Exception {
        FileInputStream inStream = null;
        PrintStream outStream = null;
        boolean isLocal = System.getenv("LOCAL_JUDGE") != null;
        if (isLocal) {
            inStream = new FileInputStream("1.in");
            // outStream = new PrintStream("1.out");
            System.setIn(inStream);
            // System.setOut(outStream);
        }

        Main main = new Main();
        main.solve();
        main.close();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
