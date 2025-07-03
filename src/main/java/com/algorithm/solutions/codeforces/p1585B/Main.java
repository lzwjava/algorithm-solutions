package com.algorithm.solutions.codeforces.p1585B;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        new Main().solve();
    }

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            t--;
            int n = in.nextInt();
            int[] a = new int[n];
            int mx = 0;
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
                mx = Integer.max(mx, a[i]);
            }
            int p = n - 1;
            int c = 0;
            while (true) {
                int x = a[p];
                if (x == mx) {
                    break;
                }
                int i;
                for (i = p - 1; i >= 0; i--) {
                    if (a[i] > x) {
                        break;
                    }
                }
                c++;
                p = i;
            }
            System.out.println(c);
        }
    }

}