package com.lzw.solutions.codeforces.p339B;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[] as = new int[m];
        for (int i = 0; i < m; i++) {
            as[i] = in.nextInt();
        }
        int p = 1;
        long cnt = 0;
        for (int i = 0; i < m; i++) {
            if (as[i] == p) {
                // do
            } else {
                if (as[i] > p) {
                    int d = as[i] - p;
                    cnt += d;
                    p = as[i];
                } else {
                    //as[i] < p
                    int d = n - p + as[i];
                    cnt += d;
                    p = as[i];
                }
            }
        }
        System.out.println(cnt);
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
