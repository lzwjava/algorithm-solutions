package com.lzw.solutions.codeforces.p617A;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        int ans = (int) Math.ceil(x * 1.0 / 5);
        System.out.println(ans);
    }
}
