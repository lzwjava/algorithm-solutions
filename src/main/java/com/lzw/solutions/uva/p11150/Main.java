package com.lzw.solutions.uva.p11150;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            int s = 0;
            while (n > 0) {
                if (n >= 3) {
                    int a = n / 3;
                    s += a * 3;
                    n -= a * 3;
                    n += a;
                } else if (n == 2) {
                    s += (n + 1);
                    n -= 2;
                } else if (n == 1) {
                    s += 1;
                    n -= 1;
                }
            }
            System.out.println(s);
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {

        new Main().solve();
    }
}
