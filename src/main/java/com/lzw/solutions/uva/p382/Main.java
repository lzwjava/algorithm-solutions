package com.lzw.solutions.uva.p382;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    class Prime {
        int num;
        int count;
    }

    int divisorSum;
    int divisorN;

    void permutation(ArrayList<Prime> primeNums, int i, int n, int cnts[]) {
        if (i == n) {
            int p = 1;
            for (int j = 0; j < n; j++) {
                Prime prime = primeNums.get(j);
                if (cnts[j] > 0) {
                    p *= (int) Math.pow(prime.num, cnts[j]);
                }
            }
            if (divisorN / p != 1) {
                divisorSum += p;
            }
            return;
        }
        Prime prime = primeNums.get(i);
        for (int j = 0; j <= prime.count; j++) {
            cnts[i] = j;
            permutation(primeNums, i + 1, n, cnts);
        }
    }

    void solve() {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= 60000; i++) {
            boolean isPrime = true;
            for (int j = 0; j < primes.size(); j++) {
                if (i % primes.get(j) == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                primes.add(i);
            }
        }
        System.out.println("PERFECTION OUTPUT");
        while (true) {
            int n = sc.nextInt();
            if (n == 0) {
                break;
            }
            ArrayList<Prime> primeNums = new ArrayList<>();
            int p = n;
            for (int i = 0; i < primes.size(); i++) {
                int pi = primes.get(i);
                if (p % pi == 0) {
                    int cnt = 0;
                    while (p % pi == 0) {
                        p /= pi;
                        cnt++;
                    }
                    Prime primeNum = new Prime();
                    primeNum.num = pi;
                    primeNum.count = cnt;
                    primeNums.add(primeNum);
                }
                if (p == 1) {
                    break;
                }
            }
            int cnts[] = new int[primeNums.size()];
            divisorSum = 0;
            divisorN = n;
            permutation(primeNums, 0, primeNums.size(), cnts);
            System.out.print(String.format("%5d  ", divisorN));
            if (divisorSum == n) {
                System.out.println("PERFECT");
            } else if (divisorSum < n) {
                System.out.println("DEFICIENT");
            } else {
                System.out.println("ABUNDANT");
            }
        }
        System.out.println("END OF OUTPUT");
        sc.close();
    }

    public static void main(String[] args) throws Exception {

        new Main().solve();
    }
}
