package com.lzw.solutions.codeforces.p703A;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int mw = 0, cw = 0;
        while (n > 0) {
            int m = in.nextInt();
            int c = in.nextInt();
            if (m > c) {
                mw++;
            } else if (m < c) {
                cw++;
            }
            n--;
        }
        if (mw > cw) {
            System.out.println("Mishka");
        } else if (mw < cw) {
            System.out.println("Chris");
        } else {
            System.out.println("Friendship is magic!^^");
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
