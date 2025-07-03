package com.lzw.solutions.uva.p10986;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    class Edge implements Comparable<Edge> {
        int a;
        int b;
        int w;

        Edge() {
        }

        Edge(int a, int b, int w) {
            this.a = a;
            this.b = b;
            this.w = w;
        }

        @Override
        public int compareTo(Main.Edge o) {
            return w - o.w;
        }
    }
    
    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
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

            ArrayList<Edge>[] edgeList = new ArrayList[n];            
            for (int i = 0; i < m; i++) {
                str = in.readLine();
                st = new StringTokenizer(str);
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                
                if (edgeList[a] == null) {
                    edgeList[a] = new ArrayList<>();
                }
                edgeList[a].add(new Edge(a, b, w));
                if (edgeList[b] == null) {
                    edgeList[b] = new ArrayList<>();
                }
                edgeList[b].add(new Edge(b, a, w));
            }
            int dist[] = new int[n];
            for (int i = 0; i < n; i++) {
                dist[i] = Integer.MAX_VALUE / 2;
            }
            dist[s] = 0;

            PriorityQueue<Edge> pq = new PriorityQueue<>();
            pq.add(new Edge(s, s, 0));
            while (!pq.isEmpty()) {
                Edge e = pq.poll();
                if (e.b == t) {
                    break;
                }
                if (edgeList[e.b] != null) {
                    for (Edge ne : edgeList[e.b]) {
                        if (dist[ne.b] > dist[ne.a] + ne.w) {
                            dist[ne.b] = dist[ne.a] + ne.w;
                            pq.offer(new Edge(ne.a, ne.b, dist[ne.b]));
                        }
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
        FileInputStream inStream = null;
        PrintStream outStream = null;
        boolean isLocal = System.getProperty("os.name").equals("Mac OS X");        
        if (isLocal) {
            inStream = new FileInputStream("1.in");
            outStream = new PrintStream("1.out");
            System.setIn(inStream);
            System.setOut(outStream);
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
