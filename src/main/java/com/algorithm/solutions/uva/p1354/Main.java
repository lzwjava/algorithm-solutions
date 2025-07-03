package com.algorithm.solutions.uva.p1354;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;

public class Main {

    BufferedReader in;
    PrintWriter out;
    int ws[];

    class Node {
        int v;
        Node left;
        Node right;

        Node() {
        }

        Node(int v) {
            this.v = v;
        }
    }

    void makeTree(int nums[]) {
                
    }
    
    void permutation(int nums[], boolean vis[], int i, int n) {
        if (i == n) {       
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

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);        
    }
   
    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            double r = Double.parseDouble(in.readLine());
            int s = Integer.parseInt(in.readLine());
            ws = new int[s];
            for (int i = 0; i < s; i++) {
                ws[i] = Integer.parseInt(in.readLine());
            }
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
