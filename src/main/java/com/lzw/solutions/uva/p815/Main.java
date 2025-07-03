package com.lzw.solutions.uva.p815;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

   
    void work() {
        Scanner sc = new Scanner(System.in);
        int region = 1;
        for (;;) {
            int m = sc.nextInt();
            int n = sc.nextInt();
            if (m == 0 && n == 0) {
                break;
            }
            int total = m * n;
            int elevations[] = new int[total];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int e = sc.nextInt();        
                    elevations[i * n + j] = e;
                }
            }
            double water = sc.nextInt() * 1.0;
            Arrays.sort(elevations);
            int submergeCount = 0;
            double h = elevations[0];
            while (water > 0) {
                int nextSubmergeCount = submergeCount + 1;                
                if (nextSubmergeCount >= total) {
                    h += water / (m * n * 100);
                    water = 0;
                    submergeCount = nextSubmergeCount;
                } else {
                    int e = elevations[submergeCount + 1] - elevations[submergeCount];
                    if (e * nextSubmergeCount * 100 >= water) {
                        // done
                        h += water / (nextSubmergeCount * 100);
                        water = 0;
                        submergeCount = nextSubmergeCount;
                    } else {
                        // submerge
                        h += e;
                        water -= e * nextSubmergeCount * 100;
                        submergeCount = nextSubmergeCount;
                    }
                }
            }
            System.out.println(String.format("Region %d", region));
            System.out.println(String.format("Water level is %.2f meters.", h));
            System.out.println(String.format("%.2f percent of the region is under water.", submergeCount * 100.0 / total));
            System.out.println();
            region++;            
        }        
        sc.close();
    }

    public static void main(String[] args) throws Exception {
        FileInputStream inStream = null;
        PrintStream outStream = null;
        boolean isLocal = System.getProperty("os.name").equals("Mac OS X");        
        if (isLocal) {
            inStream = new FileInputStream("2.in");
            // outStream = new PrintStream("1.out");
            System.setIn(inStream);
            // System.setOut(outStream);
        }

        new Main().work();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
