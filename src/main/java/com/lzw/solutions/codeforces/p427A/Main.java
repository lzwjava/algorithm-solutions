package com.lzw.solutions.codeforces.p427A;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int officers = 0;
        int untreated = 0;
        for (int i = 0; i < n; i++) {
            int v = in.nextInt();
            if (v == -1) {
                if (officers > 0) {
                    officers--;
                } else {
                    untreated++;
                }
            } else {
                officers += v;
            }
        }
        System.out.print(untreated);
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}
