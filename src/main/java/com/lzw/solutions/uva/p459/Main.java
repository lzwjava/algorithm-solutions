package com.lzw.solutions.uva.p459;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    BufferedReader in;
    PrintWriter out;
    boolean graph[][];
    boolean vis[];
    int n;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        in.readLine();
        while (t > 0) {
            String maxNode = in.readLine();
            char maxChar = maxNode.charAt(0);
            int charCount = maxChar - 'A' + 1;
            ArrayList<Character> nodeList = new ArrayList<>();
            for (int i = 0; i < charCount; i++) {
                nodeList.add((char) ('A' + i));
            }
            n = nodeList.size();
            graph = new boolean[n][n];
            while (true) {
                String e = in.readLine();
                if (e == null || e.isEmpty()) {
                    break;
                }
                char n1 = e.charAt(0);
                char n2 = e.charAt(1);
                int p1 = Collections.binarySearch(nodeList, n1);
                int p2 = Collections.binarySearch(nodeList, n2);
                graph[p1][p2] = graph[p2][p1] = true;
            }
            vis = new boolean[n];
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (!vis[i]) {
                    vis[i] = true;
                    dfs(i);
                    count++;
                }
            }
            out.append(String.format("%d\n", count));
            t--;
            if (t != 0) {
                out.append('\n');
            }
        }
    }

    private void dfs(int i) {
        for (int j = 0; j < n; j++) {
            if (graph[i][j] && !vis[j]) {
                vis[j] = true;
                dfs(j);
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

        Main main = new Main();
        main.solve();
        main.close();
    }
}
