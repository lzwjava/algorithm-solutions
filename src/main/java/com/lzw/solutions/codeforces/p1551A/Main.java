package com.lzw.solutions.codeforces.p1551A;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            int n = in.nextInt();
            int c2 = (int) Math.round(n * 1.0 / 3);
            int c1 = n - 2 * c2;
            System.out.println(String.format("%d %d", c1, c2));
            t--;
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
