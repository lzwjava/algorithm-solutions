package com.lzw.solutions.uva.p10327;

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
            int op = 0;
            while (true) {
                boolean swap = false;
                for (int i = 0; i < n - 1; i++) {
                    if (nums[i] > nums[i + 1]) {
                        int tmp = nums[i];
                        nums[i] = nums[i + 1];
                        nums[i + 1] = tmp;
                        op++;
                        swap = true;
                    }
                }
                if (!swap) {
                    break;
                }
            }
            System.out.println(String.format("Minimum exchange operations : %d", op));
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {

        new Main().solve();
    }
}
