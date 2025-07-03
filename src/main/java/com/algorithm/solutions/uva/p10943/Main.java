package com.algorithm.solutions.uva.p10943;

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

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }
   
    void solve() throws IOException {
        int maxNum = 201;
        int[][] C = new int[maxNum][maxNum];
        C[0][0] = 1;
        for (int n = 1; n <= 200; n++) {
            C[n][0] = 1;
            for (int k = 1; k <= n; k++) {
                C[n][k] = (C[n - 1][k - 1] + C[n - 1][k]) % 1000000;                
            }
        }
        while(true){
            String s = in.readLine();        
            StringTokenizer st = new StringTokenizer(s);
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            if (n == 0 && k == 0) {
                break;
            }
            int ans = C[n + k - 1][k - 1];
            out.append(String.format("%d\n", ans));
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
