package com.lzw.solutions.uva.p562;

import java.util.Scanner;

public class Main1 {

    int minDiff;

    void permutation(int nums[], boolean selected[], int i) {
        if (minDiff == 0) {
            return;
        }
        if (i == selected.length) {
            int s1 = 0;
            int s2 = 0;
            for (int j = 0; j < selected.length; j++) {
                if (selected[j]) {
                    s1 += nums[j];
                } else {
                    s2 += nums[j];
                }
            }
            if (Math.abs(s1 - s2) < minDiff) {
                minDiff = Math.abs(s1 - s2);
            }
            return;
        }
        selected[i] = false;
        permutation(nums, selected, i + 1);
        selected[i] = true;
        permutation(nums, selected, i + 1);
    }

    void solve() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        while (n > 0) {
            int m = sc.nextInt();
            int nums[] = new int[m];
            for (int i = 0; i < m; i++) {
                nums[i] = sc.nextInt();
            }
            minDiff = Integer.MAX_VALUE;
            boolean selected[] = new boolean[m];
            permutation(nums, selected, 0);

            System.out.println(minDiff);
            n--;
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {

        new Main().solve();
    }
}
