package com.lzw.solutions.uva.p455;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        boolean first = true;
        for (int i = 0; i < T; i++) {
            String s = sc.next();
            int p = 1;
            int len = s.length();
            for (; p <= len; p++) {
                if (len % p == 0) {
                    int n = len / p;
                    boolean ok = true;
                    String rep = s.substring(0, p);
                    for (int j = 1; j < n; j++) {
                        String sub = s.substring(p * j, p * (j + 1));
                        if (!sub.equals(rep)) {
                            ok = false;
                            break;
                        }
                    }
                    if (ok) {
                        if (!first) {
                            System.out.println();
                        }
                        first = false;
                        System.out.println(p);
                        break;
                    }
                }
            }
        }
        sc.close();
    }
}
