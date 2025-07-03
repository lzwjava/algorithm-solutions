package com.lzw.solutions.uva.p10000;

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
    
    void dfs(int s, int dist, boolean[] vis, int[] d) {
        if (dist > maxDist || (dist == maxDist && s < maxEnd)) {
            maxDist = dist;
            maxEnd = s;
        }
        for (int i = 0; i < n; i++) {
            if (!vis[i] && grid[s][i] && dist + 1 > d[i]) {                
                vis[i] = true;
                d[i] = dist + 1;
                dfs(i, dist + 1, vis, d);
                vis[i] = false;
            }
        }
    }
    
    boolean[][] grid;
    int n;
    int maxDist;
    int maxEnd;
   
    void solve() throws IOException {
        int caseNum = 1;
        while (true) {
            n = Integer.parseInt(in.readLine().trim());
            if (n == 0) {
                break;
            }
            int s = Integer.parseInt(in.readLine().trim()) - 1;
            
            grid = new boolean[n][n];
            while (true) {
                String line = in.readLine();
                StringTokenizer st = new StringTokenizer(line);
                int p = Integer.parseInt(st.nextToken());
                int q = Integer.parseInt(st.nextToken());
                if (p == 0 && q == 0) {
                    break;
                }
                grid[p - 1][q - 1] = true;
            }

            boolean[] vis = new boolean[n];
            vis[s] = true;
            maxDist = 0;
            maxEnd = 0;
            int[] d = new int[n];
            Arrays.fill(d, -1);
            d[s] = 0;
            dfs(s, 0, vis, d);

            out.append(String.format("Case %d: The longest path from %d has length %d, finishing at %d.\n",
                    caseNum, s + 1, maxDist, maxEnd + 1));                    
            out.append('\n');
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
