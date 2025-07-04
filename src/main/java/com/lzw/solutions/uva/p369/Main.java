package com.lzw.solutions.uva.p369;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            long n = sc.nextInt();
            long m = sc.nextInt();
            if (n == 0 && m == 0) {
                break;
            }
            long c = 1;
            long k = n - m;
            System.out.print(String.format("%d things taken %d at a time is ", n, m));
            while (true) {
                if (n == 1 && m == 1 && (k == 1 || k == 0)) {
                    break;
                }
                if (n > 1) {
                    c *= n;
                    n--;
                }
                while (c % m == 0 && m > 1) {
                    c /= m;
                    m--;
                }
                while (k != 0 && c % k == 0 && k > 1) {
                    c /= k;
                    k--;
                }
            }
            System.out.println(String.format("%d exactly.", c));
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {

        new Main().solve();
    }
}
