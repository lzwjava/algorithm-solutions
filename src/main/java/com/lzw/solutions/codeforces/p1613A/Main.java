package com.lzw.solutions.codeforces.p1613A;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    BigInteger bi(int x, int p) {
        BigInteger x1b = BigInteger.valueOf(x);
        BigInteger bp = BigInteger.valueOf(10);
        return x1b.multiply(bp.pow(p));
    }

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            int x1 = in.nextInt();
            int p1 = in.nextInt();
            int x2 = in.nextInt();
            int p2 = in.nextInt();
            int c;
            if (Math.abs(p1 - p2) >= 12) {
                c = Integer.compare(p1, p2);
            } else {
                int mp = Integer.min(p1, p2);
                BigInteger a = bi(x1, p1 - mp);
                BigInteger b = bi(x2, p2 - mp);
                c = a.compareTo(b);
            }
            if (c > 0) {
                System.out.println(">");
            } else if (c == 0) {
                System.out.println("=");
            } else {
                System.out.println("<");
            }
            t--;
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}
