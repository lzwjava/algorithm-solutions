package com.lzw.solutions.codeforces.p1454A;

import java.util.Scanner;

public class Main {

    boolean permutation(int[] nums, boolean[] vis, int cur, int n) {
        if (cur == n) {
            for (int i = 0; i < n; i++) {
                if (i != 0) {
                    System.out.print(' ');
                }
                System.out.print(nums[i] + 1);
            }
            System.out.println();
            return true;
        }
        for (int i = 0; i < n; i++) {
            if (!vis[i] && i != cur) {
                vis[i] = true;
                nums[cur] = i;
                boolean found = permutation(nums, vis, cur + 1, n);
                if (found) {
                    return true;
                }
                vis[i] = false;
            }
        }
        return false;
    }

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            int n = in.nextInt();
            boolean[] vis = new boolean[n];
            int[] nums = new int[n];
            permutation(nums, vis, 0, n);
            t--;
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
