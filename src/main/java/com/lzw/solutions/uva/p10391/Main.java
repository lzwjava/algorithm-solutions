package com.lzw.solutions.uva.p10391;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    int binarySearch(ArrayList<String> strs, String target) {
        int end = strs.size();
        int start = 0;
        // [start,end)
        while (start < end) {
            int mid = (start + end) / 2;
            if (strs.get(mid).equals(target)) {
                return mid;
            } else {
                String midStr = strs.get(mid);
                if (midStr.compareTo(target) < 0) {
                    start = mid+1;
                } else {
                    end = mid;
                }
            }
        }
        return -1;
    }
   
    void work() {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> words = new ArrayList<>();
        while (sc.hasNext()) {
            words.add(sc.next());
        }
        Collections.sort(words);

        for (String word : words) {
            for (int m = 1; m <= word.length() - 1; m++) {
                String part1 = word.substring(0, m);
                String part2 = word.substring(m);
                if (binarySearch(words,part1) != -1 && binarySearch(words, part2) != -1) {
                    System.out.println(word);
                    break;
                }
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

        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        // System.out.println(new Main().binarySearch(list, "b"));

        new Main().work();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
