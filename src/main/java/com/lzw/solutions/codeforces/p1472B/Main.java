package com.lzw.solutions.codeforces.p1472B;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            int n = in.nextInt();
            int c1 = 0, c2 = 0;
            for (int i = 0; i < n; i++) {
                int v = in.nextInt();
                if (v == 1) {
                    c1++;
                } else {
                    c2++;
                }
            }
            boolean ok;
            if (c2 % 2 == 0) {
                ok = c1 % 2 == 0;
            } else {
                ok = c1 >= 2 && c1 % 2 == 0;
            }
            if (ok) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
            t--;
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}
