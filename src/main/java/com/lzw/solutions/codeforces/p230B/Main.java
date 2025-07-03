package com.lzw.solutions.codeforces.p230B;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    ArrayList<Integer> list;

    boolean isPrime(int x) {
        if (x <= 1) {
            return false;
        }
        int sx = (int) Math.sqrt(x);
        for (Integer p : list) {
            if (p > sx) {
                break;
            }
            if (x % p == 0) {
                return false;
            }
        }
        return true;
    }

    void calPrimes() {
        int maxn = 1000001;
        boolean[] primes = new boolean[maxn];
        Arrays.fill(primes, true);
        primes[0] = primes[1] = false;
        for (int i = 4; i < maxn; i += 2) {
            primes[i] = false;
        }
        int sm = (int) Math.sqrt(maxn);
        for (int i = 3; i <= sm; i += 2) {
            if (primes[i]) {
                for (int j = i * i; j < maxn; j += i) {
                    primes[j] = false;
                }
            }
        }
        list = new ArrayList<>();
        for (int i = 0; i < maxn; i++) {
            if (primes[i]) {
                list.add(i);
            }
        }
    }


    void solve() {
        calPrimes();
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long[] xs = new long[n];
        for (int i = 0; i < n; i++) {
            xs[i] = in.nextLong();
        }
        for (int i = 0; i < n; i++) {
            int sx = (int) Math.sqrt(xs[i]);
            if ((long) sx * sx == xs[i] && isPrime(sx)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
