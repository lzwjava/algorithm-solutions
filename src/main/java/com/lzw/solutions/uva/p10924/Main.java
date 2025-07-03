package com.lzw.solutions.uva.p10924;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    void solve() {
        ArrayList<Integer> primes = new ArrayList<>();
        primes.add(1);
        for (int i = 2; i <= 1100; i++) {
            boolean ok = true;
            int si = (int) Math.sqrt(i);
            for (int j = 1; j < primes.size(); j++) {
                int pj = primes.get(j);
                if (pj > si) {
                    break;
                }
                if (i % pj == 0) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                primes.add(i);
            }
        }
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String s = sc.next();
            int sum = 0;
            for (int i = 0; i < s.length(); i++) {
                int v = 0;
                char ch = s.charAt(i);
                if (Character.isUpperCase(ch)) {
                    v = (ch - 'A') + 27;
                } else if (Character.isLowerCase(ch)) {
                    v = (ch - 'a') + 1;
                }
                sum += v;
            }
            int index = Collections.binarySearch(primes, sum);
            if (index >= 0) {
                System.out.println("It is a prime word.");
            } else {
                System.out.println("It is not a prime word.");
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

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
