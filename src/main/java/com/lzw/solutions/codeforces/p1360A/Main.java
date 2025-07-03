package com.lzw.solutions.codeforces.p1360A;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            int a = in.nextInt();
            int b = in.nextInt();
            if (b > a) {
                int p = a;
                a = b;
                b = p;
            }
            int e1 = Integer.max(a, b * 2);
            int e2 = Integer.max(a * 2, b);
            int e3 = Integer.max(a + b, a);
            int min = Integer.min(e1, e2);
            int m = Integer.min(min, e3);
            System.out.println(m * m);
            t--;
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}
