package com.lzw.solutions.codeforces.p313A;

import java.util.Scanner;

public class Main {

    int deleteDigit(int n, int pos) {
        String s = String.format("%d", n);
        int len = s.length();
        int deletePos = len - 1 + pos;
        if (deletePos >= 0 && deletePos < len && s.charAt(deletePos) != '-') {
            String ns = s.substring(0, deletePos) + s.substring(deletePos + 1);
            return Integer.parseInt(ns);
        } else {
            return Integer.MAX_VALUE;
        }
    }

    void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int max = n;
        for (int i = 0; i >= -1; i--) {
            int v = deleteDigit(n, i);
            if (v != Integer.MAX_VALUE) {
                max = Integer.max(max, v);
            }
        }
        System.out.println(max);
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}
