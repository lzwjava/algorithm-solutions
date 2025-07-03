package com.lzw.solutions.codeforces.p148A;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int[] ns = new int[4];
        for (int i = 0; i < 4; i++) {
            ns[i] = in.nextInt();
        }
        int d = in.nextInt();
        boolean[] vis = new boolean[d + 1];
        for (int i = 0; i < 4; i++) {
            for (int j = ns[i]; j <= d; j += ns[i]) {
                vis[j] = true;
            }
        }
        int c = 0;
        for (int j = 1; j <= d; j++) {
            if (vis[j]) {
                c++;
            }
        }
        System.out.println(c);
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}
