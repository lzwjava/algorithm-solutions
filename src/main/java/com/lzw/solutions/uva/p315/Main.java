package com.lzw.solutions.uva.p315;

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
    boolean grid[][];
    int n;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }
    
    void dfs(int skip, int start, boolean vis[]) {
        vis[start] = true;
        for (int i = 0; i < n; i++) {
            if (i != skip && grid[start][i] && !vis[i]) {
                dfs(skip, i, vis);
            }
        }
    }
   
    void solve() throws IOException {
        while (true) {
            n = Integer.parseInt(in.readLine());
            if (n == 0) {
                break;
            }
            grid = new boolean[n][n];
            while (true) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                int a = Integer.parseInt(st.nextToken());
                if (a == 0) {
                    break;
                }
                while (st.hasMoreTokens()) {
                    int b = Integer.parseInt(st.nextToken());
                    grid[a - 1][b - 1] = grid[b - 1][a - 1] = true;
                }
            }
            int count = 0;
            for (int i = 0; i < n; i++) {
                boolean vis[] = new boolean[n];
                int j = (i + 1) % n;
                dfs(i, j, vis);
                boolean critical = false;
                for (int k = 0; k < n; k++) {
                    if (k != i && !vis[k]) {
                        critical = true;
                        break;
                    }
                }
                if (critical) {
                    count++;
                }
            }
            out.append(String.format("%d\n", count));
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
