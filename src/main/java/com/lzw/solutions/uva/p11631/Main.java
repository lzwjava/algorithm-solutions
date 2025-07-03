package com.lzw.solutions.uva.p11631;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
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
    
    class Edge implements Comparable<Edge> {
        int x, y, z;

        Edge() {
        }

        Edge(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public int compareTo(Main.Edge o) {
            return z - o.z;
        }
    }

    int[] parents;
    int[] rank;    
    
    int find(int x) {
        if (parents[x] == x) {
            return x;
        } else {
            return find(parents[x]);
        }
    }
    
    void union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px == py) {
            return;
        }
        if (rank[px] > rank[py]) {
            parents[py] = px;            
        } else {
            parents[px] = py;
            if (rank[px] == rank[py]) {
                rank[py]++;
            }            
        }
    }
   
    void solve() throws IOException {
        while (true) {
            String line = in.readLine();
            StringTokenizer st = new StringTokenizer(line);
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            if (m == 0 && n == 0) {
                break;
            }
            Edge[] edges = new Edge[n];
            int total = 0;
            for (int i = 0; i < n; i++) {
                line = in.readLine();
                st = new StringTokenizer(line);
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());
                Edge edge = new Edge(x, y, z);
                edges[i] = edge;
                total += z;
            }
        
            // int[][] grid = new int[m][m];
            // for (int i = 0; i < n; i++) {
            //     Triple triple = tripes[i];
            //     grid[triple.x][triple.y] = grid[triple.y][triple.x] = triple.z;
            // }
            
            parents = new int[m];
            rank = new int[m];
            for (int i = 0; i < m; i++) {
                parents[i] = i;
                rank[i] = 0;
            }
            Arrays.sort(edges);
            int meters = 0;
            for (int i = 0; i < n; i++) {
                Edge edge = edges[i];
                int px = find(edge.x);
                int py = find(edge.y);
                if (px == py) {
                    continue;
                } else {
                    union(edge.x, edge.y);
                    meters += edge.z;
                }
                int p = find(0);
                boolean ok = true;
                for (int j = 1; j < m; j++) {
                    int pj = find(j);
                    if (pj != p) {
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    break;
                }
            }

            out.append(String.format("%d\n", total - meters));
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
