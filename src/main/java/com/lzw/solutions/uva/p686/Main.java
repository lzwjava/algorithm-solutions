package com.lzw.solutions.uva.p686;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    void solve() {
        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= 32768; i++) {
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
            int n = sc.nextInt();
            if (n == 0) {
                break;
            }
            int pairs = 0;
            for (int i = 0; i < primes.size(); i++) {
                int pi = primes.get(i);
                if (pi > n / 2) {
                    break;
                }
                int pj = n - pi;
                int index = Collections.binarySearch(primes, pj);
                if (index >= 0) {
                    pairs++;
                }
            }
            System.out.println(pairs);
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {

        new Main().solve();
    }
}
