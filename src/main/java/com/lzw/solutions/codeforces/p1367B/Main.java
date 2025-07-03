package com.lzw.solutions.codeforces.p1367B;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            int n = in.nextInt();
            int c1 = 0, c2 = 0;
            for (int i = 0; i < n; i++) {
                int v = in.nextInt() % 2;
                if (v != i % 2) {
                    if (i % 2 == 0) {
                        c1++;
                    } else {
                        c2++;
                    }
                }
            }
            if (c1 != c2) {
                System.out.println(-1);
            } else {
                System.out.println(c1);
            }
            t--;
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
