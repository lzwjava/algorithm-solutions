package com.lzw.solutions.codeforces.p1335A;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            int n = in.nextInt();
            int ans;
            if (n % 2 == 0) {
                ans = n / 2 - 1;
            } else {
                ans = n / 2;
            }
            System.out.println(ans);
            t--;
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}
