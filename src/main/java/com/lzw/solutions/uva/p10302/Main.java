package com.lzw.solutions.uva.p10302;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            long s = 0;
            for (long i = 1; i <= n; i++) {
                s += i * i * i;
            }
            System.out.println(s);
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {

        new Main().solve();
    }
}
