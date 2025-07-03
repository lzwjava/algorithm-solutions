package com.lzw.solutions.uva.p264;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
   
    void solve() {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i < 4472; i++) {
            list.add(i * (i + 1) / 2);
        }

        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            int s = (int) Math.sqrt(n * 2);
            int sum1 = s * (s + 1) / 2;
            int sum2 = (s + 1) * (s + 2) / 2;
            if (sum1 < n && sum2 >= n) {
                s = s + 1;
            }
            int sum = s * (s + 1) / 2;
            assert (n <= sum);

            int os = s - 1;
            int osSum = os * (os + 1) / 2;
            
            int x = n - osSum;
            int i, j;
            if (s % 2 == 0) {
                // top to down
                i = x;
                j = s-(x-1);
            } else {
                // down to top
                i = s - (x - 1);
                j = x;
            }            
            System.out.println(String.format("TERM %d IS %d/%d", n, i, j));
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
