package com.lzw.solutions.uva.p10935;

import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

public class Main {

    void work() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int n = sc.nextInt();
            if (n == 0) {
                break;
            }
            ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(n);
            for (int i = 1; i <= n; i++) {
                queue.add(i);
            }
            System.out.print("Discarded cards:");
            boolean first = true;
            while (queue.size() > 1) {
                if (!first) {
                    System.out.print(", ");
                } else {
                    System.out.print(" ");
                    first = false;
                }
                System.out.print(queue.poll());
                Integer top = queue.poll();
                queue.add(top);
            }
            System.out.println();
            System.out.print("Remaining card: ");
            System.out.println(queue.poll());
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {

        new Main().work();
    }
}
