package com.algorithm.solutions.codeforces.p546A;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int k = in.nextInt();
        int n = in.nextInt();
        int w = in.nextInt();
        int d = (1 + w) * w / 2 * k;
        int ans = 0;
        if (d > n) {
            ans = d - n;
        }
        System.out.println(ans);
    }

}
