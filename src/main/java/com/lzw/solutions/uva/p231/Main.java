package com.lzw.solutions.uva.p231;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    boolean[] taken;

    int dp(ArrayList<Integer> nums, int i, int last) {
        if (i >= nums.size()) {
            return 0;
        }
        // take i
        int ans = 0;
        if (nums.get(i) <= last) {
            ans = 1;
            for (int j = i + 1; j < nums.size(); j++) {
                if (nums.get(j) <= nums.get(i)) {
                    int ans1 = dp(nums, j, nums.get(i)) + 1;
                    if (ans1 > ans) {
                        ans = ans1;
                    }
                }
            }
        }
        // do not take i
        int ans1 = dp(nums, i + 1, last);
        if (ans1 > ans) {
            taken[i] = false;
            ans = ans1;
        } else {
            taken[i] = true;
        }
        return ans;
    }

    void solve() throws IOException {
        int caseNum = 1;
        while (true) {
            ArrayList<Integer> nums = new ArrayList<>();
            int a = Integer.parseInt(in.readLine());
            if (a == -1) {
                break;
            }
            nums.add(a);
            while (true) {
                int num = Integer.parseInt(in.readLine());
                if (num == -1) {
                    break;
                }
                nums.add(num);
            }
            taken = new boolean[nums.size()];
            int count = dp(nums, 0, Integer.MAX_VALUE);
            if (caseNum != 1) {
                out.append('\n');
            }
            out.append(String.format("Test #%d:\n", caseNum));
            out.append(String.format("  maximum possible interceptions: %d\n", count));
            caseNum++;
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

        Main main = new Main();
        main.solve();
        main.close();
    }
}
