package com.lzw.solutions.uva.p11547;

import java.util.Scanner;

public class Main {

    void work() {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t > 0) {
            int n = sc.nextInt();
            long num = (n * 567 / 9 + 7492) * 235 / 47 - 498;
            System.out.println(Math.abs(num % 100 / 10));
            t--;
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {

        new Main().work();
    }
}
