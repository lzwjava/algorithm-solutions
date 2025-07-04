package com.lzw.solutions.uva.p509;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {

    String binaryToHex(String binaryStr) {
        String hexStr = "";
        while (binaryStr.length() > 0) {
            String sub = binaryStr.substring(0, 4);
            int decimal = Integer.parseInt(sub, 2);
            String subHex = Integer.toString(decimal, 16).toUpperCase();
            hexStr += subHex;
            binaryStr = binaryStr.substring(4);
        }
        return hexStr;
    }

    String hexToBinary(String hexStr) {
        int decimal = Integer.parseInt(hexStr, 16);
        String binaryStr = Integer.toString(decimal, 2);
        return binaryStr;
    }

    void test() {
        System.out.println(hexToBinary("FFC"));
        System.out.println(binaryToHex("110000111100"));
    }

    void work() {
        Scanner sc = new Scanner(System.in);
        int n = 1;
        for (; ; ) {
            int d = sc.nextInt();
            if (d == 0) {
                break;
            }
            int s = sc.nextInt();
            int b = sc.nextInt();

            String parity = sc.next();
            int parityNum;
            if (parity.equals("E")) {
                parityNum = 0;
            } else {
                parityNum = 1;
            }

            String disk[] = new String[d];
            for (int i = 0; i < d; i++) {
                disk[i] = sc.next();
            }
            int len = disk[0].length();
            int rows = len / s;
            String diskTable[][] = new String[rows][d];
            for (int i = 0; i < d; i++) {
                for (int j = 0; j < rows; j++) {
                    diskTable[j][i] = disk[i].substring(j * s, (j + 1) * s);
                }
            }
            // for (int i = 0; i < rows; i++) {
            //     for (int j = 0; j < d; j++) {
            //         System.out.print(diskTable[i][j] + " ");
            //     }
            //     System.out.println();
            // }
            // System.out.println();

            // boolean broken = false;
            // for (int i = 0; i < rows; i++) {
            //     int count = 0;
            //     for (int j = 0; j < d; j++) {
            //         String str = diskTable[i][j];
            //         for (int k = 0; k < str.length(); k++) {
            //             if (str.charAt(k) == 'x') {
            //                 count++;
            //             }
            //         }
            //     }
            //     if (count >= 2) {
            //         broken = true;
            //         break;
            //     }
            // }

            boolean broken = false;
            for (int i = 0; i < rows; i++) {
                for (int k = 0; k < s; k++) {
                    int brokenCount = 0;
                    for (int j = 0; j < d; j++) {
                        if (diskTable[i][j].charAt(k) == 'x') {
                            brokenCount++;
                        }
                    }
                    if (brokenCount > 1) {
                        broken = true;
                        break;
                    } else if (brokenCount == 1) {
                        int xColumn = 0;
                        for (int j = 0; j < d; j++) {
                            if (diskTable[i][j].charAt(k) == 'x') {
                                xColumn = j;
                                break;
                            }
                        }
                        if (i % d == xColumn) {
                            // parity error
                            // broken = true;
                            // break;
                            continue;
                        } else {
                            int oneBit = -1;
                            int zeroBit = -1;
                            for (int m = 0; m < d; m++) {
                                int v1, v0;
                                if (m == xColumn) {
                                    v1 = 1;
                                    v0 = 0;
                                } else {
                                    int v = diskTable[i][m].charAt(k) - '0';
                                    v1 = v;
                                    v0 = v;
                                }
                                if (oneBit == -1) {
                                    oneBit = v1;
                                    zeroBit = v0;
                                } else {
                                    oneBit = oneBit ^ v1;
                                    zeroBit = zeroBit ^ v0;
                                }
                            }
                            int bitAtX = 0;
                            if (oneBit == parityNum) {
                                bitAtX = 1;
                            } else if (zeroBit == parityNum) {
                                bitAtX = 0;
                            } else {
                                assert (false);
                            }

                            String origin = diskTable[i][xColumn];

                            diskTable[i][xColumn] =
                                    origin.substring(0, k) + bitAtX + origin.substring(k + 1, origin.length());
                        }
                    } else {
                        int v = diskTable[i][0].charAt(k) - '0';
                        for (int m = 1; m < d; m++) {
                            int kv = diskTable[i][m].charAt(k) - '0';
                            v = v ^ kv;
                        }
                        if (v != parityNum) {
                            broken = true;
                            break;
                        }
                    }
                }
                if (broken) {
                    break;
                }
            }

            if (broken) {
                System.out.println(String.format("Disk set %d is invalid.", n));
                n++;
                continue;
            }

            String hex = "";
            String binary = "";
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < d; j++) {
                    if (i % d != j) {
                        binary += diskTable[i][j];
                        while (binary.length() > 4) {
                            String sub = binary.substring(0, 4);
                            hex += binaryToHex(sub);
                            binary = binary.substring(4);
                        }
                    }
                }
            }
            if (binary.length() > 0) {
                while (binary.length() < 4) {
                    binary += "0";
                }
                hex += binaryToHex(binary);
            }

            System.out.println(String.format("Disk set %d is valid, contents are: %s", n, hex));
            n++;
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
    }
}
