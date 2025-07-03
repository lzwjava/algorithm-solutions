package com.lzw.solutions.uva.p1225;

import java.io.FileInputStream;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        FileInputStream inStream = null;
        boolean isLocal = System.getProperty("os.name").equals("Mac OS X");
        if (isLocal) {
            inStream = new FileInputStream("1.in");
            System.setIn(inStream);
        }
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            int n = sc.nextInt();
            StringBuilder sb = new StringBuilder();
            for (int j = 1; j <= n; j++) {
                sb.append(String.format("%d", j));
            }
            String s = sb.toString();
            int cnts[] = new int[10];
            for (int j = 0; j < s.length(); j++) {
                cnts[s.charAt(j) - '0']++;
            }
            for (int j = 0; j < 10; j++) {
                if (j != 0) {
                    System.out.print(" ");
                }
                System.out.print(cnts[j]);
            }
            System.out.println();
        }
        sc.close();

        if (isLocal) {
            inStream.close();
        }
    }
}
