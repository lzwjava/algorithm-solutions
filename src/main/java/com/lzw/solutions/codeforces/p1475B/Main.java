package com.lzw.solutions.codeforces.p1475B;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            int n = in.nextInt();
            int c = 0;
            while (n >= 2020) {
                n -= 2020;
                c++;
            }
            boolean ok = false;
            if (n == 0) {
                ok = true;
            } else {
                if (n <= c) {
                    ok = true;
                }
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
