package com.lzw.solutions.uva.p11854;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    void solve() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int nums[] = new int[3];
            boolean allZero = true;
            for (int i = 0; i < 3; i++) {
                nums[i] = sc.nextInt();
                if (nums[i] != 0) {
                    allZero = false;
                }
            }
            if (allZero) {
                break;
            }
            Arrays.sort(nums);
            if (nums[0] * nums[0] + nums[1] * nums[1] == nums[2] * nums[2]) {
                System.out.println("right");
            } else {
                System.out.println("wrong");
            }
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {

        new Main().solve();
    }
}
