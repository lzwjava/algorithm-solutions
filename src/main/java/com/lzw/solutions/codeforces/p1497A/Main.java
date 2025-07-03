package com.lzw.solutions.codeforces.p1497A;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        new Main().solve();
    }

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            t--;
            int n = in.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
            }
            Arrays.sort(a);
            List<Integer> list = new ArrayList<>();
            list.add(a[0]);
            List<Integer> rest = new ArrayList<>();
            for (int i = 1; i < n; i++) {
                if (a[i] != a[i - 1]) {
                    list.add(a[i]);
                } else {
                    rest.add(a[i]);
                }
            }
            list.addAll(rest);
            for (int i = 0; i < n; i++) {
                if (i != 0) {
                    System.out.print(' ');
                }
                System.out.print(String.format("%d", list.get(i)));
            }
            System.out.println();
        }
    }

}