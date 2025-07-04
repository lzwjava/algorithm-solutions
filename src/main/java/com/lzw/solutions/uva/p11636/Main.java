package com.lzw.solutions.uva.p11636;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner sc = new Scanner(System.in);
        int caseNum = 1;
        while (true) {
            int n = sc.nextInt();
            if (n < 0) {
                break;
            }
            int cnt = 0;
            int lines = 1;
            while (lines < n) {
                lines *= 2;
                cnt++;
            }
            System.out.println(String.format("Case %d: %d", caseNum, cnt));
            caseNum++;
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {

        new Main().solve();
    }
}
