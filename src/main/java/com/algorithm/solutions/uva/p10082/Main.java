package com.algorithm.solutions.uva.p10082;

import java.util.*;

public class Main {
    private static String SYMBOLS = "`1234567890-=QWERTYUIOP[]\\ASDFGHJKL;'ZXCVBNM,./";

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            int n = str.length();        
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                char ch = str.charAt(i);
                if (ch == ' ') {
                    sb.append(' ');
                } else {
                    int chIndex = SYMBOLS.indexOf(ch);
                    if (chIndex > 1) {
                        sb.append(SYMBOLS.charAt(chIndex - 1));
                    }
                }
            }
            System.out.println(sb.toString());   
        }
        sc.close();
    }
}
