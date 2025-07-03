package com.algorithm.solutions.uva.p1586;

import java.io.FileInputStream;
import java.util.Scanner;

public class Main {
    private static int indexOfAtom(char ch) {
        if (ch == 'C') {
            return 0;
        } else if (ch == 'H') {
            return 1;
        } else if (ch == 'O') {
            return 2;
        } else if (ch == 'N') {
            return 3;
        }
        return 0;
    }

    public static void main(String[] args) throws Exception {
        FileInputStream inStream = null;
        boolean isLocal = System.getProperty("os.name").equals("Mac OS X");
        if (isLocal) {
            inStream = new FileInputStream("1.in");
            System.setIn(inStream);
        }
        double weight[] = new double[]{12.01, 1.008, 16.00, 14.01}; 

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            String s = sc.next();
            int cnts[] = new int[4];
            char lastCh = 'a';
            String lastNum = "";
            boolean handleLast = true;
            for (int j = 0; j < s.length(); j++) {
                char ch = s.charAt(j);
                if (!Character.isDigit(ch)) {
                    if (!handleLast) {
                        if (lastNum.isEmpty()) {
                            cnts[indexOfAtom(lastCh)]++;
                        } else {
                            int num = Integer.parseInt(lastNum);
                            cnts[indexOfAtom(lastCh)] += num;
                        }
                        lastNum = "";
                        lastCh = 'a';
                    }
                    lastCh = ch;
                    handleLast = false;
                } else {
                    // digit
                    lastNum += ch;
                }
            }
            if (!handleLast) {
                if (lastNum.isEmpty()) {
                    cnts[indexOfAtom(lastCh)]++;
                } else {
                    int num = Integer.parseInt(lastNum);
                    cnts[indexOfAtom(lastCh)] += num;
                }                
            }
            double mass = 0;
            for (int j = 0; j < 4; j++) {
                // System.out.println(cnts[j]);
                mass += weight[j] * cnts[j];
            }
            System.out.println(String.format("%.3f", mass));
        }
        sc.close();

        if (isLocal) {
            inStream.close();
        }
    }
}
