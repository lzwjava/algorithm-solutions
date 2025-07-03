package com.lzw.solutions.uva.p11559;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {

    class Hotel {
        int p;
        int ws[];

        Hotel() {
            
        }
    }

    void solve() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int n, b, h, w;
            n = sc.nextInt();
            b = sc.nextInt();
            h = sc.nextInt();
            w = sc.nextInt();
            Hotel hs[] = new Hotel[h];
            for (int i = 0; i < h; i++) {
                int p = sc.nextInt();
                int ws[] = new int[w];
                for (int j = 0; j < ws.length; j++) {
                    ws[j] = sc.nextInt();
                }
                Hotel hotel = new Hotel();
                hotel.p = p;
                hotel.ws = ws;
                hs[i] = hotel;
            }
            int minCost = Integer.MAX_VALUE;
            for (int i = 0; i < w; i++) {
                for (int j = 0; j < h; j++) {
                    // week i, hotel j
                    if (hs[j].ws[i] >= n) {
                        // enought bed 
                        int cost = n * hs[j].p;
                        if (cost < minCost) {
                            minCost = cost;
                        }
                    }
                }
            }
            if (minCost == Integer.MAX_VALUE || minCost >b) {
                System.out.println("stay home");
            } else {
                System.out.println(minCost);
            }
        }
        sc.close();;
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
