package com.algorithm.solutions.uva.p1592j;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Scanner;

public class Main2 {

   
    void work() {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextInt()) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            String table[][] = new String[n][m];
            for (int i = 0; i < n; i++) {
                String s = sc.nextLine();
                if (s.isEmpty()) {
                    s = sc.nextLine();
                }
                String strs[] = s.split(",");
                assert (strs.length == m);
                table[i] = strs;
            }
            boolean found = false;
            int c1 = 0, c2 = 0;
            for (c1 = 0; c1 < m - 1; c1++) {            
                for (c2 = c1 + 1; c2 < m; c2++) {
                    HashMap<String, Integer> map = new HashMap<>();
                    for (int r = 0; r < n; r++) {
                        String str = table[r][c1] + "," + table[r][c2];
                        Integer prev = map.get(str);
                        if (prev != null) {
                            found = true;
                            System.out.println("NO");
                            System.out.println(String.format("%d %d", prev + 1, r + 1));
                            System.out.println(String.format("%d %d", c1 + 1, c2 + 1));
                            break;
                        } else {
                            map.put(str, r);
                        }
                    }
                    if (found) {
                        break;
                    }
                }
                if (found) {
                    break;
                }
            }       
            if (!found) {
                System.out.println("YES");                             
            }
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
