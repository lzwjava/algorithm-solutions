package com.lzw.solutions.codeforces.p1472A;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    Map<String, Integer> map;

    int dp(int w, int h) {
        if (w > h) {
            return dp(h, w);
        }
        String key = String.format("%d,%d", w, h);
        Integer v = map.get(key);
        if (v != null) {
            return v;
        }
        if (w % 2 == 1 && h % 2 == 1) {
            v = 1;
        } else {
            v = 0;
            if (w % 2 == 0) {
                v = Integer.max(v, dp(w / 2, h) * 2);
            }
            if (h % 2 == 0) {
                v = Integer.max(v, dp(w, h / 2) * 2);
            }
        }
        map.put(key, v);
        return v;
    }

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        map = new HashMap<>();
        while (t > 0) {
            int w = in.nextInt();
            int h = in.nextInt();
            int n = in.nextInt();
            int p = dp(w, h);
            if (p >= n) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
            t--;
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
