package com.lzw.solutions.uva.p10616;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MainPlus {

    BufferedReader in;
    PrintWriter out;

    MainPlus() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int readInt() throws IOException {
        return Integer.parseInt(in.readLine());
    }

    int total;

    void permutation(int[] nums, int[] ts, int cur, int m, int d) {
        if (cur == m) {
            int s = 0;
            for (int i = 0; i < m; i++) {
                s += ts[i];
            }
            if (s % d == 0) {
                total++;
            }
            return;
        }
        int st;
        if (cur == 0) {
            st = nums[0];
        } else {
            st = ts[cur - 1];
        }
        int nn = nums.length;
        for (int i = 0; i < nn; i++) {
            if (nums[i] >= st) {
                int c1 = 0;
                int v = nums[i];
                for (int j = 0; j < nn; j++) {
                    if (nums[j] == v) {
                        c1++;
                    }
                }
                int c2 = 0;
                for (int j = 0; j < cur; j++) {
                    if (ts[j] == v) {
                        c2++;
                    }
                }
                if (c2 < c1) {
                    ts[cur] = v;
                    permutation(nums, ts, cur + 1, m, d);
                }
            }
        }
    }

    void solve() throws IOException {
        int caseNum = 1;
        while (true) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            if (n == 0 && q == 0) {
                break;
            }
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = readInt();
            }
            Arrays.sort(nums);
            out.append(String.format("SET %d:\n", caseNum));
            for (int i = 0; i < q; i++) {
                st = new StringTokenizer(in.readLine());
                int d = Integer.parseInt(st.nextToken());
                int m = Integer.parseInt(st.nextToken());
                int[] ns = new int[n];
                for (int j = 0; j < n; j++) {
                    ns[j] = nums[j] % d;
                }
                total = 0;
                int[] ts = new int[m];
                permutation(ns, ts, 0, m, d);
                out.append(String.format("QUERY %d: %d\n", i + 1, total));
            }
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
        FileInputStream inStream = null;
        PrintStream outStream = null;
        boolean isLocal = System.getenv("LOCAL_JUDGE") != null;
        if (isLocal) {
            inStream = new FileInputStream("2.in");
            // outStream = new PrintStream("1.out");
            System.setIn(inStream);
            // System.setOut(outStream);
        }

        MainPlus main = new MainPlus();
        main.solve();
        main.close();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
