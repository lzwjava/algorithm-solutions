package com.lzw.solutions.uva.p10340;

import java.util.Scanner;

public class Main {

    boolean subsequence(String s, String t) {
        if (s.isEmpty() || t.isEmpty()) {
            if (s.isEmpty() && t.isEmpty()) {
                return true;
            } else if (s.isEmpty()) {
                return true;
            } else {
                return false;
            }
        }
        char sch = s.charAt(0);
        char tch = t.charAt(0);
        if (sch == tch) {
            return subsequence(s.substring(1), t.substring(1));
        } else {
            return subsequence(s, t.substring(1));
        }
    }

    boolean subsequence1(String s, String t) {
        int sp = 0, tp = 0;
        boolean is = false;
        for (; ; ) {
            if (sp == s.length()) {
                is = true;
                break;
            }
            if (tp == t.length()) {
                break;
            }
            char sch = s.charAt(sp);
            char tch = t.charAt(tp);
            if (sch == tch) {
                sp++;
                tp++;
            } else {
                tp++;
            }
        }
        return is;
    }

    void work() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String s = sc.next();
            String t = sc.next();

            boolean is = subsequence1(s, t);
            if (is) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {

        new Main().work();
    }
}
