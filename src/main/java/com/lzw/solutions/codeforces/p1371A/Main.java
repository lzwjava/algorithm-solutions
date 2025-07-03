package com.lzw.solutions.codeforces.p1371A;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            int n = in.nextInt();
            int ans = (int) Math.ceil(n * 1.0 / 2);
            System.out.println(ans);
            t--;
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
