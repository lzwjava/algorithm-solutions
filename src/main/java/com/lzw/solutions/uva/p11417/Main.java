package com.lzw.solutions.uva.p11417;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    ArrayList<Integer> primes = new ArrayList<>();

    int GCD(int i, int j) {
        int gcd = 1;
        for (int x = 0; x < primes.size(); x++) {
            int p = primes.get(x);
            if (i % p == 0 && j % p == 0) {
                while (i % p == 0 && j % p == 0) {
                    gcd *= p;
                    i /= p;
                    j /= p;
                }
            }
            if (i == 1 || j == 1) {
                return gcd;
            }
        }
        return gcd;
    }

    void solve() {
        for (int i = 2; i <= 500; i++) {
            boolean ok = true;
            for (int j = 0; j < primes.size(); j++) {
                if (i % primes.get(j) == 0) {
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
            int n = sc.nextInt();
            if (n == 0) {
                break;
            }
            int G = 0;
            for (int i = 1; i < n; i++) {
                for (int j = i + 1; j <= n; j++) {
                    G += GCD(i, j);
                }
            }
            System.out.println(G);
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
