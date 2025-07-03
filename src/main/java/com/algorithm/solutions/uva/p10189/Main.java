package com.algorithm.solutions.uva.p10189;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {

    int dx[] = { -1, -1, -1, 0, 0, 1, 1, 1 };
    int dy[] = { -1, 0, 1, -1, 1, -1, 0, 1 };
   
    void work() {
        Scanner sc = new Scanner(System.in);
        int fieldNum = 1;
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            if (n == 0 && m == 0) {
                break;
            }
            char field[][] = new char[n][m];
            for (int i = 0; i < n; i++) {
                String s = sc.next();
                assert (s.length() == m);
                for (int j = 0; j < s.length(); j++) {
                    field[i][j] = s.charAt(j);
                }
            }

            if (fieldNum > 1) {
                System.out.println();
            }

            System.out.println(String.format("Field #%d:", fieldNum));

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (field[i][j] == '.') {
                        int cnt = 0;
                        for (int d = 0; d < dx.length; d++) {
                            int ni = i + dx[d];
                            int nj = j + dy[d];
                            if (ni >= 0 && ni < n && nj >= 0 && nj < m) {
                                if (field[ni][nj] == '*') {
                                    cnt++;
                                }
                            }
                        }
                        field[i][j] = (char) ('0' + cnt);
                    }
                    System.out.print(field[i][j]);
                }
                System.out.println();
            }

            fieldNum++;
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

        new Main().work();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
