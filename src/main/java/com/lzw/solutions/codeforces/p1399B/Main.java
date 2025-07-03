package com.lzw.solutions.codeforces.p1399B;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            int n = in.nextInt();
            int[] a = new int[n];
            int ma = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
                ma = Integer.min(ma, a[i]);
            }
            int[] b = new int[n];
            int mb = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                b[i] = in.nextInt();
                mb = Integer.min(mb, b[i]);
            }
            long c = 0;
            for (int i = 0; i < n; i++) {
                a[i] -= ma;
                b[i] -= mb;
                c += Integer.max(a[i], b[i]);
            }
            System.out.println(c);
            t--;
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}
