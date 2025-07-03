package com.algorithm.solutions.codeforces.p1358A;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            int n = in.nextInt();
            int m = in.nextInt();
            int a = n * m;
            int ans;
            if (a % 2 == 0) {
                ans = a / 2;
            } else {
                ans = (a - 1) / 2 + 1;
            }
            System.out.println(ans);
            t--;
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
