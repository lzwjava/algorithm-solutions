package com.lzw.solutions.uva.p10008;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    class Letter implements Comparable<Letter> {
        int index;
        int count;

        Letter() {
            index = 0;
            count = 0;
        }

        @Override
        public int compareTo(Main.Letter o) {
            if (count != o.count) {
                return o.count - count;
            } else {
                return index - o.index;
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append((char) ('A' + index));
            sb.append(" ");
            sb.append(count);
            return sb.toString();
        }
    }

    void solve() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Letter> letters = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            Letter l = new Letter();
            l.index = i;
            l.count = 0;
            letters.add(l);
        }
        for (int i = 0; i < n; i++) {
            String s = sc.nextLine();
            if (s.isEmpty()) {
                s = sc.nextLine();
            }
            for (int j = 0; j < s.length(); j++) {
                char ch = s.charAt(j);
                if (Character.isAlphabetic(ch)) {
                    char uch = Character.toUpperCase(ch);
                    Letter l = letters.get(uch - 'A');
                    l.count++;
                }
            }
        }
        Collections.sort(letters);
        for (Letter l : letters) {
            if (l.count > 0) {
                System.out.println(l.toString());
            }
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {
        FileInputStream inStream = null;
        PrintStream outStream = null;
        boolean isLocal = System.getProperty("os.name").equals("Mac OS X");
        if (isLocal) {
            inStream = new FileInputStream("1.in");
            // outStream = new PrintStream("1.out");
            System.setIn(inStream);
            // System.setOut(outStream);
        }

        new Main().solve();
    }
}
