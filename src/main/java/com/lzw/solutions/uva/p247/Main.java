package com.lzw.solutions.uva.p247;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    class Pair {
        String a, b;

        Pair(String a, String b) {
            this.a = a;
            this.b = b;
        }
    }

    ArrayList<Integer>[] adjNodes;
    int n;
    int m;
    int p;

    void dfs(boolean[] vis, int i, int fs[]) {
        vis[i] = true;
        for (Integer j : adjNodes[i]) {
            if (!vis[j]) {
                dfs(vis, j, fs);
            }
        }
        fs[i] = p++;
    }

    void dfs2(ArrayList<Integer>[] nodes, boolean[] vis, int i, ArrayList<Integer> path) {
        vis[i] = true;
        path.add(i);
        for (Integer j : nodes[i]) {
            if (!vis[j]) {
                dfs2(nodes, vis, j, path);
            }
        }
    }

    void solve() throws IOException {
        int caseNum = 1;
        while (true) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            if (n == 0 && m == 0) {
                break;
            }
            if (caseNum > 1) {
                out.append('\n');
            }
            Pair[] pairs = new Pair[m];
            ArrayList<String> list = new ArrayList<String>();
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(in.readLine());
                String a = st.nextToken();
                String b = st.nextToken();
                pairs[i] = new Pair(a, b);
                if (!list.contains(a)) {
                    list.add(a);
                }
                if (!list.contains(b)) {
                    list.add(b);
                }
            }

            adjNodes = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                adjNodes[i] = new ArrayList<Integer>();
            }
            for (Pair pair : pairs) {
                int ia = list.indexOf(pair.a);
                int ib = list.indexOf(pair.b);
                adjNodes[ia].add(ib);
            }

            boolean[] vis = new boolean[n];
            p = 0;
            int[] fs = new int[n];
            for (int i = 0; i < n; i++) {
                if (!vis[i]) {
                    dfs(vis, i, fs);
                }
            }

            ArrayList<Integer>[] revNodes = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                revNodes[i] = new ArrayList<Integer>();
            }
            for (Pair pair : pairs) {
                int ia = list.indexOf(pair.a);
                int ib = list.indexOf(pair.b);
                revNodes[ib].add(ia);
            }

            int[] orders = new int[n];
            for (int i = 0; i < n; i++) {
                orders[fs[i]] = i;
            }

            out.append(String.format("Calling circles for data set %d:\n", caseNum));
            if (m == 0) {
                caseNum++;
                continue;
            }

            Arrays.fill(vis, false);
            for (int i = n - 1; i >= 0; i--) {
                int v = orders[i];
                if (!vis[v]) {
                    ArrayList<Integer> path = new ArrayList<Integer>();
                    dfs2(revNodes, vis, v, path);
                    for (int u = 0; u < path.size(); u++) {
                        if (u != 0) {
                            out.append(", ");
                        }
                        out.append(list.get(path.get(u)));
                    }
                    out.append('\n');
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
    }
}
