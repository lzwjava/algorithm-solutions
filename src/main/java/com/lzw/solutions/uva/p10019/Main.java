package com.lzw.solutions.uva.p10019;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        while (n > 0) {
            int m = sc.nextInt();
            String binary = Integer.toBinaryString(m);
            int b1 = 0;
            for (int i = 0; i < binary.length(); i++) {
                int bit = binary.charAt(i) - '0';
                if (bit == 1) {
                    b1++;
                }
            }

            int hex = Integer.parseInt(m + "", 16);
            String hexBinary = Integer.toBinaryString(hex);
            int b2 = 0;
            for (int i = 0; i < hexBinary.length(); i++) {
                int bit = hexBinary.charAt(i) - '0';
                if (bit == 1) {
                    b2++;
                }
            }

            System.out.println(String.format("%d %d", b1, b2));

            n--;
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {

        new Main().solve();
    }
}
