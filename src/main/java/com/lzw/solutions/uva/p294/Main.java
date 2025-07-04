package com.lzw.solutions.uva.p294;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    ArrayList<Integer> primes;

    Main() {
        primes = new ArrayList<>();
        for (int i = 2; i <= 10000; i++) {
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
    }

    class Divisor {
        int prime;
        int count;
    }

    int calDivisor(int x) {
        ArrayList<Divisor> list = new ArrayList<>();
        for (int i = 0; i < primes.size(); i++) {
            int pi = primes.get(i);
            if (x % pi == 0) {
                int count = 0;
                while (x % pi == 0) {
                    x /= pi;
                    count++;
                }
                Divisor d = new Divisor();
                d.prime = pi;
                d.count = count;
                list.add(d);
            }
            if (x == 1) {
                break;
            }
        }
        int sum = 1;
        for (Divisor d : list) {
            sum *= (d.count + 1);
        }
        return sum;
    }

    void solve() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        while (n > 0) {
            int l = sc.nextInt();
            int u = sc.nextInt();
            int maxDivisor = 0;
            int maxNum = 0;
            for (int i = l; i <= u; i++) {
                int d = calDivisor(i);
                if (d > maxDivisor) {
                    maxNum = i;
                    maxDivisor = d;
                }
            }
            System.out.println(
                    String.format("Between %d and %d, %d has a maximum of %d divisors.", l, u, maxNum, maxDivisor));
            n--;
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
