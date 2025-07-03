package com.algorithm.solutions.codeforces.p460A;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int cnt = 0;
        for (int i = 1; ; i++) {
            if (n == 0) {
                break;
            }
            cnt++;
            n--;
            if (i % m == 0) {
                n += 1;
            }
        }
        System.out.println(cnt);
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
