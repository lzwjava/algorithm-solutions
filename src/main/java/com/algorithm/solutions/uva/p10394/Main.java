package com.algorithm.solutions.uva.p10394;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Main {

    BufferedReader in;
    PrintWriter out;
    ArrayList<Integer> primes;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }
    
    void calPrimes() {
        primes = new ArrayList<>();
        for (int i = 2; i <= 18409550; i++) {
            if (i > 2 && i % 2 == 0) {
                continue;
            }
            boolean ok = true;
            int si = (int) Math.sqrt(i);
            for (int j = 0; j < primes.size(); j++) {
                int pj = primes.get(j);
                if (pj > si) {
                    break;
                }
                if (i % pj == 0) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                primes.add(i);
            }
        }
    }

    class Twin {
        int a;
        int b;

        Twin() {
        }
        
        Twin(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
   
    void solve() throws IOException {
        calPrimes();
        ArrayList<Twin> twins = new ArrayList<>();
        for (int i = 0; i < primes.size() - 1; i++) {
            int pi = primes.get(i);
            int pj = primes.get(i + 1);
            if (pj == pi + 2) {
                Twin twin = new Twin(pi, pj);
                twins.add(twin);
            }
        }
        while (true) {
            String s = in.readLine();
            if (s == null) {
                break;
            }
            int n = Integer.parseInt(s);
            Twin t = twins.get(n-1);
            out.append(String.format("(%d, %d)\n", t.a, t.b));
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
