package com.lzw.solutions.uva.p10608;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;
    boolean graph[][];
    int n;
    boolean vis[];
    int count;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }
    
    void dfs(int i) {
        for (int j = 0; j < n; j++) {
            if (graph[i][j] && !vis[j]) {
                count++;                
                vis[j] = true;
                dfs(j);
            }
        }
    }
   
    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            String s = in.readLine();
            StringTokenizer st = new StringTokenizer(s);
            n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            graph = new boolean[n][n];
            vis = new boolean[n];
            for (int i = 0; i < m; i++) {
                s = in.readLine();
                st = new StringTokenizer(s);
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph[a - 1][b - 1] = graph[b - 1][a - 1] = true;
            }
            int max = 0;            
            for (int i = 0; i < n; i++) {
                if (!vis[i]) {
                    vis[i] = true;
                    count = 1;
                    dfs(i);
                    if (count > max) {
                        max = count;
                    }
                }
            }
            out.append(String.format("%d\n", max));
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
