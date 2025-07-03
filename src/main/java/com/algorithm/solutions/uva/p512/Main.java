package com.algorithm.solutions.uva.p512;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    boolean contains(int[] array, int key) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == key) {
                return true;
            }
        }
        return false;
    }
    
    void print(int curNums[][]) {
        for (int i = 0; i < curNums.length; i++) {
            for (int j = 0; j < curNums[i].length; j++) {
                System.out.printf("%2d ", curNums[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
    
    void work() {
        Scanner sc = new Scanner(System.in);
        int sn = 1;
        for (;;) {
            int r, c;
            r = sc.nextInt();
            c = sc.nextInt();
            if (r == 0 && c == 0) {
                break;
            }
            int[][] nums = new int[r][c];
            int p =0;
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    nums[i][j] = p;
                    p++;
                }
            }            
            int n = sc.nextInt();

            // print(nums);

            int curNums[][] = new int[r][c];
            int curR = r;
            int curC = c;
            for (int i = 0; i < r; i++) {
                curNums[i] = Arrays.copyOf(nums[i], nums[i].length);
            }
            // print(nums);

            for (int i = 0; i < n; i++) {
                String command = sc.next();
                int cnt = 0;
                int rcs[] = {};
                int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
                if (!command.equals("EX")) {
                    cnt = sc.nextInt();
                    rcs = new int[cnt];
                    for (int j = 0; j < cnt; j++) {
                        rcs[j] = sc.nextInt();
                    }
                } else {
                    x1 = sc.nextInt() - 1;
                    y1 = sc.nextInt() - 1;
                    x2 = sc.nextInt() - 1;
                    y2 = sc.nextInt() - 1;
                }

                int nr = 0, nc = 0;
                switch (command) {
                    case "DR":
                        nr = curR - cnt;
                        nc = curC;
                        break;
                    case "DC":
                        nr = curR;
                        nc = curC - cnt;
                        break;
                    case "IC":
                        nr = curR;
                        nc = curC + cnt;
                        break;
                    case "IR":
                        nr = curR + cnt;
                        nc = curC;
                        break;
                    case "EX":
                        nr = curR;
                        nc = curC;
                        break;
                    default:
                        break;
                }

                int nNums[][] = new int[nr][nc];
                switch (command) {
                    case "DR":
                        int np = 0;
                        for (int j = 0; j < curR; j++) {
                            if (contains(rcs, j + 1)) {
                                continue;
                            }
                            for (int k = 0; k < curC; k++) {
                                nNums[np][k] = curNums[j][k];
                            }
                            np++;
                        }
                        break;
                    case "DC":
                        int cp = 0;
                        for (int k = 0; k < curC; k++) {
                            if (contains(rcs, k + 1)) {
                                continue;
                            }
                            for (int j = 0; j < curR; j++) {
                                nNums[j][cp] = curNums[j][k];
                            }
                            cp++;
                        }
                        break;
                    case "IC":
                        int ncp = 0;
                        for (int k = 0; k < curC; k++) {
                            if (contains(rcs, k + 1)) {
                                for (int j = 0; j < curR; j++) {
                                    nNums[j][ncp] = p;
                                    p++;
                                }
                                ncp++;
                            }
                            for (int j = 0; j < curR; j++) {
                                nNums[j][ncp] = curNums[j][k];
                            }
                            ncp++;
                        }
                        if (contains(rcs, curC + 1)) {
                            for (int j = 0; j < curR; j++) {
                                nNums[j][ncp] = p;
                                p++;
                            }
                            ncp++;
                        }
                        break;
                    case "IR":
                        int rcp = 0;
                        for (int j = 0; j < curR; j++) {
                            if (contains(rcs, j + 1)) {
                                for (int k = 0; k < curC; k++) {
                                    nNums[rcp][k] = p;
                                    p++;
                                }
                                rcp++;
                            }
                            for (int k = 0; k < curC; k++) {
                                nNums[rcp][k] = curNums[j][k];
                            }
                            rcp++;
                        }
                        if (contains(rcs, curR + 1)) {
                            for (int k = 0; k < curC; k++) {
                                nNums[rcp][k] = p;
                                p++;
                            }
                            rcp++;
                        }
                        break;
                    case "EX":
                        nNums = curNums.clone();
                        int t = nNums[x1][y1];
                        nNums[x1][y1] = nNums[x2][y2];
                        nNums[x2][y2] = t;
                        break;
                    default:
                        break;
                } // switch
                
                curNums = nNums;
                curR = nr;
                curC = nc;
                // print(curNums);
            } // for            

            if (sn > 1) {
                System.out.println();
            }
            System.out.println(String.format("Spreadsheet #%d", sn));
            int qn = sc.nextInt();
            for (int i = 0; i < qn; i++) {
                int qr = sc.nextInt();
                int qc = sc.nextInt();
                
                int v = nums[qr-1][qc-1];
                boolean found = false;
                int nr = 0, nc = 0;
                // System.out.println(v);
                for (int j = 0; j < curR; j++) {
                    for (int k = 0; k < curC; k++) {
                        if (v == curNums[j][k]) {
                            nr = j;
                            nc = k;
                            found = true;
                            break;
                        }
                    }
                }
                if (found) {
                    System.out.println(String.format("Cell data in (%d,%d) moved to (%d,%d)", 
                    qr, qc, nr+1,nc+1));
                } else {
                    System.out.println(String.format("Cell data in (%d,%d) GONE", qr,qc));
                }
            }
            sn++;
        }
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

        new Main().work();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
