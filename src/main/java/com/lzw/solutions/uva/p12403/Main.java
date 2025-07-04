package com.lzw.solutions.uva.p12403;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        sc.nextLine();
        int sum = 0;
        while (t > 0) {
            String op = sc.nextLine();
            if (op.startsWith("donate")) {
                String[] strs = op.split(" ");
                sum += Integer.parseInt(strs[1]);
            } else if (op.startsWith("report")) {
                System.out.println(sum);
            }
            t--;
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {

        new Main().solve();
    }
}
