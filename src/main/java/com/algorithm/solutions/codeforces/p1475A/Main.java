package com.algorithm.solutions.codeforces.p1475A;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            long n = in.nextLong();
            boolean odd;
            if (n % 2 == 1) {
                odd = true;
            } else {
                while (n % 2 == 0) {
                    n /= 2;
                }
                if (n == 1) {
                    odd = false;
                } else {
                    odd = true;
                }
            }
            if (odd) {
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
