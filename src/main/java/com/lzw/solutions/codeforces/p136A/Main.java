package com.lzw.solutions.codeforces.p136A;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] ps = new int[n];
        for (int i = 0; i < n; i++) {
            ps[i] = in.nextInt() - 1;
        }
        int[] fs = new int[n];
        for (int i = 0; i < n; i++) {
            fs[ps[i]] = i;
        }
        for (int i = 0; i < n; i++) {
            if (i != 0) {
                System.out.print(" ");
            }
            System.out.print(fs[i] + 1);
        }
        System.out.println();
    }
}
