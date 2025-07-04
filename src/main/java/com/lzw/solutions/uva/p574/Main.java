package com.lzw.solutions.uva.p574;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    class Ans implements Comparable<Ans> {
        int[] nums;

        @Override
        public int compareTo(Main.Ans o) {
            int min = Math.min(nums.length, o.nums.length);
            for (int i = 0; i < min; i++) {
                if (nums[i] != o.nums[i]) {
                    return Integer.compare(o.nums[i], nums[i]);
                }
            }
            if (nums.length == o.nums.length) {
                return 0;
            }
            return Integer.compare(nums.length, o.nums.length);
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Ans)) {
                return false;
            }
            Ans objAns = (Ans) obj;
            return compareTo(objAns) == 0;
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(nums);
        }
    }

    void permutation(int[] nums, boolean[] selected, int cur, int n, int sum, int total) {
        if (sum > total) {
            return;
        }
        if (cur == n) {
            if (sum == total) {
                int count = 0;
                for (int i = 0; i < n; i++) {
                    if (selected[i]) {
                        count++;
                    }
                }
                int[] fnums = new int[count];
                int j = 0;
                for (int i = 0; i < n; i++) {
                    if (selected[i]) {
                        fnums[j] = nums[i];
                        j++;
                    }
                }
                Arrays.sort(fnums);
                for (int i = 0; i < fnums.length / 2; ++i) {
                    int temp = fnums[i];
                    fnums[i] = fnums[fnums.length - i - 1];
                    fnums[fnums.length - i - 1] = temp;
                }
                Ans ans = new Ans();
                ans.nums = fnums;
                set.add(ans);
            }
            return;
        }
        selected[cur] = false;
        permutation(nums, selected, cur + 1, n, sum, total);
        selected[cur] = true;
        permutation(nums, selected, cur + 1, n, sum + nums[cur], total);
    }

    HashSet<Ans> set;

    void solve() throws IOException {
        while (true) {
            String line = in.readLine();
            StringTokenizer st = new StringTokenizer(line);
            int t = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            if (t == 0 && n == 0) {
                break;
            }
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }
            out.append(String.format("Sums of %d:\n", t));
            set = new HashSet<>();
            boolean[] selected = new boolean[n];
            permutation(nums, selected, 0, n, 0, t);
            if (set.size() == 0) {
                out.append("NONE\n");
            } else {
                ArrayList<Ans> ansList = new ArrayList<>();
                ansList.addAll(set);
                Collections.sort(ansList);
                for (Ans ans : ansList) {
                    for (int i = 0; i < ans.nums.length; i++) {
                        if (i != 0) {
                            out.append("+");
                        }
                        out.append(String.format("%d", ans.nums[i]));
                    }
                    out.append('\n');
                }
            }
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
