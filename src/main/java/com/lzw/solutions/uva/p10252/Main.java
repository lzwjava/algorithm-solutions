package com.lzw.solutions.uva.p10252;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    HashMap<Character, Integer> cal(String a) {
        HashMap<Character, Integer> chs = new HashMap<>();
        for (int i = 0; i < a.length(); i++) {
            char ch = a.charAt(i);
            Integer count = chs.get(ch);
            if (count == null) {
                count = 0;
            }
            count++;
            chs.put(ch, count);
        }
        return chs;
    }

    void solve() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String a = sc.nextLine();
            String b = sc.nextLine();

            HashMap<Character, Integer> achs = cal(a);
            HashMap<Character, Integer> bchs = cal(b);

            ArrayList<Character> list = new ArrayList<>();
            for (Character ch : achs.keySet()) {
                if (bchs.containsKey(ch)) {
                    Integer an = achs.get(ch);
                    Integer bn = bchs.get(ch);
                    Integer n = an < bn ? an : bn;
                    for (int i = 0; i < n; i++) {
                        list.add(ch);
                    }
                }
            }

            Collections.sort(list);

            for (int i = 0; i < list.size(); i++) {
                System.out.print(list.get(i));
            }
            System.out.println();
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
