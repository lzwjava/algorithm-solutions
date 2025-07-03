package com.lzw.solutions.codeforces.p379A;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int b = in.nextInt();
        int s = 0;
        int c = 0;
        while (a > 0) {
            s += a;
            c += a;
            if (c >= b) {
                a = c / b;
                c -= a * b;
            } else {
                break;
            }
        }
        System.out.println(s);
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}
