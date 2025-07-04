package com.lzw.solutions.uva.p484;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    class Num implements Comparable<Num> {
        int v;
        int pos;
        int count;

        Num() {
            v = 0;
            pos = 0;
            count = 0;
        }

        @Override
        public int compareTo(Main.Num o) {
            return pos - o.pos;
        }
    }

    void solve() {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> nums = new ArrayList<>();
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            nums.add(n);
        }

        HashMap<Integer, Num> map = new HashMap<>();
        for (int i = 0; i < nums.size(); i++) {
            int v = nums.get(i);
            Num num = map.get(v);
            if (num == null) {
                num = new Num();
                num.v = v;
                num.pos = i;
                num.count = 1;
                map.put(v, num);
            } else {
                num.count++;
            }
        }
        ArrayList<Num> ns = new ArrayList<>();
        ns.addAll(map.values());

        Collections.sort(ns);
        for (Num num : ns) {
            System.out.println(String.format("%d %d", num.v, num.count));
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {

        new Main().solve();
    }
}
