package com.lzw.solutions.codeforces.p580A;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int prev = -1;
        int len = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            int v = in.nextInt();
            if (v >= prev) {
                len++;
            } else {
                len = 1;
            }
            if (len > max) {
                max = len;
            }
            prev = v;
        }
        System.out.println(max);
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}
