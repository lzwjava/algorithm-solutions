package com.lzw.solutions.uva.p11727;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    void work() {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int caseNum = 1;
        while (t > 0) {
            int nums[] = new int[3];
            nums[0] = sc.nextInt();
            nums[1] = sc.nextInt();
            nums[2] = sc.nextInt();
            Arrays.sort(nums);
            System.out.println(String.format("Case %d: %d", caseNum, nums[1]));
            caseNum++;
            t--;
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {

        new Main().work();
    }
}
