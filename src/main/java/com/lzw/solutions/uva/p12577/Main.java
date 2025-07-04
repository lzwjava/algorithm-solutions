package com.lzw.solutions.uva.p12577;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner sc = new Scanner(System.in);
        int caseNum = 1;
        while (true) {
            String s = sc.next();
            if (s.equals("*")) {
                break;
            }
            System.out.print(String.format("Case %d: ", caseNum));
            if (s.equals("Hajj")) {
                System.out.println("Hajj-e-Akbar");
            } else if (s.equals("Umrah")) {
                System.out.println("Hajj-e-Asghar");
            }
            caseNum++;
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {

        new Main().solve();
    }
}
