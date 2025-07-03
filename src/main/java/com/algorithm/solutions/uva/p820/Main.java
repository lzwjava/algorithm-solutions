package com.algorithm.solutions.uva.p820;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.concurrent.ArrayBlockingQueue;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }
    
    class Edge {
        int from, to, cap, flow;

        Edge(int from, int to, int cap, int flow) {
            this.from = from;
            this.to = to;
            this.cap = cap;
            this.flow = flow;
        }
    }

    class EdmondsKarp {
        int n, m;
        ArrayList<Edge> edges;
        ArrayList<Integer> G[];
        int[] a;
        int[] p;

        EdmondsKarp(int n) {
            this.n = n;
            G = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                G[i] = new ArrayList<>();
            }
            edges = new ArrayList<>();
            a = new int[n];
            p = new int[n];
        }

        void addEdge(int from, int to, int cap) {
            edges.add(new Edge(from, to, cap, 0));
            edges.add(new Edge(to, from, cap, 0));
            m = edges.size();
            G[from].add(m - 2);
            G[to].add(m - 1);
        }

        int maxFlow(int s, int t) {
            int flow = 0;
            for (;;) {
                Arrays.fill(a, 0);
                ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(n * 2);
                queue.add(s);
                a[s] = Integer.MAX_VALUE;
                while (!queue.isEmpty()) {
                    int x = queue.poll();
                    for (int i = 0; i < G[x].size(); i++) {
                        Edge e = edges.get(G[x].get(i));
                        if (a[e.to] == 0 && e.cap > e.flow) {
                            p[e.to] = G[x].get(i);
                            a[e.to] = Math.min(a[x], e.cap - e.flow);
                            queue.add(e.to);
                        }
                    }
                    if (a[t] > 0) {
                        break;
                    }
                }
                if (a[t] == 0) {
                    break;
                }
                for (int u = t; u != s; u = edges.get(p[u]).from) {
                    edges.get(p[u]).flow += a[t];
                    edges.get(p[u] ^ 1).flow -= a[t];
                }
                flow += a[t];
            }
            return flow;
        }
    }
   
    void solve() throws IOException {
        int caseNum = 1;
        while (true) {
            int n = Integer.parseInt(in.readLine());
            if (n == 0) {
                break;
            }
            String str = in.readLine();
            StringTokenizer st = new StringTokenizer(str);
            int s = Integer.parseInt(st.nextToken()) - 1;            
            int t = Integer.parseInt(st.nextToken()) - 1;            
            int c = Integer.parseInt(st.nextToken());
            EdmondsKarp edmondsKarp = new EdmondsKarp(n);
            for (int i = 0; i < c; i++) {
                st = new StringTokenizer(in.readLine());
                int x, y, b;
                x = Integer.parseInt(st.nextToken());
                y = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                edmondsKarp.addEdge(x - 1, y - 1, b);
            }
            int maxBandwidth = edmondsKarp.maxFlow(s, t);                   
            out.append(String.format("Network %d\n", caseNum));                         
            out.append(String.format("The bandwidth is %d.\n", maxBandwidth));            
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
        boolean isLocal = System.getProperty("os.name").equals("Mac OS X");        
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
