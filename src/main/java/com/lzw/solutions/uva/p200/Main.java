package com.lzw.solutions.uva.p200;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }    

    ArrayList<Integer> finishOrders;

    void dfs(boolean graph[][], int n, boolean vis[], int i) {
        vis[i] = true;
        for (int j = 0; j < n; j++) {
            if (graph[i][j] && !vis[j]) {
                dfs(graph, n, vis, j);
            }
        }
        finishOrders.add(i);
    }
   
    void solve() throws IOException {
        ArrayList<String> strs = new ArrayList<>();
        while (true) {
            String s = in.readLine();
            if (s.equals("#")) {
                break;
            }
            strs.add(s);
        }
        HashSet<Character> chs = new HashSet<>();
        for (String s : strs) {
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                chs.add(ch);
            }
        }
        ArrayList<Character> chList = new ArrayList<>();
        chList.addAll(chs);
        Collections.sort(chList);

        HashMap<Character, Integer> map = new HashMap<>();
        int n = chList.size();                
        for (int i = 0; i < n; i++) {
            map.put(chList.get(i), i);
        }

        boolean[][] graph = new boolean[n][n];
        for (int i = 0; i < strs.size() - 1; i++) {
            String str1 = strs.get(i);
            String str2 = strs.get(i + 1);
            int len1 = str1.length();
            int len2 = str2.length();
            int minLen = len1 < len2 ? len1 : len2;
            for (int j = 0; j < minLen; j++) {
                Character ch1 = str1.charAt(j);
                Character ch2 = str2.charAt(j);
                if (ch1 != ch2) {
                    int index1 = map.get(ch1);
                    int index2 = map.get(ch2);
                    graph[index1][index2] = true;
                    break; // important!
                }
            }
        }
        boolean[] vis = new boolean[n];
        finishOrders = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                dfs(graph, n, vis, i);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = finishOrders.size() - 1; i >= 0; i--) {
            int index = finishOrders.get(i);
            Character ch = chList.get(index);
            sb.append(ch);
        }
        out.append(sb.toString()).append('\n');
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
