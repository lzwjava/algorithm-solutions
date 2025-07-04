package com.lzw.solutions.codeforces.p1552A;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    String sortString(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    void work() {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            int n = sc.nextInt();
            String s = sc.next();
            String sorted = sortString(s);
            int diff = 0;
            for (int j = 0; j < n; j++) {
                if (s.charAt(j) != sorted.charAt(j)) {
                    diff++;
                }
            }
            System.out.println(diff);
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {

        new Main().work();
    }
}
