package com.algorithm.solutions.uva.p10810;

import java.io.*;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    class Num implements Comparable<Num> {
        int v;
        int i;

        Num(int v, int i) {
            this.v = v;
            this.i = i;
        }

        @Override
        public int compareTo(Num o) {
            return Integer.compare(v, o.v);
        }
    }

    void mergeSort(int[] nums, int i, int j) {
        if (i == j) {
            return;
        }
        int mid = (i + j) / 2;
        mergeSort(nums, i, mid);
        mergeSort(nums, mid + 1, j);
        int len = j - i + 1;
        int[] ms = new int[len];
        int c = 0;
        int p = i, q = mid + 1;
        while (p <= mid && q <= j) {
            if (nums[p] <= nums[q]) {
                ms[c++] = nums[p++];
            } else {
                ms[c++] = nums[q++];
                total += mid - p + 1;
            }
        }
        if (p <= mid) {
            while (p <= mid) {
                ms[c++] = nums[p++];
            }
        }
        if (q <= j) {
            while (q <= j) {
                ms[c++] = nums[q++];
            }
        }
        for (int x = 0; x < len; x++) {
            nums[x + i] = ms[x];
        }
    }

    long total;

    void solve() throws IOException {
        while (true) {
            int n = Integer.parseInt(in.readLine());
            if (n == 0) {
                break;
            }
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = Integer.parseInt(in.readLine());
            }
            total = 0;
            mergeSort(nums, 0, n - 1);
            out.append(String.format("%d\n", total));
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
        FileInputStream inStream = null;
        PrintStream outStream = null;
        boolean isLocal = System.getenv("LOCAL_JUDGE") != null;
        if (isLocal) {
            inStream = new FileInputStream("1.in");
            // outStream = new PrintStream("1.out");
            System.setIn(inStream);
            // System.setOut(outStream);
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
