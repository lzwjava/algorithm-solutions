package com.lzw.solutions.uva.p10099;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main1 {

    BufferedReader in;
    PrintWriter out;

    Main1() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    class Edge implements Comparable<Edge> {
        int c1;
        int c2;
        int p;

        Edge() {}

        Edge(int c1, int c2, int p) {
            this.c1 = c1;
            this.c2 = c2;
            this.p = p;
        }

        @Override
        public int compareTo(Main1.Edge o) {
            return o.p - p;
        }
    }

    int getGroup(int[] groups, int x) {
        if (groups[x] == x) {
            return x;
        } else {
            return getGroup(groups, groups[x]);
        }
    }

    String readLine() throws IOException {
        while (true) {
            String line = in.readLine();
            line = line.trim();
            if (!line.isEmpty()) {
                return line;
            }
        }
    }

    void solve() throws IOException {
        int caseNum = 1;
        while (true) {
            StringTokenizer st = new StringTokenizer(readLine());
            int n = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            if (n == 0 && r == 0) {
                break;
            }
            ArrayList<Edge> edges = new ArrayList<>();
            for (int i = 0; i < r; i++) {
                st = new StringTokenizer(readLine());
                Edge e = new Edge();
                e.c1 = Integer.parseInt(st.nextToken());
                e.c2 = Integer.parseInt(st.nextToken());
                e.p = Integer.parseInt(st.nextToken());
                edges.add(e);
            }
            st = new StringTokenizer(readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            int[] groups = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                groups[i] = i;
            }

            Collections.sort(edges);

            out.append(String.format("Scenario #%d\n", caseNum));

            for (Edge e : edges) {
                int g1 = getGroup(groups, e.c1);
                int g2 = getGroup(groups, e.c2);
                if (g1 != g2) {
                    groups[g2] = g1;
                    if (getGroup(groups, s) == getGroup(groups, d)) {
                        double x = t * 1.0 / (e.p - 1);
                        int trip = (int) Math.ceil(x);
                        out.append(String.format("Minimum Number of Trips = %d\n", trip));
                        out.append('\n');
                        break;
                    }
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
        FileInputStream inStream = null;
        PrintStream outStream = null;
        boolean isLocal = System.getProperty("os.name").equals("Mac OS X");
        if (isLocal) {
            inStream = new FileInputStream("1.in");
            // outStream = new PrintStream("1.out");
            System.setIn(inStream);
            // System.setOut(outStream);
        }

        Main1 main = new Main1();
        main.solve();
        main.close();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
