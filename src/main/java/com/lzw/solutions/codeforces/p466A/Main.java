package com.lzw.solutions.codeforces.p466A;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();
        int ans;
        if (b >= a * m) {
            // b/m >=a
            ans = n * a;
        } else {
            int d = n / m;
            int v1 = b * d + (n % m) * a;
            int v2 = (int) Math.ceil(n * 1.0 / m) * b;
            ans = Integer.min(v1, v2);
        }
        System.out.println(ans);
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}
