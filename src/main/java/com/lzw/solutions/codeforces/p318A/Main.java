package com.lzw.solutions.codeforces.p318A;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        long n = in.nextLong();
        long k = in.nextLong();
        long ans;
        if (n % 2 == 0) {
            long half = n / 2;
            if (k <= half) {
                ans = 2 * k - 1;
            } else {
                long v = k - half;
                ans = 2 * v;
            }
        } else {
            long half = (n + 1) / 2;
            if (k <= half) {
                ans = 2 * k - 1;
            } else {
                long v = k - half;
                ans = 2 * v;
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
