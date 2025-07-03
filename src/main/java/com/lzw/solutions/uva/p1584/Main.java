package com.lzw.solutions.uva.p1584;

import java.io.FileInputStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        FileInputStream inStream = null;
        if (System.getProperty("os.name").equals("Mac OS X")) {
            inStream = new FileInputStream("1.in");
            System.setIn(inStream);
        }

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            String s = sc.next();
            String smallest = s;
            int n = s.length();
            for (int j = 1; j < n; j++) {
                StringBuilder sb = new StringBuilder();
                sb.append(s.substring(j, n));
                sb.append(s.substring(0, j));
                if (sb.toString().compareTo(smallest) < 0) {
                    smallest = sb.toString();
                }
            }
            System.out.println(smallest);
        }
        sc.close();

        if (inStream != null) {
            inStream.close();
        }
    }
}
