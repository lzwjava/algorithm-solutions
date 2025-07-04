package com.lzw.solutions.uva.p10986;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main1 {

    BufferedReader in;
    PrintWriter out;
    // int graph[][];

    Main1() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    class Edge {
        int a;
        int b;
        int w;

        Edge() {}

        Edge(int a, int b, int w) {
            this.a = a;
            this.b = b;
            this.w = w;
        }
    }

    void solve() throws IOException {
        int N = Integer.parseInt(in.readLine());
        int caseNum = 1;
        while (N > 0) {
            String str = in.readLine();
            StringTokenizer st = new StringTokenizer(str);
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            // graph = new int[n][n];
            // for (int i = 0; i < n; i++) {
            //     Arrays.fill(graph[i], -1);
            // }
            ArrayList<Edge> edges = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                str = in.readLine();
                st = new StringTokenizer(str);
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                // graph[a][b] = graph[b][a] = w;
                Edge e = new Edge(a, b, w);
                edges.add(e);
            }
            int dist[] = new int[n];
            for (int i = 0; i < n; i++) {
                dist[i] = Integer.MAX_VALUE / 2;
            }
            dist[s] = 0;
            for (int i = 0; i < m; i++) {
                for (Edge e : edges) {
                    if (dist[e.b] > dist[e.a] + e.w) {
                        dist[e.b] = dist[e.a] + e.w;
                    }
                    if (dist[e.a] > dist[e.b] + e.w) {
                        dist[e.a] = dist[e.b] + e.w;
                    }
                }
            }
            if (dist[t] == Integer.MAX_VALUE / 2) {
                out.append(String.format("Case #%d: unreachable\n", caseNum));
            } else {
                out.append(String.format("Case #%d: %d\n", caseNum, dist[t]));
            }
            caseNum++;
            N--;
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

        Main1 main = new Main1();
        main.solve();
        main.close();
    }
}
