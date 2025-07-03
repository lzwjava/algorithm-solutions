package com.lzw.solutions.codeforces.p1367A;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            String s = in.next();
            String sub = s.substring(1, s.length() - 1);
            StringBuilder sb = new StringBuilder();
            sb.append(s.charAt(0));
            for (int i = 0; i < sub.length(); i += 2) {
                sb.append(sub.charAt(i));
            }
            sb.append(s.charAt(s.length() - 1));
            System.out.println(sb.toString());
            t--;
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
