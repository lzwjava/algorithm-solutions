package com.lintcode;

import com.lintcode.ZeroEvenOdd;

import java.util.function.IntConsumer;

class Main {
    public static void main(String[] args) {
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(
            Integer.parseInt(args[0])
        );

        IntConsumer printZero = (int x) -> {
            try {
                if (x != 0) {
                    throw new Exception("passed x != 0 to printZero in thread for printing zeroes.");
                }
                System.out.print(x);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        };
        IntConsumer printEven = (int x) -> {
            try {
                if (x % 2 != 0) {
                    throw new Exception("passed odd number to printEven in thread for printing even numbers.");
                }
                System.out.print(x);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        };
        IntConsumer printOdd = (int x) -> {
            try {
                if (x % 2 != 1) {
                    throw new Exception("passed even number to printOdd in thread for printing odd numbers.");
                }
                System.out.print(x);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        };

        new Thread(() -> {
            try {
                zeroEvenOdd.zero(printZero);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                zeroEvenOdd.even(printEven);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                zeroEvenOdd.odd(printOdd);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
