package com.lzw.solutions.uva.p499;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    void solve() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            HashMap<Character, Integer> chars = new HashMap<>();
            int maxCount = 0;
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (Character.isAlphabetic(ch)) {
                    Character character = Character.valueOf(ch);
                    Integer count = chars.get(character);
                    if (count == null) {
                        count = 0;
                    }
                    count++;
                    chars.put(character, count);
                    if (count > maxCount) {
                        maxCount = count;
                    }
                }
            }
            ArrayList<Character> list = new ArrayList<>();
            for (Character ch : chars.keySet()) {
                Integer count = chars.get(ch);
                if (count == maxCount) {
                    list.add(ch);
                }
            }
            list.sort(new Comparator<Character>() {
                @Override
                public int compare(Character o1, Character o2) {
                    boolean u1 = Character.isUpperCase(o1);
                    boolean u2 = Character.isUpperCase(o2);
                    if (u1 && u2 || (!u1 && !u2)) {
                        return o1.charValue() - o2.charValue();
                    } else if (u1) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
            });
            for (Character ch : list) {
                System.out.print(ch);
            }
            System.out.print(" ");
            System.out.println(maxCount);
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

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
