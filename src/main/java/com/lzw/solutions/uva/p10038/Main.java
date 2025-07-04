package com.lzw.solutions.uva.p10038;

import java.util.Scanner;

public class Main {

    void work() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            int nums[] = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = sc.nextInt();
            }
            boolean ok = true;
            boolean[] takes = new boolean[n];
            for (int i = 0; i < n - 1; i++) {
                int v = Math.abs(nums[i] - nums[i + 1]);
                if (v >= 1 && v <= n - 1) {
                    if (!takes[v]) {
                        takes[v] = true;
                    } else {
                        ok = false;
                        break;
                    }
                } else {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                System.out.println("Jolly");
            } else {
                System.out.println("Not jolly");
            }
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {

        new Main().work();
    }
}
