package com.lzw.solutions.uva.p483;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String words[] = line.split(" ");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < words.length; i++) {
                if (i != 0) {
                    sb.append(" ");
                }
                StringBuilder word = new StringBuilder(words[i]);
                sb.append(word.reverse());
            }
            System.out.println(sb.toString());
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {

        new Main().solve();
    }
}
