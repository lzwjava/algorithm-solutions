package com.lzw.solutions.uva.p1593;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    void work() {
        Scanner sc = new Scanner(System.in);
        ArrayList<ArrayList<String>> list = new ArrayList<>();
        char space = ' ';
        int maxWords = 0;
        while (sc.hasNextLine()) {
            ArrayList<String> strs = new ArrayList<>();
            String line = sc.nextLine();
            if (line.isEmpty()) {
                break;
            }
            String s = "";
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) != space) {
                    s += line.charAt(i);
                } else {
                    if (s.isEmpty()) {
                        continue;
                    } else {
                        strs.add(s);
                        s = "";
                    }
                }
            }
            if (!s.isEmpty()) {
                strs.add(s);
            }
            list.add(strs);
            if (maxWords < strs.size()) {
                maxWords = strs.size();
            }
        }
        int n = list.size();

        ArrayList<Integer> maxLengths = new ArrayList<>();
        for (int i = 0; i < maxWords; i++) {
            int len = 0;
            for (int j = 0; j < n; j++) {
                ArrayList<String> strs = list.get(j);
                if (strs.size() > i) {
                    String s = strs.get(i);
                    if (s.length() > len) {
                        len = s.length();
                    }
                }
            }
            maxLengths.add(len);
        }
        for (int i = 0; i < n; i++) {
            ArrayList<String> strs = list.get(i);
            for (int j = 0; j < strs.size(); j++) {
                String word = strs.get(j);
                System.out.print(word);
                int maxLen = maxLengths.get(j);
                if (j != strs.size() - 1) {
                    for (int k = word.length(); k < maxLen + 1; k++) {
                        System.out.print(space);
                    }
                }
            }
            System.out.println();
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {

        new Main().work();
    }
}
