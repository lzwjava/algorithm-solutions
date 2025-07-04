package com.lzw.solutions.uva.p534;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    class Edge implements Comparable<Edge> {
        int u;
        int v;
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

    int find(int x) {
        if (x == parent[x]) {
            return x;
        } else {
            return find(parent[x]);
        }
    }

    int parent[];
    int rank[];

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
        int caseNum = 1;
        while (true) {
            int n = Integer.parseInt(in.readLine());
            if (n == 0) {
                break;
            }
            Node[] nodes = new Node[n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                nodes[i] = new Node(x, y);
            }
            in.readLine();

            ArrayList<Edge> edges = new ArrayList<Edge>();
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    double d = Math.hypot(nodes[i].x - nodes[j].x, nodes[i].y - nodes[j].y);
                    edges.add(new Edge(i, j, d));
                }
            }

            Collections.sort(edges);
            rank = new int[n];
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
            double ans = 0;
            for (Edge edge : edges) {
                union(edge.u, edge.v);
                int p0 = find(0);
                int p1 = find(1);
                if (p0 == p1) {
                    ans = edge.d;
                    break;
                }
            }

            out.append(String.format("Scenario #%d\n", caseNum));
            //            double d = Math.hypot(nodes[0].x - nodes[1].x, nodes[0].y - nodes[1].y);
            out.append(String.format("Frog Distance = %.3f\n\n", ans));

            caseNum++;
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

        Main main = new Main();
        main.solve();
        main.close();
    }
}
