package com.algorithm.solutions.codeforces.p579A;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        int maxn = 31;
        int[] v = new int[maxn];
        for (int i = 0; i < maxn; i++) {
            v[i] = 1 << i;
        }
        int cnt = 0;
        for (int i = maxn - 1; i >= 0; i--) {
            if (x >= v[i]) {
                x -= v[i];
                cnt++;
                if (x == 0) {
                    break;
                }
            }
        }
        System.out.println(cnt);
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
