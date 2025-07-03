package com.lzw.solutions.codeforces.p707A;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        boolean color = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char c = in.next().charAt(0);
                String s = "CMY";
                int p = s.indexOf(c);
                if (p >= 0) {
                    color = true;
                    break;
                }
            }
            if (color) {
                break;
            }
        }
        if (color) {
            System.out.println("#Color");
        } else {
            System.out.println("#Black&White");
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}
