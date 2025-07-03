package com.lzw.solutions.codeforces.p25A;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] as = new int[n];
        int s = 0;
        for (int i = 0; i < n; i++) {
            int v = in.nextInt();
            int p = v % 2;
            as[i] = p;
            s += p;
        }
        int q;
        if (s == 1) {
            q = 1;
        } else {
            q = 0;
        }
        int i;
        for (i = 0; i < n; i++) {
            if (as[i] == q) {
                break;
            }
        }
        System.out.println(i + 1);
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
