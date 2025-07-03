package com.lzw.solutions.uva.p10533;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;

public class Data {

    BufferedReader in;
    PrintWriter out;

    Data() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }
   
    void solve() throws IOException {
        int maxn= 500000;
        out.append(String.format("%d\n", maxn));
        for (int i = 0; i < maxn; i++) {
            int t1=1;
            int t2=999999;
            out.append(String.format("%d %d\n", t1, t2));
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
            outStream = new PrintStream("2.in");
            System.setIn(inStream);
            System.setOut(outStream);
        }

        Data main = new Data();
        main.solve();
        main.close();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
