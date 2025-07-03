package com.algorithm.solutions.codeforces.p467A;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            int p = in.nextInt();
            int q = in.nextInt();
            if (p + 2 <= q) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }

}
