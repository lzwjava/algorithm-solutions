package com.lzw.solutions.uva.p10107;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    void solve() {
        Scanner sc = new Scanner(System.in);

        ArrayList<Integer> nums = new ArrayList<>();
        while (sc.hasNextInt()) {
            int x = sc.nextInt();
            int find = Collections.binarySearch(nums, x);
            if (find < 0) {
                nums.add(-find - 1, x);
            } else {
                nums.add(find, x);
            }
            int m;
            int numslen = nums.size();
            if (numslen % 2 == 0) {
                m = (nums.get(numslen / 2) + nums.get(numslen / 2 - 1)) / 2;
            } else {
                m = nums.get(numslen / 2);
            }
            System.out.println(m);
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {

        new Main().solve();
    }
}
