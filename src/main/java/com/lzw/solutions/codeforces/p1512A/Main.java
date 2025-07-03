package com.lzw.solutions.codeforces.p1512A;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            int n = in.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
            }
            int i;
            for (i = 0; i < n; i++) {
                int j = (i - 1 + n) % n;
                int k = (i + 1 + n) % n;
                if (a[i] != a[j] && a[i] != a[k]) {
                    break;
                }
            }
            System.out.println(i + 1);
            t--;
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}
