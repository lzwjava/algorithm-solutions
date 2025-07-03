package com.lzw.solutions.uva.p10179;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Main1 {

    BufferedReader in;
    PrintWriter out;

    Main1() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }
    
    int gcd(ArrayList<Integer> factors, int a) {
        for (Integer factor : factors) {
            if (a % factor == 0) {
                return factor;
            }
        }
        return 1;
    }

    ArrayList<Integer> primes;    

    void calPrimes() {
        primes = new ArrayList<>();
        for (int i = 2; i < 3162; i++) {
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
    
    class Factor {
        int prime;
        int count;

        Factor(int prime, int count) {
            this.prime = prime;
            this.count = count;
        }
    }

    private ArrayList<Integer> calFactor(int a) {
        ArrayList<Integer> factors = new ArrayList<>();
        int si = (int)Math.sqrt(a);
        for (int i = 0; i < primes.size(); i++) {
            int pi = primes.get(i);
            if (pi > si) {
                break;
            }
            if (a % pi == 0) {
                factors.add(pi);
                while (a % pi == 0) {
                    a /= pi;
                }
            }
        }
        if (a != 1) {
            factors.add(a);
        }
        return factors;
    }    
   
    void solve() throws IOException {
        calPrimes();

        // gcd(240767243, 999999999);
        while (true) {
            int n = Integer.parseInt(in.readLine());
            if (n == 0) {
                break;
            }
            
            ArrayList<Integer> factors = calFactor(n);
            boolean[] reducible = new boolean[n];            
            for (Integer factor : factors) {
                for (int i = factor; i < n; i += factor) {
                    reducible[i] = true;
                }
            }
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (!reducible[i]) {
                    count++;
                }
            }
            count--;
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
            inStream = new FileInputStream("2.in");
            // outStream = new PrintStream("1.out");
            System.setIn(inStream);
            // System.setOut(outStream);
        }

        Main1 main = new Main1();
        main.solve();
        main.close();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
