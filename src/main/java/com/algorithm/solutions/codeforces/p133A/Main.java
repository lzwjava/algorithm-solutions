package com.algorithm.solutions.codeforces.p133A;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        boolean ok = false;
        for (char c : s.toCharArray()) {
            if (c == 'H' || c == 'Q' || c == '9') {
                ok = true;
                break;
            }
        }
        if (ok) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

}
