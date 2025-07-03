package com.algorithm.solutions.uva.p10013;

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
        int n = Integer.parseInt(in.readLine());
        for (int i = 0; i < n; i++) {
            in.readLine();
            int m = Integer.parseInt(in.readLine());
            int ans[] = new int[m];
            for (int j = m - 1; j >= 0; j--) {
                String s = in.readLine();
                StringTokenizer st = new StringTokenizer(s);
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                ans[j] = a + b;
            }
            int carry = 0;
            for (int j = 0; j < m; j++) {
                ans[j] += carry;

                carry = ans[j] / 10;
                ans[j] -= carry * 10;
            }
            StringBuilder sb = new StringBuilder();
            for (int j = m - 1; j >= 0; j--) {
                sb.append(String.valueOf(ans[j]));
            }
            if (i != 0) {
                out.append('\n');
            }            
            out.append(sb.toString()).append('\n');
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
