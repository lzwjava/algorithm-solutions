package com.lzw.solutions.codeforces.p131A;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        int len = s.length();
        boolean ok = true;
        for (int i = 1; i < len; i++) {
            if (Character.isLowerCase(s.charAt(i))) {
                ok = false;
                break;
            }
        }
        if (!ok) {
            System.out.println(s);
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < len; i++) {
                char ch = s.charAt(i);
                char nch;
                if (Character.isLowerCase(ch)) {
                    nch = Character.toUpperCase(ch);
                } else {
                    nch = Character.toLowerCase(ch);
                }
                sb.append(nch);
            }
            System.out.println(sb.toString());
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}
