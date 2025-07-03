package com.lzw.solutions.codeforces.p160A;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] vs = new int[n];
        int s = 0;
        for (int i = 0; i < n; i++) {
            vs[i] = in.nextInt();
            s += vs[i];
        }
        Arrays.sort(vs);
        int p = 0;
        int cnt = 0;
        for (int i = n - 1; i >= 0; i--) {
            p += vs[i];
            cnt++;
            if (p > s - p) {
                break;
            }
        }
        System.out.println(cnt);
    }
}
