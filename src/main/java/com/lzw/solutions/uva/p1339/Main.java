package com.lzw.solutions.uva.p1339;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    private static void work() {
        Scanner sc = new Scanner(System.in);

        for (; ; ) {
            if (!sc.hasNext()) {
                break;
            }
            String encrypted = sc.next();
            String origin = sc.next();

            char[] encryptedChars = encrypted.toCharArray();
            int[] encryptedNums = new int[26];
            for (char ch : encryptedChars) {
                encryptedNums[ch - 'A']++;
            }
            ArrayList<Integer> encryptedList = new ArrayList<>();
            for (int num : encryptedNums) {
                if (num > 0) {
                    encryptedList.add(num);
                }
            }

            char[] originChars = origin.toCharArray();
            int[] originNums = new int[26];
            for (char ch : originChars) {
                originNums[ch - 'A']++;
            }
            ArrayList<Integer> originList = new ArrayList<>();
            for (int num : originNums) {
                if (num > 0) {
                    originList.add(num);
                }
            }

            Collections.sort(encryptedList);
            Collections.sort(originList);
            // System.out.println(encryptedList);
            // System.out.println(originList);

            if (originList.equals(encryptedList)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
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

        work();

        if (isLocal) {
            inStream.close();
            outStream.close();
        }
    }
}
