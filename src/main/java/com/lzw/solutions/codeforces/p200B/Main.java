package com.lzw.solutions.codeforces.p200B;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int s = 0;
        for (int i = 0; i < n; i++) {
            int v = in.nextInt();
            s += v;
        }
        System.out.println(s * 1.0 / n);
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}
