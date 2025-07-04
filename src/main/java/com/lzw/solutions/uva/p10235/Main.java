package com.lzw.solutions.uva.p10235;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    void solve() {
        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= 1000005; i++) {
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

        Scanner sc = new Scanner(System.in);
        while (true) {
            int n = 0;
            try {
                n = sc.nextInt();
            } catch (NoSuchElementException e) {
                break;
            }
            String s = String.format("%d", n);
            String rs = new StringBuilder(s).reverse().toString();
            int rn = Integer.parseInt(rs);

            boolean reversePrime = Collections.binarySearch(primes, rn) >= 0;
            boolean prime = Collections.binarySearch(primes, n) >= 0;
            boolean emirp = prime && reversePrime && rn != n;
            System.out.print(n);
            if (!prime) {
                System.out.println(" is not prime.");
            } else if (!emirp) {
                System.out.println(" is prime.");
            } else {
                System.out.println(" is emirp.");
            }
        }
        sc.close();
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
    }
}
