package com.lzw.solutions.codeforces.p1520B;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            int n = in.nextInt();
            String s = String.format("%d", n);
            int len = s.length();
            int a = (len - 1) * 9;

            if (n < 1000000000) {
                List<Integer> list = new ArrayList<>();
                for (int i = 1; i <= 9; i++) {
                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < len; j++) {
                        sb.append(String.valueOf(i));
                    }
                    list.add(Integer.parseInt(sb.toString()));
                }
                for (int x : list) {
                    if (n >= x) {
                        a++;
                    } else {
                        break;
                    }
                }
            }
            System.out.println(a);
            t--;
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}
