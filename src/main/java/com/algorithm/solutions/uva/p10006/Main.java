package com.algorithm.solutions.uva.p10006;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;

public class Main {    

    int cal(int a, int n) {
        int mod = 1;
        for (int i = 0; i < n; i++) {
            mod *= a % n;
            mod %= n;
        }
        return mod % n;
    }
   
    void solve() throws IOException {
        ArrayList<Integer> primes = new ArrayList<>();
        boolean isp[] = new boolean[65000];
        for (int i = 2; i < 65000; i++) {
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
                isp[i] = true;
            }
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        PrintWriter out = new PrintWriter(System.out);
        while (true) {
            String s = br.readLine();
            int n = Integer.parseInt(s);
            if (n == 0) {
                break;
            }
            boolean isPrime = isp[n];
            boolean carmichael = false;
            if (!isPrime) {
                boolean pass = true;
                BigInteger bn = BigInteger.valueOf(n);
                for (int a = 2; a <= n - 1; a++) {
                    BigInteger bi = BigInteger.valueOf(a);
                    BigInteger bmod = bi.modPow(bn, bn);

                    // int mod = cal(a, n);
                    if (bmod.compareTo(bi) != 0) {
                        pass = false;
                        break;
                    }

                    // if (mod != a) {
                    //     pass = false;
                    //     break;                        
                    // }
                }
                if (pass) {
                    carmichael = true;
                }
            }
            if (carmichael) {
                out.append(String.format("The number %d is a Carmichael number.\n", n));
            } else {
                out.append(String.format("%d is normal.\n", n));
            }
        }
        out.flush();
        
        out.close();
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
