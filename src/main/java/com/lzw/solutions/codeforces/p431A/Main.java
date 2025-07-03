package com.lzw.solutions.codeforces.p431A;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int[] a = new int[4];
        for (int i = 0; i < 4; i++) {
            a[i] = in.nextInt();
        }
        String s = in.next();
        int p = 0;
        for (char c : s.toCharArray()) {
            int d = c - '0';
            p += a[d - 1];
        }
        System.out.println(p);
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
