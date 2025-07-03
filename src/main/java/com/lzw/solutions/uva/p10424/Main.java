package com.lzw.solutions.uva.p10424;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {

    int sum(String s) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isAlphabetic(ch)) {
                int lch = Character.toLowerCase(ch);
                int v = lch - 'a' + 1;
                sum += v;
            }
        }
        return sum;
    }

    int merge(int s) {
        while (s >= 10) {
            int p = 0;
            while (s != 0) {
                p += s % 10;
                s /= 10;
            }
            s = p;
        }
        return s;
    }

    void solve() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String a = sc.nextLine();
            String b = sc.nextLine();
            int asum = sum(a);
            int bsum = sum(b);

            int am = merge(asum);
            int bm = merge(bsum);

            double r;
            if (am <= bm) {
                r = am * 100.0 / bm;
            } else {
                r = bm * 100.0 / am;
            }
            System.out.println(String.format("%.2f %%", r));
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
