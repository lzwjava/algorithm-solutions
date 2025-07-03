package com.lzw.solutions.uva.p558;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }
    
    class Edge {
        int x;
        int y;
        int t;

        Edge() {
        }
        
        Edge(int x, int y, int t) {
            this.x = x;
            this.y = y;
            this.t = t;
        }
    }
   
    void solve() throws IOException {
        int c = Integer.parseInt(in.readLine());
        while (c > 0) {
            String s = in.readLine();
            StringTokenizer st = new StringTokenizer(s);
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            ArrayList<Edge> edges = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                s = in.readLine();
                st = new StringTokenizer(s);
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());
                edges.add(new Edge(x, y, t));
            }
            int dist[] = new int[n];
            for (int i = 0; i < n; i++) {
                dist[i] = Integer.MAX_VALUE / 2;
            }
            dist[0] = 0;
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < m; j++) {
                    Edge e = edges.get(j);
                    if (dist[e.y] > dist[e.x] + e.t) {
                        dist[e.y] = dist[e.x] + e.t;
                    }
                }
            }
            boolean ok = false;
            for (int j = 0; j < m; j++) {
                Edge e = edges.get(j);
                if (dist[e.y] > dist[e.x] + e.t) {
                    ok = true;
                    break;
                }
            }
            if (ok) {
                out.append("possible\n");                
            } else {
                out.append("not possible\n");
            }         
            c--;
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
