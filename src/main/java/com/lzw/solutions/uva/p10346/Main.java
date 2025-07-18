package com.lzw.solutions.uva.p10346;

import java.util.Scanner;

public class Main {

    void work() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            int k = sc.nextInt();
            int c = 0;
            while (true) {
                if (n < k) {
                    c += n;
                    break;
                }
                int a = n / k;
                n -= a * k;
                c += a * k;
                n += a;
            }
            System.out.println(c);
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {

        new Main().work();
    }
}
