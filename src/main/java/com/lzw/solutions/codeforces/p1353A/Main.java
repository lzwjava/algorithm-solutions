package com.lzw.solutions.codeforces.p1353A;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            int n = in.nextInt();
            int m = in.nextInt();
            int p;
            if (n > 2) {
                p = m * 2;
            } else if (n == 2) {
                p = m;
            } else {
                p = 0;
            }
            System.out.println(p);
            t--;
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}
