package com.lzw.solutions.uva.p1594;

import java.util.HashSet;
import java.util.Scanner;

public class Main {

    String numsToString(int nums[]) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(nums[i]);
        }
        return sb.toString();
    }

    boolean allZeros(int nums[]) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                return false;
            }
        }
        return true;
    }

    void work() {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            int n = sc.nextInt();
            int nums[] = new int[n];
            for (int j = 0; j < n; j++) {
                nums[j] = sc.nextInt();
            }
            HashSet<String> sequence = new HashSet<>();
            sequence.add(numsToString(nums));
            int result = -1;
            for (int j = 0; j < 1000; j++) {
                int nnums[] = new int[n];
                for (int k = 0; k < n; k++) {
                    int nextK = k + 1;
                    if (nextK == n) {
                        nextK = 0;
                    }
                    nnums[k] = Math.abs(nums[k] - nums[nextK]);
                }
                if (allZeros(nnums)) {
                    result = 0;
                    break;
                }
                String ns = numsToString(nnums);
                if (sequence.contains(ns)) {
                    result = 1;
                    break;
                }
                sequence.add(ns);
                nums = nnums;
            }
            if (result == 0) {
                System.out.println("ZERO");
            } else if (result == 1) {
                System.out.println("LOOP");
            } else {
                assert (result != -1);
            }
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {

        new Main().work();
    }
}
