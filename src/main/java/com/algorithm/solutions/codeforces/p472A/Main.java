package com.algorithm.solutions.codeforces.p472A;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    int maxn = 1000001;
    boolean[] isPrime;

    void calPrimes() {
        isPrime = new boolean[maxn];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 4; i < maxn; i += 2) {
            isPrime[i] = false;
        }
        int si = (int) Math.sqrt(maxn);
        for (int i = 3; i <= si; i += 2) {
            if (isPrime[i]) {
                for (int j = i * i; j < maxn; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    }

    void solve() {
        calPrimes();
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 4; i <= n / 2; i++) {
            int j = n - i;
            if (!isPrime[i] && !isPrime[j]) {
                System.out.println(String.format("%d %d", i, j));
                break;
            }
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
