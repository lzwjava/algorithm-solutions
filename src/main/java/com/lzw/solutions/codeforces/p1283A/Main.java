package com.lzw.solutions.codeforces.p1283A;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            int h = in.nextInt();
            int m = in.nextInt();
            int s = (23 - h) * 60 + 60 - m;
            System.out.println(s);
            t--;
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
