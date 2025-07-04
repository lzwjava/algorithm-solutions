package com.lzw.solutions.uva.p624;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    int maxDuration = 0;
    boolean maxSelected[];

    void permutation(int nums[], boolean selected[], int i, int n) {
        int duration = 0;
        for (int j = 0; j < selected.length; j++) {
            if (selected[j]) {
                duration += nums[j];
            }
        }
        if (duration > n) {
            return;
        }
        if (i == selected.length) {
            if (duration <= n && duration > maxDuration) {
                maxDuration = duration;
                maxSelected = Arrays.copyOf(selected, selected.length);
            }
            return;
        }
        selected[i] = true;
        permutation(nums, selected, i + 1, n);
        selected[i] = false;
        permutation(nums, selected, i + 1, n);
    }

    void solve() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            int track = sc.nextInt();
            int nums[] = new int[track];
            for (int i = 0; i < track; i++) {
                nums[i] = sc.nextInt();
            }
            boolean selected[] = new boolean[track];
            maxDuration = 0;
            permutation(nums, selected, 0, n);
            for (int i = 0; i < maxSelected.length; i++) {
                if (maxSelected[i]) {
                    System.out.print(nums[i]);
                    System.out.print(" ");
                }
            }
            System.out.print("sum:");
            System.out.println(maxDuration);
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
    }
}
