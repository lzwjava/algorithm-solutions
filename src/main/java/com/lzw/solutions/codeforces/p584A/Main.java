package com.lzw.solutions.codeforces.p584A;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int t = in.nextInt();
        BigInteger bi = BigInteger.ONE;
        for (int i = 0; i < n - 1; i++) {
            bi = bi.multiply(BigInteger.valueOf(10));
        }
        BigInteger bt = BigInteger.valueOf(t);
        boolean found = false;
        while (bi.toString().length() == n) {
            if (bi.mod(bt).intValue() == 0) {
                found = true;
                break;
            } else {
                bi = bi.add(BigInteger.ONE);
            }
        }
        if (found) {
            System.out.println(bi.toString());
        } else {
            System.out.println(-1);
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}
