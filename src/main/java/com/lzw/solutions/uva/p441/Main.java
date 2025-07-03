package com.lzw.solutions.uva.p441;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {

    void permutation(int nums[], boolean selected[], int i) {
        int count = 0;
        for (int j = 0; j < selected.length; j++) {
            if (selected[j]) {
                count++;
            }
        }
        int rest = nums.length - i;
        if (count + rest < 6) {
            return;
        }
        if (i == nums.length) {
            if (count == 6) {
                boolean first = true;
                for (int j = 0; j < selected.length; j++) {
                    if (selected[j]) {
                        if (!first) {
                            System.out.print(" ");
                        } else {
                            first = false;
                        }
                        System.out.print(nums[j]);
                    }
                }
                System.out.println();
            }
            return;
        }
        selected[i] = true;
        permutation(nums, selected, i + 1);

        selected[i] = false;
        permutation(nums, selected, i + 1);
    }

    void solve() {
        Scanner sc = new Scanner(System.in);
        boolean first = true;
        while (true) {
            int k = sc.nextInt();
            if (k == 0) {
                break;
            }

            int nums[] = new int[k];
            for (int i = 0; i < k; i++) {
                nums[i] = sc.nextInt();
            }
            boolean selected[] = new boolean[k];
            if (!first) {
                System.out.println();
            }
            first = false;

            permutation(nums, selected, 0);
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
