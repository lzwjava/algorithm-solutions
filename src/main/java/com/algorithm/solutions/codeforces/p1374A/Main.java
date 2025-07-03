package com.algorithm.solutions.codeforces.p1374A;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            int x = in.nextInt();
            int y = in.nextInt();
            int n = in.nextInt();
            int d = (int) Math.floor(n * 1.0 / x);
            int ans = x * d + y;
            if (ans > n) {
                ans -= x;
            }
            System.out.println(ans);
            t--;
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
