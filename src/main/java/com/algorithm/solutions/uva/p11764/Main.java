package com.algorithm.solutions.uva.p11764;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
   
    void solve() {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int caseNum = 1;
        while (t > 0) {
            int n = sc.nextInt();
            int nums[] = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = sc.nextInt();
            }
            int high = 0;
            int low = 0;
            for (int i = 0; i < n - 1; i++) {
                if (nums[i] > nums[i + 1]) {
                    low++;
                } else if (nums[i] < nums[i + 1]) {
                    high++;
                }
            }
            System.out.println(String.format("Case %d: %d %d", caseNum, high, low));
            caseNum++;
            t--;
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
