package com.lzw.solutions.uva.p232;

import java.io.FileInputStream;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        FileInputStream inStream = null;
        boolean isLocal = System.getProperty("os.name").equals("Mac OS X");
        if (isLocal) {
            inStream = new FileInputStream("1.in");
            System.setIn(inStream);
        }
        Scanner sc = new Scanner(System.in);
        int n = 1;
        for (;;) {
            String lines[] = new String[5];            
            lines[0] = sc.nextLine();
            if (lines[0].equals("Z")) {
                break;
            }
            char grid[][] = new char[5][5];
            for (int i = 1; i <= 4; i++) {
                lines[i] = sc.nextLine();                
            }
            int xe = 0, ye = 0;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    grid[i][j] = lines[i].charAt(j);
                    if (grid[i][j] == ' ') {
                        xe = i;
                        ye = j;
                    }
                }
            }
            boolean ok = true;
            boolean end = false; 
            for (;;) {
                if (end) {
                    break;
                }
                String move = sc.nextLine();
                // System.out.println(move);
                for (int i = 0; i < move.length(); i++) {
                    int xs = xe, ys = ye;
                    char ch = move.charAt(i);
                    if (ch == 'A') {
                        xs = xe - 1;
                    } else if (ch == 'R') {
                        ys = ye + 1;
                    } else if (ch == 'B') {
                        xs = xe + 1;
                    } else if (ch == 'L') {
                        ys = ye - 1;
                    } else if (ch == '0') {
                        // end
                        end = true;
                        break;
                    }
                    if (xs < 0 || xs >= 5 || ys < 0 || ys >= 5) {
                        ok = false;
                    }
                    if (!ok) {
                        continue;
                    }
                    // System.out.println(ch);
                    // System.out.println("e "+ xe + " " + ye);
                    // System.out.println("s " + xs + " " + ys);
                    char sch = grid[xe][ye];
                    grid[xe][ye] = grid[xs][ys];
                    grid[xs][ys] = sch;
                    xe = xs;
                    ye = ys;
                }
            }
            
            if (n != 1) {
                System.out.println();
            }
            
            System.out.println(String.format("Puzzle #%d:", n));
            if (ok) {                
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        if (j != 0) {
                            System.out.print(" ");
                        }
                        System.out.print(grid[i][j]);
                    }
                    System.out.println();
                }
            } else {
                System.out.println("This puzzle has no final configuration.");
            }
            n++;
        }                        
        sc.close();

        if (isLocal) {
            inStream.close();
        }
    }
}
