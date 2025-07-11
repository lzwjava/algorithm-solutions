package com.lzw.solutions.uva.p11057;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    void solve() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            int nums[] = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = sc.nextInt();
            }
            int m = sc.nextInt();
            Arrays.sort(nums);
            int minDiff = Integer.MAX_VALUE;
            int ai = 0, aj = 0;
            for (int i = 0; i < n; i++) {
                int v = m - nums[i];
                if (v > 0) {
                    int j = Arrays.binarySearch(nums, i + 1, n, v);
                    if (j >= 0) {
                        if (j - i < minDiff) {
                            minDiff = j - i;
                            ai = i;
                            aj = j;
                        }
                    }
                }
            }
            System.out.println(String.format("Peter should buy books whose prices are %d and %d.", nums[ai], nums[aj]));
            System.out.println();
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {

        new Main().solve();
    }
}
