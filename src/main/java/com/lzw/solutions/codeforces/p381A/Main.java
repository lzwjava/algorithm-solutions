package com.lzw.solutions.codeforces.p381A;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            list.add(in.nextInt());
        }
        int a = 0, b = 0;
        boolean p = true;
        while (!list.isEmpty()) {
            int f = list.getFirst();
            int l = list.getLast();
            if (f > l) {
                if (p) {
                    a += f;
                } else {
                    b += f;
                }
                list.removeFirst();
            } else {
                if (p) {
                    a += l;
                } else {
                    b += l;
                }
                list.removeLast();
            }
            p = !p;
        }
        System.out.println(String.format("%d %d\n", a, b));
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
