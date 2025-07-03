package com.algorithm.solutions.codeforces.p9A;

import java.util.Scanner;

public class Main {

    int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    void solve() {
        Scanner in = new Scanner(System.in);
        int y = in.nextInt();
        int w = in.nextInt();
        int max = Integer.max(y, w);
        int a = 6 - max + 1;
        int b = 6;
        int gcd = gcd(a, b);
        a = a / gcd;
        b = b / gcd;
        System.out.println(String.format("%d/%d", a, b));
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
