package com.lzw.solutions.uva.p11504;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    ArrayList<Integer>[] adjNodes;
    int n, m;
    Stack<Integer> stack;

    void dfs(boolean[] vis, int i) {
        vis[i] = true;
        for (Integer j : adjNodes[i]) {
            if (!vis[j]) {
                dfs(vis, j);
            }
        }
        stack.add(i);
    }

    void dfs2(boolean[] vis, int i) {
        vis[i] = true;
        for (Integer j : adjNodes[i]) {
            if (!vis[j]) {
                dfs2(vis, j);
            }
        }
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine().trim());
        while (t > 0) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            adjNodes = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                adjNodes[i] = new ArrayList<Integer>();
            }
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(in.readLine());
                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;
                adjNodes[a].add(b);
            }
            boolean[] vis = new boolean[n];
            stack = new Stack<Integer>();
            for (int i = 0; i < n; i++) {
                if (!vis[i]) {
                    dfs(vis, i);
                }
            }

            Arrays.fill(vis, false);
            int cnt = 0;
            while (!stack.isEmpty()) {
                Integer v = stack.pop();
                if (!vis[v]) {
                    dfs2(vis, v);
                    cnt++;
                }
            }
            out.append(String.format("%d\n", cnt));
            t--;
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
            outStream = new PrintStream("2.out");
            System.setIn(inStream);
            System.setOut(outStream);
        }

        Main main = new Main();
        main.solve();
        main.close();
    }
}
