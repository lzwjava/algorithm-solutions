package com.algorithm.solutions.uva.p793;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main1 {

    BufferedReader in;
    PrintWriter out;
    boolean graph[][];
    int n;
    boolean vis[];

    Main1() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }
    
    boolean dfs(int x, int t) {
        if (x == t) {
            return true;
        }
        vis[x] = true;
        for (int i = 0; i < n; i++) {
            if (!vis[i] && graph[x][i]) {
                if (dfs(i, t)) {
                    return true;
                }
            }
        }
        return false;
    }
   
    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        in.readLine();
        while (t > 0) {
            n = Integer.parseInt(in.readLine());
            graph = new boolean[n][n];
            int s = 0, us = 0;
            while (true) {
                String str = in.readLine();
                if (str == null || str.isEmpty()) {
                    break;
                }
                StringTokenizer st = new StringTokenizer(str);
                String op = st.nextToken();
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                char ch = op.charAt(0);
                if (ch == 'c') {
                    graph[a - 1][b - 1] = graph[b - 1][a - 1] = true;
                } else if (ch == 'q') {
                    vis = new boolean[n];
                    boolean res = dfs(a - 1, b - 1);
                    if (res) {
                        s++;
                    } else {
                        us++;
                    }
                } else {
                    assert (false);
                }                
            }
            out.append(String.format("%d,%d\n", s, us));
            t--;
            if (t != 0) {
                out.append('\n');
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
        boolean isLocal = System.getProperty("os.name").equals("Mac OS X");        
        if (isLocal) {
            inStream = new FileInputStream("1.in");
            outStream = new PrintStream("1.out");
            System.setIn(inStream);
            System.setOut(outStream);
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
