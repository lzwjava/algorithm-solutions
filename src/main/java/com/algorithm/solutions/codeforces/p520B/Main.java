package com.algorithm.solutions.codeforces.p520B;

import java.util.Scanner;

public class Main {

    int dp(int n, int m) {
        if (n >= m) {
            return n - m;
        }
        int ans;
        if (m % 2 == 0) {
            ans = dp(n, m / 2) + 1;
        } else {
            ans = dp(n, m + 1) + 1;
        }
        return ans;
    }

    void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int ans = dp(n, m);
        System.out.println(ans);
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
