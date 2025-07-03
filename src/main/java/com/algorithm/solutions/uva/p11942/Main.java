package com.algorithm.solutions.uva.p11942;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
   
    void solve() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println("Lumberjacks:");
        while (n > 0) {
            int lastNum= 0;
            int type = -1;  // 0: shortToLong 1:longToShort
            boolean ok = true;
            for (int i = 0; i < 10; i++) {
                if (i == 0) {
                    lastNum = sc.nextInt();
                } else {
                    int num = sc.nextInt();
                    if (!ok) {
                        continue;
                    }
                    if (type == -1) {
                        if (num > lastNum) {
                            type = 0;
                        } else {
                            type = 1;
                        }
                    } else {
                        if ((type == 0 && !(num > lastNum)) || (type == 1 && !(num < lastNum))) {
                            ok = false;
                        }
                    }
                    lastNum = num;
                }
            }
            if (ok) {
                System.out.println("Ordered");
            } else {
                System.out.println("Unordered");                
            }
            n--;
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
