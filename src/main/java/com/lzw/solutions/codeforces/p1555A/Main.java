package com.lzw.solutions.codeforces.p1555A;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        new Main().solve();
    }

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            t--;
            long n = in.nextLong();
            long time = 0;
            if (n >= 20) {
                long d = n - 20;
                long a;
                if (d % 10 == 0) {
                    a = d / 10;
                } else {
                    a = d / 10 + 1;
                }
                time += 25 * a;
                n -= a * 10;
            }
            if (n > 18) {
                time += 25 * 2;
            } else if (n > 16) {
                time += 25 + 20;
            } else if (n > 14) {
                time += 20 + 20;
            } else if (n > 12) {
                time += 20 + 15;
            } else if (n > 10) {
                time += 15 + 15;
            } else if (n > 8) {
                time += 25;
            } else if (n > 6) {
                time += 20;
            } else {
                time += 15;
            }
            System.out.println(String.format("%d", time));
        }
    }

}
