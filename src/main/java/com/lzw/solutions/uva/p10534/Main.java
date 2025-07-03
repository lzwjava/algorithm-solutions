package com.lzw.solutions.uva.p10534;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    PrintWriter out;

    Main() {
        out = new PrintWriter(System.out);
    }

    int n;
    int[] nums;

    void calLIS(int[] nums, int[] lis) {
        int n = nums.length;
        int[] top = new int[n];
        int piles = 0;
        for (int i = 0; i < n; i++) {
            int poker = nums[i];

            int left = Arrays.binarySearch(top, 0, piles, poker);
            if (left < 0) {
                left = -(left + 1);
            }
            if (left == piles) {
                piles++;
            }
            top[left] = poker;
            lis[i] = left + 1;
        }
    }

    void reverse(int[] as) {
        int an = as.length;
        for (int i = 0; i < an / 2; i++) {
            int t = as[i];
            as[i] = as[an - 1 - i];
            as[an - 1 - i] = t;
        }
    }

    void solve() throws IOException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            if (!sc.hasNextInt()) {
                break;
            }
            n = sc.nextInt();
            nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = sc.nextInt();
            }
            int[] lis = new int[n];
            calLIS(nums, lis);
            reverse(nums);
            int[] lds = new int[n];
            calLIS(nums, lds);
            reverse(lds);
            reverse(nums);

            int ans = 0;
            for (int i = 0; i < n; i++) {
                int v = 2 * Integer.min(lis[i] - 1, lds[i] - 1) + 1;
                ans = Integer.max(ans, v);
            }
            out.append(String.format("%d\n", ans));
        }
    }

    void close() throws IOException {
        if (out != null) {
            out.flush();
            out.close();
        }
    }

    public static void main(String[] args) throws Exception {
        FileInputStream inStream = null;
        PrintStream outStream = null;
        boolean isLocal = System.getenv("LOCAL_JUDGE") != null;
        if (isLocal) {
            inStream = new FileInputStream("1.in");
            //            outStream = new PrintStream("1.out");
            System.setIn(inStream);
            //            System.setOut(outStream);
        }

        Main main = new Main();
        main.solve();
        main.close();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
