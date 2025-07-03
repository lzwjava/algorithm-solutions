package com.algorithm.solutions.codeforces.p4C;

import java.util.*;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Set<String> names = new HashSet<>();
        Map<String, Integer> count = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String name = in.next();
            if (!names.contains(name)) {
                System.out.println("OK");
                names.add(name);
            } else {
                Integer c = count.get(name);
                if (c == null) {
                    c = 0;
                }
                c++;
                String s = String.format("%s%d", name, c);
                names.add(s);
                count.put(name, c);
                System.out.println(s);
            }
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
