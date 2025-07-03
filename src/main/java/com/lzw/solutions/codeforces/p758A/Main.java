package com.lzw.solutions.codeforces.p758A;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];
        int max = 0;
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            max = Integer.max(max, a[i]);
        }
        int s = 0;
        for (int i = 0; i < n; i++) {
            s += max - a[i];
        }
        System.out.println(s);
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
