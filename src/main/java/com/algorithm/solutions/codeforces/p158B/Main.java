package com.algorithm.solutions.codeforces.p158B;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] ss = new int[5];
        for (int i = 0; i < n; i++) {
            int v = in.nextInt();
            ss[v]++;
        }
        int ans = 0;
        ans += ss[4];
        ans += ss[3];
        ss[1] -= ss[3];
        if (ss[1] < 0) {
            ss[1] = 0;
        }
        if (ss[2] % 2 == 0) {
            ans += ss[2] / 2;
            ss[2] = 0;
        } else {
            ans += (ss[2] - 1) / 2;
            ss[2] = 1;
        }
        if (ss[2] == 1) {
            ans += 1;
            if (ss[1] > 0) {
                ss[1] -= 2;
            }
            if (ss[1] < 0) {
                ss[1] = 0;
            }
        }
        if (ss[1] > 0) {
            ans += (int) Math.ceil(ss[1] * 1.0 / 4);
        }
        System.out.println(ans);
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
