package com.algorithm.solutions.codeforces.p144A;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] as = new int[n];
        for (int i = 0; i < n; i++) {
            as[i] = in.nextInt();
        }
        int[] cs = as.clone();
        Arrays.sort(cs);
        int ans;
        if (as[0] == cs[n - 1] && as[n - 1] == cs[0]) {
            ans = 0;
        } else {
            int i;
            for (i = 0; i < n; i++) {
                if (as[i] == cs[n - 1]) {
                    break;
                }
            }
            int j;
            for (j = n - 1; j >= 0; j--) {
                if (as[j] == cs[0]) {
                    break;
                }
            }
            if (i < j) {
                ans = i + n - 1 - j;
            } else {
                ans = i - 1 + n - 1 - j;
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
