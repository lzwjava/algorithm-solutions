package com.lzw.solutions.uva.p11332;

import java.util.Scanner;

public class Main {

    void work() {
        Scanner sc = new Scanner(System.in);
        for (; ; ) {
            int n = sc.nextInt();
            if (n == 0) {
                break;
            }
            for (; ; ) {
                int sum = 0;
                while (n != 0) {
                    sum += n % 10;
                    n /= 10;
                }
                n = sum;
                if (n < 10) {
                    break;
                }
            }
            System.out.println(n);
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {

        new Main().work();
    }
}
