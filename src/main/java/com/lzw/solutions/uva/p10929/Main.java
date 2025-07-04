package com.lzw.solutions.uva.p10929;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    void solve() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String s = sc.next();
            if (s.equals("0")) {
                break;
            }
            BigInteger bs = new BigInteger(s);
            BigInteger b11 = new BigInteger("11");
            String str;
            if (bs.mod(b11).intValue() == 0) {
                str = "";
            } else {
                str = "not ";
            }
            System.out.println(String.format("%s is %sa multiple of 11.", s, str));
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {

        new Main().solve();
    }
}
