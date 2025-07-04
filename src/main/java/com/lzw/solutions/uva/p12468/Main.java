package com.lzw.solutions.uva.p12468;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            if (a == -1 && b == -1) {
                break;
            }
            if (a > b) {
                int tmp = a;
                a = b;
                b = tmp;
            }
            int len = b - a;
            int cyclic = a + 100 - b;
            int min = len < cyclic ? len : cyclic;
            System.out.println(min);
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {

        new Main().solve();
    }
}
