package com.algorithm.solutions.uva.p140;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class Main {

    BufferedReader in;
    PrintWriter out;
    boolean grid[][];
    int minBandwidth;
    int minOrdering[];

    class Record {
        char node;
        char neighbours[];

        Record() {
        }
        
        Record(char node, char neighbours[]) {
            this.node = node;
            this.neighbours = neighbours;
        }
    }

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }
    
    int bandwidth(int nums[], int n) {
        int max = 0;
        for (int j = 0; j < n; j++) {
            int bandwidth = 0;
            for (int k = 0; k < n; k++) {
                if (j != k && grid[nums[j]][nums[k]]) {
                    int w = Math.abs(j - k);
                    if (w > bandwidth) {
                        bandwidth = w;
                    }
                }
            }
            if (bandwidth > max) {
                max = bandwidth;
            }
        }
        return max;
    }
    
    void permutation(int nums[], boolean vis[], int i, int n) {
        if (i == n) {
            int max = bandwidth(nums, n);
            if (max < minBandwidth) {
                minBandwidth = max;
                minOrdering = Arrays.copyOf(nums, nums.length);
            }
            return;
        }
        for (int j = 0; j < n; j++) {
            if (!vis[j]) {
                vis[j] = true;
                nums[i] = j;
                permutation(nums, vis, i + 1, n);
                vis[j] = false;
            }
        }
    }
   
    void solve() throws IOException {
        while (true) {
            String line = in.readLine();
            if (line.equals("#")) {
                break;
            }
            String records[] = line.split(";");
            ArrayList<Record> recordList = new ArrayList<>();
            HashSet<Character> set = new HashSet<>();
            for (String record : records) {
                int mid = record.indexOf(":");
                String nodeName = record.substring(0, mid);
                char node = nodeName.charAt(0);
                String neighbourString = record.substring(mid + 1, record.length());
                char neighbours[] = new char[neighbourString.length()];
                set.add(node);
                for (int i = 0; i < neighbours.length; i++) {
                    neighbours[i] = neighbourString.charAt(i);
                    set.add(neighbours[i]);
                }
                Record r = new Record(node, neighbours);
                recordList.add(r);
            }
            ArrayList<Character> nodeList = new ArrayList<>();
            nodeList.addAll(set);
            Collections.sort(nodeList);

            int size = nodeList.size();
            
            HashMap<Character, Integer> map = new HashMap<>();
            HashMap<Integer, Character> rmap = new HashMap<>();
            for (int i = 0; i < nodeList.size(); i++) {
                Character node = nodeList.get(i);
                map.put(node, i);
                rmap.put(i, node);
            }

            grid = new boolean[size][size];
            for (Record r : recordList) {
                int i = map.get(r.node);
                for (char n : r.neighbours) {
                    int j = map.get(n);
                    grid[i][j] = true;
                    grid[j][i] = true;
                }
            }
            minBandwidth = Integer.MAX_VALUE;
            int nums[] = new int[size];
            boolean vis[] = new boolean[size];            
            permutation(nums, vis, 0, size);

            for (int i = 0; i < minOrdering.length; i++) {
                Character ch = rmap.get(minOrdering[i]);
                out.append(ch).append(' ');
            }
            out.append("-> ");
            out.append(String.format("%d\n", minBandwidth));
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
