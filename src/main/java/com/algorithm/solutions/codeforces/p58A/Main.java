package com.algorithm.solutions.codeforces.p58A;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        String hello = "hello";
        char[] chs = s.toCharArray();
        int p = 0;
        int hn = hello.length();
        for (char c : chs) {
            if (p == hn) {
                break;
            }
            if (c == hello.charAt(p)) {
                p++;
            }
        }
        if (p == hn) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

}
