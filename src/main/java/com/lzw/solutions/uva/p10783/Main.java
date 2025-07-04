package com.lzw.solutions.uva.p10783;

import java.util.Scanner;

public class Main {

    void work() {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int caseNum = 1;
        while (t > 0) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int sum = 0;
            for (int i = a; i <= b; i++) {
                if (i % 2 == 1) {
                    sum += i;
                }
            }
            System.out.println(String.format("Case %d: %d", caseNum, sum));

            caseNum++;
            t--;
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {

        new Main().work();
    }
}
