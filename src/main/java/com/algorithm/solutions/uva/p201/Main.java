package com.algorithm.solutions.uva.p201;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {

   
    void work() {
        Scanner sc = new Scanner(System.in);
        int problemNum = 1;
        while (sc.hasNextInt()) {
            if (problemNum > 1) {
                System.out.println();
                System.out.println("**********************************");
                System.out.println();
            }
            System.out.println(String.format("Problem #%d", problemNum));
            System.out.println();
            problemNum++;
            
            int n = sc.nextInt();
            int m = sc.nextInt();
            int hls[][] = new int[n][n];
            int vls[][] = new int[n][n];
            for (int k = 0; k < m; k++) {
                String dir = sc.next();
                int i = sc.nextInt() - 1;
                int j = sc.nextInt() - 1;
                char ch = dir.charAt(0);
                if (ch == 'H') {
                    hls[i][j] = 1;
                } else {
                    // V
                    vls[i][j] = 1;
                }
            }

            boolean found = false;
            for (int size = 1; size <= n - 1; size++) {
                int squares = 0;
                for (int i = 0; i < n - size; i++) {
                    for (int j = 0; j < n - size; j++) {
                        // start at (i, j), size 
                        // h i j, h i+1 j, v j i, v j+1, i

                        boolean ok = true;
                        // top
                        for (int k = 0; k < size; k++) {
                            if (hls[i][j + k] != 1) {
                                ok = false;
                                break;
                            }
                        }
                        if (!ok) {
                            continue;
                        }
                        // bottom
                        for (int k = 0; k < size; k++) {
                            if (hls[i + size][j + k] != 1) {
                                ok = false;
                                break;
                            }
                        }
                        if (!ok) {
                            continue;
                        }
                        // left
                        for (int k = 0; k < size; k++) {
                            if (vls[j][i + k] != 1) {
                                ok = false;
                                break;
                            }
                        }
                        if (!ok) {
                            continue;
                        }
                        for (int k = 0; k < size; k++) {
                            if (vls[j + size][i + k] != 1) {
                                ok = false;
                                break;
                            }
                        }
                        if (!ok) {
                            continue;
                        }
                        squares++;
                    }
                }
                if (squares > 0){
                    System.out.println(String.format("%d square (s) of size %d", squares, size));
                    found = true;
                }
            } // for size
            if (!found) {
                System.out.println("No completed squares can be found.");
            }
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {
        FileInputStream inStream = null;
        PrintStream outStream = null;
        boolean isLocal = System.getProperty("os.name").equals("Mac OS X");        
        if (isLocal) {
            inStream = new FileInputStream("2.in");
            outStream = new PrintStream("2.out");
            System.setIn(inStream);
            System.setOut(outStream);
        }

        new Main().work();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
