package com.algorithm.solutions.codeforces.p41A;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String a = in.next();
        String b = in.next();
        String rb = new StringBuilder(b).reverse().toString();
        if (a.equals(rb)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

}
