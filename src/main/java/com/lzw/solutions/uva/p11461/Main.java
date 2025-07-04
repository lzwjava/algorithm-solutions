package com.lzw.solutions.uva.p11461;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            if (a == 0 && b == 0) {
                break;
            }
            int cnt = 0;
            for (int i = a; i <= b; i++) {
                int si = (int) Math.sqrt(i);
                if (si * si == i) {
                    cnt++;
                }
            }
            System.out.println(cnt);
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {

        new Main().solve();
    }
}
