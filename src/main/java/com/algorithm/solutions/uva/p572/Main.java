package com.algorithm.solutions.uva.p572;

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

    int dx[] = new int[] { -1, -1, -1, 0,  0, 1, 1, 1 };
    int dy[] = new int[] { -1, 0, 1, -1, 1, -1, 0, 1 };

    char grid[][];
    boolean vis[][];
    int m;
    int n;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }
    
    void dfs(int x, int y) {
        for (int d = 0; d < dx.length; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                if (grid[nx][ny] == '@' && !vis[nx][ny]) {
                    vis[nx][ny] = true;
                    dfs(nx, ny);
                }
            }
        }
    }
   
    void solve() throws IOException {
        while (true) {
            String line = in.readLine();
            StringTokenizer st = new StringTokenizer(line);
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            if (m == 0 && n == 0) {
                break;
            }
            grid = new char[m][n];
            for (int i = 0; i < m; i++) {
                String s = in.readLine();
                assert (s.length() == n);
                for (int j = 0; j < n; j++) {
                    grid[i][j] = s.charAt(j);
                }
            }
            vis = new boolean[m][n];
            int count = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {                
                    if (!vis[i][j] && grid[i][j] == '@') {
                        dfs(i, j);
                        count++;
                    }
                }
            }
            System.out.println(count);
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
