package com.algorithm.solutions.uva.p357;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main1 {

    ArrayList<Integer[]> answer;

    boolean contains(Integer[] cnts) {
        for (Integer[] list : answer) {
            boolean equal = true;
            for (int i = 0; i < cnts.length; i++) {
                if (!cnts[i].equals(list[i])) {
                    equal = false;
                    break;
                }
            }
            if (equal) {
                return true;
            }
        }
        return false;
    }
    
    void calWays(int units[], Integer[] cnts, int cents) {
        if (cents == 0) {
            if (!contains(cnts)) {
                Integer[] ncnts = Arrays.copyOf(cnts, cnts.length);
                answer.add(ncnts);
            }
            return;
        }
        // 5 cents, 2 ways
        for (int i = 0; i < units.length; i++) {
            if (cents >= units[i]) {
                cnts[i]++;
                calWays(units, cnts, cents - units[i]);
                cnts[i]--;
            } else {
                break;
            }
        }
    }
   
    void solve() {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextInt()) {
            int n = sc.nextInt();            
            int units[] = new int[] { 1, 5, 10, 25, 50 };
            answer = new ArrayList<>();
            Integer[] cnts = new Integer[units.length];
            for (int i = 0; i < units.length; i++) {
                cnts[i] = 0;
            }
            calWays(units, cnts, n);
            String part = "";
            if (answer.size() > 1) {
                part = String.format("There are %d ways", answer.size());
            } else {
                part = "There is only 1 way";
            }
            System.out.println(String.format("%s to produce %d cents change.", part, n));
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
