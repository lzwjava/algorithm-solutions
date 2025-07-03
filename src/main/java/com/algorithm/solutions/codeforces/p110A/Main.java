package com.algorithm.solutions.codeforces.p110A;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        int cnt = 0;
        char[] chs = s.toCharArray();
        for (char c : chs) {
            if (c == '4' || c == '7') {
                cnt++;
            }
        }
        if (cnt == 4 || cnt == 7) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

}
