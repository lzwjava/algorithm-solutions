package com.algorithm.solutions.uva.p544;

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

    ArrayList<String> cities;

    int indexOfCity(String name) {
        int index = cities.indexOf(name);
        if (index != -1) {
            return index;
        }
        index = cities.size();
        cities.add(name);
        return index;
    }

    class Edge implements Comparable<Edge> {
        int a, b, w;

        Edge(int a, int b, int w) {
            this.a = a;
            this.b = b;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(o.w, w);
        }
    }

    int[] parent;
    int[] rank;

    int find(int x) {
        if (parent[x] == x) {
            return x;
        } else {
            return find(parent[x]);
        }
    }

    void union(int x, int y) {
        int fx = find(x);
        int fy = find(y);
        if (fx == fy) {
            return;
        }
        if (rank[fx] < rank[fy]) {
            parent[fx] = fy;
        } else {
            parent[fy] = fx;
            if (rank[fx] == rank[fy]) {
                rank[fx]++;
            }
        }
    }

    void solve() throws IOException {
        int caseNum = 1;
        while (true) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            if (n == 0 && r == 0) {
                break;
            }
            cities = new ArrayList<String>();
            ArrayList<Edge> edges = new ArrayList<Edge>();
            for (int i = 0; i < r; i++) {
                st = new StringTokenizer(in.readLine());
                String na = st.nextToken();
                String nb = st.nextToken();
                int w = Integer.parseInt(st.nextToken());
                int ia = indexOfCity(na);
                int ib = indexOfCity(nb);
                edges.add(new Edge(ia, ib, w));
            }
            st = new StringTokenizer(in.readLine());
            String na = st.nextToken();
            String nb = st.nextToken();
            int ta = indexOfCity(na);
            int tb = indexOfCity(nb);

            Collections.sort(edges);
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
            int ans = 0;
            for (int i = 0; i < r; i++) {
                Edge e = edges.get(i);
                union(e.a, e.b);
                int fa = find(ta);
                int fb = find(tb);
                if (fa == fb) {
                    ans = e.w;
                    break;
                }
            }
            out.append(String.format("Scenario #%d\n", caseNum));
            out.append(String.format("%d tons\n", ans));
            out.append('\n');

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
