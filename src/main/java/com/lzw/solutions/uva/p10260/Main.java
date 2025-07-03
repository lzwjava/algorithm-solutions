package com.lzw.solutions.uva.p10260;

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
    
    int charToNum(char ch) {
        String strs[] = new String[] {
                "AEIOUHWY",            
                "BFPV", 
                "CGJKQSXZ",
                "DT",
                "L",
                "MN",
                "R"
        };                
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].indexOf(ch) >= 0) {
                return i;
            }
        }
        return -1;
    }
   
    void solve() throws IOException {
        while (true) {
            String s = in.readLine();
            if (s == null) {
                break;
            }
            StringBuilder sb = new StringBuilder();
            int lastNum = -1;
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                int num = charToNum(ch);
                if (num != 0) {
                    if (lastNum != num) {
                        sb.append(String.valueOf(num));
                    }
                }
                lastNum = num;
            }
            out.append(sb.toString()).append("\n");
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
