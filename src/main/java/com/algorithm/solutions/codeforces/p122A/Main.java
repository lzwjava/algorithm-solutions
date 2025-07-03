package com.algorithm.solutions.codeforces.p122A;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        if (n % 4 == 0 || n % 7 == 0 || n % 47 == 0 || n % 447 == 0 || n % 477 == 0 || n % 747 == 0) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

}
