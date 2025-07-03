package com.lzw.solutions.uva.p12157;

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
    
    int mile(int seconds) {
        int a = seconds / 30;
        return (a + 1) * 10;
    }
    
    int juice(int seconds) {
        int a = seconds / 60;
        return (a + 1) * 15;
    }
   
    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        for (int k = 0; k < t; k++) {
            int n = Integer.parseInt(in.readLine());
            int[] nums = new int[n];
            String s = in.readLine();
            StringTokenizer st = new StringTokenizer(s);
            for (int i = 0; i < n; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }
            int m = 0, j = 0;
            for (int i = 0; i < n; i++) {
                m += mile(nums[i]);
                j += juice(nums[i]);
            }
            out.append(String.format("Case %d: ", k + 1));
            if (m == j) {
                out.append(String.format("Mile Juice %d", m));
            } else if (m < j) {
                out.append(String.format("Mile %d", m));                
            } else {
                out.append(String.format("Juice %d", j));
            }
            out.append('\n');
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
