package com.lzw.solutions.uva.p10298;

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
        while (true) {
            String s = in.readLine();
            if (s.equals(".")) {
                break;
            }
            int len = s.length();
            int n;
            for (n = len; n >= 1; n--) {
                if (len % n != 0) {
                    continue;
                }
                int subLen = len / n;
                boolean ok = true;
                for (int i = 1; i < n; i++) {
                    for (int j = 0; j < subLen; j++) {
                        char ch1 = s.charAt(j);
                        char ch2 = s.charAt(i * subLen + j);
                        if (ch1 != ch2) {
                            ok = false;
                            break;
                        }
                    }
                }
                if (ok) {
                    out.append(String.format("%d\n", n));
                    break;
                }
            }
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
