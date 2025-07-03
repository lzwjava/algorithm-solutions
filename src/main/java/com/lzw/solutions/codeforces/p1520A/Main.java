package com.lzw.solutions.codeforces.p1520A;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            int n = in.nextInt();
            String s = in.next();
            char lc = ' ';
            Set<Character> set = new HashSet<>();
            boolean ok = true;
            for (char c : s.toCharArray()) {
                if (lc != c) {
                    if (set.contains(c)) {
                        ok = false;
                        break;
                    }
                    set.add(c);
                }
                lc = c;
            }
            if (ok) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
            t--;
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
