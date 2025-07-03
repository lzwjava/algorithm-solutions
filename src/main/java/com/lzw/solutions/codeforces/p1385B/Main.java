package com.lzw.solutions.codeforces.p1385B;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            int n = in.nextInt();
            Set<Integer> set = new HashSet<>();
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < n * 2; i++) {
                int v = in.nextInt();
                if (!set.contains(v)) {
                    list.add(v);
                    set.add(v);
                }
            }
            for (int i = 0; i < n; i++) {
                if (i != 0) {
                    System.out.print(' ');
                }
                System.out.print(list.get(i));
            }
            System.out.println();
            t--;
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}
