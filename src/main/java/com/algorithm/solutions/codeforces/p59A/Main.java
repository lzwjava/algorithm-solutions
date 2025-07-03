package com.algorithm.solutions.codeforces.p59A;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        int n = s.length();
        int low = 0;
        char[] chs = s.toCharArray();
        for (char c : chs) {
            if (Character.isLowerCase(c)) {
                low++;
            }
        }
        int up = n - low;
        boolean toLow = low >= up;
        StringBuilder sb = new StringBuilder();
        for (char c : chs) {
            char nc;
            if (toLow) {
                nc = Character.toLowerCase(c);
            } else {
                nc = Character.toUpperCase(c);
            }
            sb.append(nc);
        }
        System.out.println(sb.toString());
    }

}
