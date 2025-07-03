package com.lzw.solutions.codeforces.p750A;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int d = 240 - k;
        int s = 0;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            int t = (i + 1) * 5;
            if (s + t <= d) {
                s += t;
                cnt++;
            } else {
                break;
            }
        }
        System.out.println(cnt);
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
