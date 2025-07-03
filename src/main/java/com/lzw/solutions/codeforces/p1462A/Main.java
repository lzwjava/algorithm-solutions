package com.lzw.solutions.codeforces.p1462A;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            int n = in.nextInt();
            LinkedList<Integer> list = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                list.add(in.nextInt());
            }
            ArrayList<Integer> ans = new ArrayList<>();
            boolean left = true;
            while (!list.isEmpty()) {
                int v;
                if (left) {
                    v = list.pollFirst();
                } else {
                    v = list.pollLast();
                }
                ans.add(v);
                left = !left;
            }
            for (int i = 0; i < n; i++) {
                if (i != 0) {
                    System.out.print(' ');
                }
                System.out.print(ans.get(i));
            }
            System.out.println();
            t--;
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}
