package com.lzw.solutions.uva.p1585;

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
            int len = s.length();
            int oCount = 0;
            int score = 0;
            for (int j = 0; j < len; j++) {
                if (s.charAt(j) == 'O') {
                    oCount++;
                } else {
                    oCount = 0;
                }
                score += oCount;
            }
            System.out.println(score);
        }
        sc.close();

        if (inStream != null) {
            inStream.close();
        }
    }
}
