package com.lzw.solutions.uva.p10370;

import java.util.Scanner;

public class Main {

    void work() {
        Scanner sc = new Scanner(System.in);
        int c = sc.nextInt();
        while (c > 0) {
            int n = sc.nextInt();
            int nums[] = new int[n];
            double avg = 0;
            for (int i = 0; i < n; i++) {
                nums[i] = sc.nextInt();
                avg += nums[i];
            }
            avg /= n;
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                if (nums[i] > avg) {
                    cnt++;
                }
            }
            System.out.println(String.format("%.3f%%", cnt * 100.0 / n));
            c--;
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {

        new Main().work();
    }
}
