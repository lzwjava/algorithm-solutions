package com.lzw.solutions.uva.p213;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    void work() {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> keyStrings = new ArrayList<>();
        for (int len = 1; len <= 7; len++) {
            for (int i = 0; i < (1 << len) - 1; i++) {
                String base2 = Integer.toString(i, 2);
                String formatBase2 = String.format("%" + len + "s", base2).replace(' ', '0');
                keyStrings.add(formatBase2);
            }
        }
        String header = sc.nextLine();
        for (; ; ) {
            HashMap<String, String> map = new HashMap<>();
            for (int i = 0; i < header.length(); i++) {
                String headerChar = header.substring(i, i + 1);
                String key = keyStrings.get(i);
                map.put(key, headerChar);
            }
            String message = "";
            boolean noCase = false;
            for (; ; ) {
                if (sc.hasNext()) {
                    String line = sc.next();
                    boolean allDigit = line.chars().allMatch(Character::isDigit);
                    if (allDigit) {
                        message += line;
                    } else {
                        header = line;
                        break;
                    }
                } else {
                    noCase = true;
                    break;
                }
            }
            String decoded = "";
            int p = 0;
            while (p + 3 <= message.length()) {
                String first3Digits = message.substring(p, p + 3);
                if (first3Digits.equals("000")) {
                    break;
                }
                p += 3;
                int len = Integer.parseInt(first3Digits, 2);
                for (; ; ) {
                    if (p + len >= message.length()) {
                        break;
                    }
                    String key = message.substring(p, p + len);
                    if (keyStrings.contains(key)) {
                        String headerChar = map.get(key);
                        decoded += headerChar;
                        p += len;
                    } else {
                        p += len;
                        break;
                    }
                }
            }
            System.out.println(decoded);
            if (noCase) {
                break;
            }
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {

        new Main().work();
    }
}
