package com.lzw.solutions.codeforces.p443A;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        String ns = s.substring(1, s.length() - 1);
        int ans;
        if (ns.equals("")) {
            ans = 0;
        } else {
            String[] splits = ns.split(", ");
            Set<String> set = new HashSet<>();
            set.addAll(Arrays.asList(splits));
            ans = set.size();
        }
        System.out.println(ans);
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}
