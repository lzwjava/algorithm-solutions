package com.lzw.solutions.codeforces.p935A;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int c = 0;
        for (int a = 1; a < n; a++) {
            int d = n - a;
            if (d % a == 0) {
                c++;
            }
        }
        System.out.println(c);
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
