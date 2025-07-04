package com.lzw.solutions.uva.p10696;

import java.util.Scanner;

public class Main {

    int f91(int n) {
        if (n <= 100) {
            return f91(f91(n + 11));
        } else {
            return n - 10;
        }
    }

    void work() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int n = sc.nextInt();
            if (n == 0) {
                break;
            }
            System.out.println(String.format("f91(%d) = %d", n, f91(n)));
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {

        new Main().work();
    }
}
