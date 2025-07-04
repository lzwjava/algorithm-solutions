package com.lzw.solutions.uva.p10222;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner sc = new Scanner(System.in);
        String[] keyboard = new String[3];
        keyboard[0] = "qwertyuiop[]\\";
        keyboard[1] = "asdfghjkl;'";
        keyboard[2] = "zxcvbnm,./";

        String s = sc.nextLine();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isAlphabetic(ch)) {
                ch = Character.toLowerCase(ch);
            }
            if (ch == ' ') {
                sb.append(ch);
            } else {
                for (int j = 0; j < keyboard.length; j++) {
                    int p = keyboard[j].indexOf(ch);
                    if (p == -1) {
                        continue;
                    } else {
                        assert (p >= 2);
                        sb.append(keyboard[j].charAt(p - 2));
                    }
                }
            }
        }
        System.out.println(sb.toString());
        sc.close();
    }

    public static void main(String[] args) throws Exception {

        new Main().solve();
    }
}
