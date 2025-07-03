package com.lzw.solutions.codeforces.p1294A;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.nextInt();
            int n = in.nextInt();
            int s = a + b + c + n;
            int d = s / 3;
            if (s % 3 == 0 && d >= a && d >= b && d >= c) {
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
