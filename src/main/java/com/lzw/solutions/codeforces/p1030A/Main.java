package com.lzw.solutions.codeforces.p1030A;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        boolean hard = false;
        for (int i = 0; i < n; i++) {
            int v = in.nextInt();
            if (v == 1) {
                hard = true;
            }
        }
        if (hard) {
            System.out.println("HARD");
        } else {
            System.out.println("EASY");
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}
