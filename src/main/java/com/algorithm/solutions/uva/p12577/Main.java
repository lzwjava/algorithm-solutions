package com.algorithm.solutions.uva.p12577;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
   
    void solve() {
        Scanner sc = new Scanner(System.in);
        int caseNum = 1;
        while (true) {
            String s = sc.next();
            if (s.equals("*")) {
                break;
            }
            System.out.print(String.format("Case %d: ", caseNum));
            if (s.equals("Hajj")) {
                System.out.println("Hajj-e-Akbar");
            } else if (s.equals("Umrah")) {
                System.out.println("Hajj-e-Asghar");
            }
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
