package com.lzw.solutions.uva.p10079;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            long n = sc.nextInt();
            if (n < 0) {
                break;
            }
            long ans = n * (n + 1) / 2 + 1;
            System.out.println(ans);
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {

        new Main().solve();
    }
}
