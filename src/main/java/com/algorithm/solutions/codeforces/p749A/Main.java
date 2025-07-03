package com.algorithm.solutions.codeforces.p749A;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k, c2, c3;
        if (n % 2 == 1) {
            c2 = (n - 3) / 2;
            c3 = 1;
        } else {
            c2 = n / 2;
            c3 = 0;
        }
        k = c2 + c3;
        System.out.println(k);
        boolean first = true;
        for (int i = 0; i < c2; i++) {
            if (!first) {
                System.out.print(' ');
            }
            first = false;
            System.out.print(2);
        }
        if (c3 == 1) {
            if (!first) {
                System.out.print(' ');
            }
            System.out.print(3);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
