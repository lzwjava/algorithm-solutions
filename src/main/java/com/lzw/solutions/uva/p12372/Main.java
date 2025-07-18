package com.lzw.solutions.uva.p12372;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int caseNum = 1;
        while (t > 0) {
            int l = sc.nextInt();
            int w = sc.nextInt();
            int h = sc.nextInt();
            System.out.print(String.format("Case %d: ", caseNum));
            if (l <= 20 && w <= 20 && h <= 20) {
                System.out.println("good");
            } else {
                System.out.println("bad");
            }
            t--;
            caseNum++;
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {

        new Main().solve();
    }
}
