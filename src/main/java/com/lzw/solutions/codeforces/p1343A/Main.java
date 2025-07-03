package com.lzw.solutions.codeforces.p1343A;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            int n = in.nextInt();
            int s = 1;
            for (int k = 2; ; k++) {
                int v = (int) Math.pow(2, k - 1);
                s += v;
                if (n % s == 0) {
                    int x = n / s;
                    System.out.println(x);
                    break;
                }
            }
            t--;
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
