package com.algorithm.solutions.uva.p11137;

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
   
    void solve() throws IOException {
        int coinCount = 21;
        int[] coins = new int[coinCount];
        for (int i = 0; i < coinCount; i++) {
            int v = (i+1);
            coins[i] = v * v * v;
        }
        int maxn = 10000;
        long[] ways = new long[maxn];
        ways[0] = 1;
        for (int i = 0; i < coinCount; i++) {
            int coin = coins[i];
            int end = maxn - coin;
            for (int j = 0; j < end; j++) {
                ways[j + coin] += ways[j];
            }
        }
        while (true) {
            String s = in.readLine();
            if (s == null) {
                break;
            }
            int n = Integer.parseInt(s.trim());
            out.append(String.format("%d\n", ways[n]));
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
