package com.algorithm.solutions.uva.p10199;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    class Edge {
        int a, b;

        Edge(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    int n;
    String[] cities;
    //    Edge[] edges;
    ArrayList<Integer>[] adjNodes;

    int indexOfCity(String city) {
        return Arrays.asList(cities).indexOf(city);
    }

    void solve() throws IOException {
        int caseNum = 1;
        while (true) {
            n = Integer.parseInt(in.readLine());
            if (n == 0) {
                break;
            }
            cities = new String[n];
            for (int i = 0; i < n; i++) {
                cities[i] = in.readLine();
            }
            int r = Integer.parseInt(in.readLine());
            adjNodes = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                adjNodes[i] = new ArrayList<Integer>();
            }
            for (int i = 0; i < r; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                int a = indexOfCity(st.nextToken());
                int b = indexOfCity(st.nextToken());
                adjNodes[a].add(b);
                adjNodes[b].add(a);
            }

            boolean[] vis = new boolean[n];
            int total = 0;
            for (int i = 0; i < n; i++) {
                if (!vis[i]) {
                    total++;
                    dfs(vis, i);
                }
            }

            ArrayList<String> ansList = new ArrayList<String>();

            for (int i = 0; i < n; i++) {
                Arrays.fill(vis, false);
                vis[i] = true;
                int cnt = 0;
                for (int j = 0; j < n; j++) {
                    if (i != j && !vis[j]) {
                        dfs(vis, j);
                        cnt++;
                    }
                }
                if (cnt > total) {
                    ansList.add(cities[i]);
                }
                vis[i] = false;
            }

            Collections.sort(ansList);
            if (caseNum != 1) {
                out.append('\n');
            }
            out.append(String.format("City map #%d: %d camera(s) found\n", caseNum, ansList.size()));
            for (String s : ansList) {
                out.append(String.format("%s\n", s));
            }
            caseNum++;
        }
    }

    void dfs(boolean[] vis, int u) {
        vis[u] = true;
        for (int v : adjNodes[u]) {
            if (!vis[v]) {
                dfs(vis, v);
            }
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
            inStream = new FileInputStream("2.in");
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
