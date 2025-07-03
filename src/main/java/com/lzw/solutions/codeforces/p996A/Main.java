package com.lzw.solutions.codeforces.p996A;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] ps = new int[] {1, 5, 10, 20, 100};
        int c = 0;
        for (int j = ps.length - 1; j >= 0; j--) {
            if (n >= ps[j]) {
                int c1 = n / ps[j];
                c += c1;

                n -= ps[j] * c1;
                if (n == 0) {
                    break;
                }
            }
        }
        System.out.println(c);
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}
