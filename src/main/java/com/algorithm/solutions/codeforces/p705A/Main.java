package com.algorithm.solutions.codeforces.p705A;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        boolean hate = true;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n - 1; i++) {
            if (hate) {
                sb.append("I hate that ");
            } else {
                sb.append("I love that ");
            }
            hate = !hate;
        }
        if (hate) {
            sb.append("I hate it");
        } else {
            sb.append("I love it");
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
