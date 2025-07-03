package com.lzw.solutions.uva.p10954;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    void solve() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int n = sc.nextInt();
            if (n == 0) {
                break;
            }
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for (int i = 0; i < n; i++) {
                int num = sc.nextInt();
                pq.add(num);
            }
            long cost = 0;
            while (pq.size() >= 2) {
                int a = pq.poll();
                int b = pq.poll();
                int s = a + b;
                cost += s;
                pq.add(s);
            }
            System.out.println(cost);
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
