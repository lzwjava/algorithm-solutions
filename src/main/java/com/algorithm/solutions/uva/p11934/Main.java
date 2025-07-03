package com.algorithm.solutions.uva.p11934;

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
        while (true) {
            int a, b, c, d, L;
            String line = in.readLine();
            StringTokenizer st = new StringTokenizer(line);
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            if (a == 0 && b == 0 && c == 0 && d == 0 && L == 0) {
                break;
            }
            int count = 0;
            for (int x = 0; x <= L; x++) {
                int f = a * x * x + b * x + c;
                if (f % d == 0) {
                    count++;
                }
            }
            out.append(String.format("%d\n", count));
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
