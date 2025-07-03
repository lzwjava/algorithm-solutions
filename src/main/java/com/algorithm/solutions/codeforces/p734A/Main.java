package com.algorithm.solutions.codeforces.p734A;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String s = in.next();
        int a = 0;
        int d = 0;
        for (char c : s.toCharArray()) {
            if (c == 'A') {
                a++;
            } else if (c == 'D') {
                d++;
            }
        }
        if (a > d) {
            System.out.println("Anton");
        } else if (a == d) {
            System.out.println("Friendship");
        } else {
            System.out.println("Danik");
        }
    }

}
