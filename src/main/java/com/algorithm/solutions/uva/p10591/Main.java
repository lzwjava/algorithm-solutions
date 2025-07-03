package com.algorithm.solutions.uva.p10591;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);        
    }
   
    void solve() throws IOException {
        int caseNum = 1;
        int t = Integer.parseInt(in.readLine());
        while (t>0) {
            int n = Integer.parseInt(in.readLine());
            int x = n;
            HashSet<Integer> nums = new HashSet<>();
            nums.add(x);
            boolean found = false;
            for (;;) {
                String a = String.format("%d", x);
                int sum = 0;
                for (int i = 0; i < a.length(); i++) {
                    int num = a.charAt(i) - '0';
                    sum += num * num;
                }
                x = sum;
                if (x == 1) {
                    found = true;
                    break;
                } else {
                    if (nums.contains(x)) {
                        break;
                    }
                    nums.add(x);
                }               
            }
            if (found) {
                out.append(String.format("Case #%d: %d is a Happy number.\n", caseNum, n));                
            } else {
                out.append(String.format("Case #%d: %d is an Unhappy number.\n", caseNum, n));                                
            }
            caseNum++;            
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
