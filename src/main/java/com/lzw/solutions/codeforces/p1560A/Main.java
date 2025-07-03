package com.lzw.solutions.codeforces.p1560A;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; list.size() <= 1000; i++) {
            if (i % 3 == 0 || i % 10 == 3) {
                continue;
            } else {
                list.add(i);
            }
        }

        int t = in.nextInt();
        while (t > 0) {
            int k = in.nextInt();
            System.out.println(list.get(k - 1));
            t--;
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}
