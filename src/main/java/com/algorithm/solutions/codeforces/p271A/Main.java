package com.algorithm.solutions.codeforces.p271A;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int y = in.nextInt();
        do {
            y++;
            String s = String.format("%d", y);
            Set<Character> set = new HashSet<>();
            for (char c : s.toCharArray()) {
                set.add(c);
            }
            if (set.size() == s.length()) {
                break;
            }
        } while (true);
        System.out.println(y);
    }

}
