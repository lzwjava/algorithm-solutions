package com.algorithm.solutions.uva.p583;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

public class Main {

    class Factor {
        int prime;
        int count;

        Factor() {
            
        }
    }
   
    void solve() throws IOException {
        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= 46340; i++) {
            boolean ok = true;
            int si = (int) Math.sqrt(i);
            for (int j = 0; j < primes.size(); j++) {
                int pj = primes.get(j);
                if (pj > si) {
                    break;
                }
                if (i % pj == 0) {
                    ok = false;
                }
            }
            if (ok) {
                primes.add(i);
            }
        }        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String line = br.readLine();
            int n = Integer.parseInt(line);
            if (n == 0) {
                break;
            }
            int no = n;
            boolean minus = false;
            if (n < 0) {
                minus = true;
                n = -n;
            }
            ArrayList<Factor> fs = new ArrayList<>();
            for (int i = 0; i < primes.size(); i++) {
                int pi = primes.get(i);
                if (n % pi == 0) {
                    Factor f = new Factor();
                    f.prime = pi;
                    while (n % pi == 0) {
                        f.count++;
                        n /= pi;
                    }
                    fs.add(f);
                }
                if (n == 1) {
                    break;
                }
            }
            if (fs.size() == 0) {
                Factor f = new Factor();
                f.prime = n;
                f.count = 1;
                fs.add(f);
            }
            if (minus) {
                System.out.print(String.format("%d = %d x", no, -1));
            } else {
                System.out.print(String.format("%d =", no));
            }
            boolean first = true;
            for (int i = 0; i < fs.size(); i++) {
                Factor f = fs.get(i);
                for (int j = 0; j < f.count; j++) {
                    if (first) {
                        first = false;
                    } else {
                        System.out.print(" x");
                    }
                    System.out.print(String.format(" %d", f.prime));
                }
            }
            System.out.println();
        }
        br.close();
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

        new Main().solve();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
