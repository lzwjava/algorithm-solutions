package com.lzw.solutions.uva.p10137;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main1 {

    BufferedReader in;
    PrintWriter out;

    Main1() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void solve() throws IOException {
        while (true) {
            int n = Integer.parseInt(in.readLine().trim());
            if (n == 0) {
                break;
            }
            int nums[] = new int[n];
            int total = 0;
            for (int i = 0; i < n; i++) {
                double num = Double.parseDouble(in.readLine().trim());
                nums[i] = (int) Math.round(num * 100);
                total += nums[i];
            }
            int avg;
            int highAvg, lowAvg;
            if (total % n == 0) {
                avg = total / n;
                highAvg = lowAvg = avg;
            } else {
                avg = total / n;
                highAvg = avg + 1;
                lowAvg = avg;
            }
            int highSum = 0;
            int lowSum = 0;
            for (int i = 0; i < n; i++) {
                int num = nums[i];
                if (num > highAvg) {
                    highSum += num - highAvg;
                }
                if (num < lowAvg) {
                    lowSum += lowAvg - num;
                }
            }
            int ans = Math.max(lowSum, highSum);
            double ansNum = ans * 1.0 / 100;
            out.append(String.format("$%.2f\n", ansNum));
        }
    }

    void close() throws IOException {
        if (in != null) {
            in.close();
        }
        if (out != null) {
            out.flush();
            out.close();
        }
    }

    public static void main(String[] args) throws Exception {

        Main1 main = new Main1();
        main.solve();
        main.close();
    }
}
