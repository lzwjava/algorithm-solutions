package com.lzw.solutions.codeforces.p327A;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];
        int[] s = new int[n];
        int p = 0;
        int k = 0;
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            p += a[i];
            s[i] = p;
            if (a[i] == 1) {
                k++;
            }
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int c;
                if (i == 0) {
                    c = 0;
                } else {
                    c = s[i - 1];
                }
                int one = s[j] - c;
                int len = j - i + 1;
                int zero = len - one;
                int m = zero - one;
                if (m > max) {
                    max = m;
                }
            }
        }
        System.out.println(k + max);
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
