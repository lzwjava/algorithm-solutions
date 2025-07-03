package com.lzw.solutions.codeforces.p489C;

import java.util.Scanner;

public class Main {

    String arrToStr(int[] a) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            sb.append(String.format("%d", a[i]));
        }
        return sb.toString();
    }

    void solve() {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int s = in.nextInt();
        if ((s == 0 && m != 1) || s > m * 9) {
            System.out.println(String.format("%d %d", -1, -1));
        } else {
            int[] a = new int[m];
            int p = s;
            for (int i = m - 1; i >= 0; i--) {
                if (p > 9) {
                    a[i] = 9;
                    p -= 9;
                } else {
                    if (i == 0) {
                        a[i] = p;
                    } else {
                        a[i] = p - 1;
                        a[0] = 1;
                        break;
                    }
                }
            }
            int[] b = new int[m];
            p = s;
            for (int i = 0; i < m; i++) {
                if (p >= 9) {
                    b[i] = 9;
                    p -= 9;
                } else {
                    b[i] = p;
                    p -= p;
                }
                if (p == 0) {
                    break;
                }
            }
            String sa = arrToStr(a);
            String sb = arrToStr(b);
            System.out.println(String.format("%s %s", sa, sb));
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}
