package com.lzw.solutions.codeforces.p791A;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int b = in.nextInt();
        int y = 0;
        while (a <= b) {
            a *= 3;
            b *= 2;
            y++;
        }
        System.out.println(y);
    }
}
