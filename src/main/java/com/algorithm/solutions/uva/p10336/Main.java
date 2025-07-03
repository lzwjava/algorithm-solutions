package com.algorithm.solutions.uva.p10336;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int[] dx = { -1, 0, 1, 0 };    
    int[] dy = { 0, 1, 0, -1 };    
    
    void dfs(char[][] grid, boolean[][] vis, int h, int w, int i, int j) {
        vis[i][j] = true;
        char ch = grid[i][j];
        for (int d = 0; d < 4; d++) {
            int ni = i + dx[d];
            int nj = j + dy[d];
            if (ni >= 0 && ni < h && nj >= 0 && nj < w && grid[ni][nj] == ch && !vis[ni][nj]) {
                dfs(grid, vis, h, w, ni, nj);
            }
        }
    }

    class Ans implements Comparable<Ans> {
        char ch;
        int count;

        Ans(char ch, int count) {
            this.ch = ch;
            this.count = count;
        }

        @Override
        public int compareTo(Main.Ans o) {
            if (count != o.count) {
                return Integer.compare(o.count, count);
            }
            return Character.compare(ch, o.ch);
        }
    }
   
    void solve() throws IOException {
        int n = Integer.parseInt(in.readLine());
        for (int i = 0; i < n; i++) {
            String line = in.readLine();
            StringTokenizer st = new StringTokenizer(line);
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            char[][] grid = new char[h][w];
            for (int j = 0; j < h; j++) {
                line = in.readLine();
                for (int k = 0; k < w; k++) {
                    grid[j][k] = line.charAt(k);
                }
            }
            boolean[][] vis = new boolean[h][w];
            HashMap<Character, Integer> map = new HashMap<>();            
            for (int j = 0; j < h; j++) {
                for (int k = 0; k < w; k++) {
                    if (!vis[j][k]) {
                        char ch = grid[j][k];
                        Integer count = map.get(ch);
                        if (count == null) {
                            count = 0;
                        }
                        dfs(grid, vis, h, w, j, k);
                        count++;
                        map.put(ch, count);
                    }
                }
            }
            ArrayList<Ans> list = new ArrayList<>();
            for (Character ch : map.keySet()) {
                Integer count = map.get(ch);
                list.add(new Ans(ch, count));
            }
            Collections.sort(list);
            out.append(String.format("World #%d\n", i + 1));
            for (Ans ans : list) {
                out.append(String.format("%c: %d\n", ans.ch, ans.count));
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
