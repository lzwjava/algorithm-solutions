package com.lzw.solutions.uva.p10931;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int n = sc.nextInt();
            if (n == 0) {
                break;
            }
            String binary = Integer.toString(n, 2);
            int parity = 0;
            for (int i = 0; i < binary.length(); i++) {
                char ch = binary.charAt(i);
                if (ch - '0' == 1) {
                    parity++;
                }
            }
            System.out.println(String.format("The parity of %s is %d (mod 2).", binary, parity));
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {

        new Main().solve();
    }
}
