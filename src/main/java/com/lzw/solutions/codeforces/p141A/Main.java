package com.lzw.solutions.codeforces.p141A;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {

    void addToMap(Map<Character, Integer> m, String a) {
        for (char ch : a.toCharArray()) {
            Integer c = m.get(ch);
            if (c == null) {
                c = 0;
            }
            c++;
            m.put(ch, c);
        }
    }

    void solve() {
        Scanner in = new Scanner(System.in);
        String a = in.next();
        String b = in.next();
        String c = in.next();
        boolean ans;
        int an = a.length();
        int bn = b.length();
        int cn = c.length();
        if (an + bn != cn) {
            ans = false;
        } else {
            Map<Character, Integer> m1 = new HashMap<>();
            addToMap(m1, a);
            addToMap(m1, b);

            Map<Character, Integer> m2 = new HashMap<>();
            addToMap(m2, c);
            Set<Character> s1 = m1.keySet();
            Set<Character> s2 = m2.keySet();
            if (s1.size() != s2.size()) {
                ans = false;
            } else {
                ans = true;
                for (char ch : s1) {
                    Integer c1 = m1.get(ch);
                    Integer c2 = m2.get(ch);
                    if (c1 != c2) {
                        ans = false;
                        break;
                    }
                }
            }
        }
        if (ans) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
