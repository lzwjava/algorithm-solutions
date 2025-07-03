package com.algorithm.solutions.uva.p11827;

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
    
    int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
   
    void solve() throws IOException {
        int n = Integer.parseInt(in.readLine());
        while (n > 0) {
            String line = in.readLine();
            StringTokenizer st = new StringTokenizer(line);
            int m = st.countTokens();
            int[] nums = new int[m];            
            for (int i = 0; i < m; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }
            int max = 0;
            for (int i = 0; i < m- 1; i++) {
                for (int j = i + 1; j < m; j++) {
                    int g = gcd(nums[i], nums[j]);
                    if (g > max) {
                        max = g;
                    }
                }
            }
            out.append(String.format("%d\n", max));
            n--;
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
