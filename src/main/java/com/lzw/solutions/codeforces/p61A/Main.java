package com.lzw.solutions.codeforces.p61A;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        String a = in.next();
        String b = in.next();
        StringBuilder sb = new StringBuilder();
        int n = a.length();
        for (int i = 0; i < n; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                sb.append('1');
            } else {
                sb.append('0');
            }
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}
