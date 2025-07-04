package com.lzw.solutions.uva.p160;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    void solve() {
        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= 100; i++) {
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
            HashMap<Integer, Integer> counts = new HashMap<>();
            for (int i = 2; i <= n; i++) {
                int p = i;
                while (p != 1) {
                    for (int j = 0; j < primes.size(); j++) {
                        int pj = primes.get(j);
                        if (p % pj == 0) {
                            Integer count = counts.get(pj);
                            if (count == null) {
                                count = 0;
                            }
                            while (p % pj == 0) {
                                p /= pj;
                                count++;
                            }
                            counts.put(pj, count);
                        }
                        if (p == 1) {
                            break;
                        }
                    }
                }
            }
            System.out.print(String.format("%3d! =", n));
            int nums = 0;
            for (int i = 0; i < primes.size(); i++) {
                int pi = primes.get(i);
                Integer c = counts.get(pi);
                if (c == null) {
                    break;
                } else {
                    if (nums == 15) {
                        System.out.println();
                        System.out.print("      ");
                        nums = 0;
                    }
                    System.out.print(String.format("%3d", c));
                    nums++;
                }
            }
            System.out.println();
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {

        new Main().solve();
    }
}
