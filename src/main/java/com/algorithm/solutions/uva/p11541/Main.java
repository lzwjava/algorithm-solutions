package com.algorithm.solutions.uva.p11541;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }
    
    void build(String s, StringBuilder sb, int lastCharPos, int cur) {
        char ch = s.charAt(lastCharPos);
        int v = Integer.parseInt(s.substring(lastCharPos + 1, cur));
        for (int j = 0; j < v; j++) {
            sb.append(ch);
        }
    }
   
    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        for (int u = 0; u < t; u++) {
            String s = in.readLine();
            int lastCharPos = -1;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                if (Character.isAlphabetic(s.charAt(i))) {
                    if (lastCharPos != -1) {
                        build(s, sb, lastCharPos, i);
                    }
                    lastCharPos = i;
                }
            }
            if (lastCharPos != -1) {
                build(s, sb, lastCharPos, s.length());
            }
            out.append(String.format("Case %d: %s\n", u + 1, sb.toString()));
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
