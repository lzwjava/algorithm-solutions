package com.lzw.solutions.uva.p100;

import java.util.Scanner;

public class Main {

    int calN(int n) {
        int cnt = 0;
        for (; ; ) {
            cnt++;
            if (n == 1) {
                break;
            }
            if (n % 2 == 1) {
                n = 3 * n + 1;
            } else {
                n = n / 2;
            }
        }
        return cnt;
    }

    void work() {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextInt()) {
            int i = sc.nextInt();
            int j = sc.nextInt();
            int n = 0;
            int min = Math.min(i, j);
            int max = Math.max(i, j);
            for (int k = min; k <= max; k++) {
                int len = calN(k);
                if (len > n) {
                    n = len;
                }
            }
            System.out.println(String.format("%d %d %d", i, j, n));
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {

        new Main().work();
    }
}
