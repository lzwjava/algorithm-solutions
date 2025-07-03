package com.lzw.solutions.codeforces.p1585C;

import java.util.ArrayList;
import java.util.Collections;
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
            int n, k;
            n = in.nextInt();
            k = in.nextInt();
            int[] x = new int[n];
            List<Integer> right = new ArrayList<>();
            List<Integer> left = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                x[i] = in.nextInt();
                if (x[i] > 0) {
                    right.add(x[i]);
                } else {
                    left.add(-x[i]);
                }
            }
            Collections.sort(right, (o1, o2) -> Integer.compare(o2, o1));
            Collections.sort(left, (o1, o2) -> Integer.compare(o2, o1));

            int rn = right.size();
            int ln = left.size();
            long dist = 0;
            if (rn > 0 && ln > 0) {
                if (right.get(0) >= left.get(0)) {
                    dist += calDist(right, k) + left.get(0) + calDist(left, k);
                } else {
                    dist += calDist(left, k) + right.get(0) + calDist(right, k);
                }
            } else if (rn > 0) {
                dist = calDist(right, k);
            } else if (ln > 0) {
                dist = calDist(left, k);
            }
            System.out.println(dist);
        }
    }

    long calDist(List<Integer> depots, int k) {
        // visited depot count
        int vis = 0;
        int rn = depots.size();
        long dist = 0;
        int pos = 0;
        if (rn > 0) {
            pos = depots.get(0);
            while (vis < rn) {
                if (pos != depots.get(vis)) {
                    pos = depots.get(vis);
                    dist += pos;
                }
                if (vis + k <= rn) {
                    dist += pos;
                    pos = 0;
                    vis += k;
                } else {
                    dist += pos;
                    pos = 0;
                    vis += rn - vis;
                }
            }
        }
        return dist;
    }
}
