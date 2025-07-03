package com.lzw.solutions.codeforces.p1311A;

import java.util.Scanner;

public class Main {

    int dp(int a, int b) {
        if (a == b) {
            return 0;
        }
        if (a < b) {
            int d = b - a;
            if (d % 2 == 1) {
                return 1;
            } else {
                return 2;
            }
        } else {
            int d = a - b;
            if (d % 2 == 0) {
                return 1;
            } else {
                return 2;
            }
        }
    }

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            int a = in.nextInt();
            int b = in.nextInt();
            int ans = dp(a, b);
            System.out.println(ans);
            t--;
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}
