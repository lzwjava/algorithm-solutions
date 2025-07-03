package com.lzw.solutions.uva.p105;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

    class Building {
        int l;
        int h;
        int r;
    }
   
    void solve() {
        Scanner sc = new Scanner(System.in);
        ArrayList<Building> bs = new ArrayList<>();
        HashSet<Integer> xs = new HashSet<>();
        while (sc.hasNextInt()) {
            Building b = new Building();
            b.l = sc.nextInt();
            b.h = sc.nextInt();
            b.r = sc.nextInt();
            bs.add(b);

            xs.add(b.l);
            xs.add(b.r);
        }
        
        ArrayList<Integer> ss = new ArrayList<>();
        ss.addAll(xs);
        Collections.sort(ss);
    
        ArrayList<Integer> heights = new ArrayList<>();
        for (int i = 0; i < ss.size(); i++) {
            heights.add(0);
        }

        for (int i = 0; i < bs.size(); i++) {
            Building b = bs.get(i);
            int leftIndex = Collections.binarySearch(ss, b.l);
            int rightIndex = Collections.binarySearch(ss, b.r);
            for (int j = leftIndex; j < rightIndex; j++) {
                int hj = heights.get(j);
                if (hj < b.h) {
                    heights.set(j, b.h);
                }
            }
        }
        int lastHeight = -1;
        for (int i = 0; i < ss.size(); i++) {
            if (heights.get(i) != lastHeight) {
                if (lastHeight != -1) {
                    System.out.print(" ");
                }
                lastHeight = heights.get(i);

                System.out.print(ss.get(i));

                System.out.print(" ");
                System.out.print(heights.get(i));
            }
        }
        System.out.println();

        sc.close();
    }

    public static void main(String[] args) throws Exception {
        FileInputStream inStream = null;
        PrintStream outStream = null;
        boolean isLocal = System.getProperty("os.name").equals("Mac OS X");        
        if (isLocal) {
            inStream = new FileInputStream("1.in");
            outStream = new PrintStream("1.out");
            System.setIn(inStream);
            System.setOut(outStream);
        }

        new Main().solve();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
