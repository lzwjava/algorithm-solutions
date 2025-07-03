package com.lzw.solutions.uva.p11636;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {

    void solve() {
        Scanner sc = new Scanner(System.in);
        int caseNum = 1;
        while (true) {
            int n = sc.nextInt();
            if (n < 0) {
                break;
            }
            int cnt = 0;
            int lines = 1;
            while (lines < n) {
                lines *= 2;
                cnt++;
            }
            System.out.println(String.format("Case %d: %d", caseNum, cnt));
            caseNum++;
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
