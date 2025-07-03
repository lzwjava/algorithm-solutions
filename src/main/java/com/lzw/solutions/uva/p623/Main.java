package com.lzw.solutions.uva.p623;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
   
    void solve() {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> nums = new ArrayList<>();
        int maxNum = 0;
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            nums.add(n);
            if (n > maxNum) {
                maxNum = n;
            }
        }
        sc.close();
        
        ArrayList<BigInteger> vs = new ArrayList<>();
        vs.add(BigInteger.valueOf(1));
        vs.add(BigInteger.valueOf(1));        
        for (int i = 2; i <= maxNum; i++) {
            BigInteger bi = vs.get(i - 1).multiply(BigInteger.valueOf(i));
            vs.add(bi);
        }
        for (int i=0;i<nums.size();i++) {
            int num = nums.get(i);
            System.out.println(String.format("%d!", num));
            System.out.println(vs.get(num).toString(10));
        }
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
