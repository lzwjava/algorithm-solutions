package com.lzw.solutions.uva.p11498;

import java.util.Scanner;

public class Main {

    void work() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int k = sc.nextInt();
            if (k == 0) {
                break;
            }
            int n = sc.nextInt();
            int m = sc.nextInt();
            for (int i = 0; i < k; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();

                int nx = x - n;
                int ny = y - m;
                if (nx == 0 || ny == 0) {
                    System.out.println("divisa");
                } else if (nx > 0 && ny > 0) {
                    System.out.println("NE");
                } else if (nx > 0 && ny < 0) {
                    System.out.println("SE");
                } else if (nx < 0 && ny > 0) {
                    System.out.println("NO");
                } else {
                    System.out.println("SO");
                }
            }
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {

        new Main().work();
    }
}
