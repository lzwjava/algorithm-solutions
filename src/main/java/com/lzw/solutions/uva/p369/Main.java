package com.lzw.solutions.uva.p369;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
   
    void solve() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            long n = sc.nextInt();
            long m = sc.nextInt();
            if (n == 0 && m == 0) {
                break;
            }
            long c = 1;
            long k = n - m;
            System.out.print(String.format("%d things taken %d at a time is ", n,m));            
            while (true) {
                if (n == 1 && m == 1 && (k==1 || k==0)) {
                    break;
                }
                if (n > 1) {
                    c *= n;
                    n--;               
                }
                while (c % m == 0 && m > 1) {
                    c /= m;
                    m--;
                }
                while (k != 0 && c % k == 0 && k > 1) {
                    c /= k;
                    k--;
                }
            }
            System.out.println(String.format("%d exactly.", c));
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
