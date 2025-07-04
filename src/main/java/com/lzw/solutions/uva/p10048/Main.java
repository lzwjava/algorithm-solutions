package com.lzw.solutions.uva.p10048;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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

    class Edge implements Comparable<Edge> {
        int c1, c2, d;

        Edge() {}

        Edge(int c1, int c2, int d) {
            this.c1 = c1;
            this.c2 = c2;
            this.d = d;
        }

        @Override
        public int compareTo(Main.Edge o) {
            return d - o.d;
        }
    }

    int getGroup(int[] groups, int x) {
        if (groups[x] == x) {
            return x;
        } else {
            return getGroup(groups, groups[x]);
        }
    }

    void solve() throws IOException {
        int caseNum = 1;
        while (true) {
            String line = in.readLine();
            StringTokenizer st = new StringTokenizer(line);
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            if (c == 0 && s == 0 && q == 0) {
                break;
            }
            // int[][] graph = new int[c][c];
            ArrayList<Edge> edges = new ArrayList<>();
            for (int i = 0; i < s; i++) {
                st = new StringTokenizer(in.readLine());
                int c1 = Integer.parseInt(st.nextToken());
                int c2 = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                c1--;
                c2--;
                Edge edge = new Edge(c1, c2, d);
                edges.add(edge);
                // graph[c1][c2] = graph[c2][c1] = d;
            }
            Collections.sort(edges);
            if (caseNum != 1) {
                out.append('\n');
            }
            out.append(String.format("Case #%d\n", caseNum));
            for (int i = 0; i < q; i++) {
                st = new StringTokenizer(in.readLine());
                int c1 = Integer.parseInt(st.nextToken());
                int c2 = Integer.parseInt(st.nextToken());
                c1--;
                c2--;

                int[] groups = new int[c];
                for (int j = 0; j < c; j++) {
                    groups[j] = j;
                }
                boolean found = false;
                for (Edge e : edges) {
                    int g1 = getGroup(groups, e.c1);
                    int g2 = getGroup(groups, e.c2);
                    if (g1 != g2) {
                        groups[g2] = g1;
                        int cg1 = getGroup(groups, c1);
                        int cg2 = getGroup(groups, c2);
                        if (cg1 == cg2) {
                            found = true;
                            out.append(String.format("%d\n", e.d));
                            break;
                        }
                    }
                }
                if (!found) {
                    out.append("no path\n");
                }
            }
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
