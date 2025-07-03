package com.algorithm.solutions.codeforces.p1348A;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            int n = in.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = 1 << (i + 1);
            }
            int s1 = 0;
            for (int i = 0; i < n / 2 - 1; i++) {
                s1 += a[i];
            }
            s1 += a[n - 1];
            int s2 = 0;
            for (int i = n / 2 - 1; i < n - 1; i++) {
                s2 += a[i];
            }
            System.out.println(Math.abs(s1 - s2));
            t--;
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
