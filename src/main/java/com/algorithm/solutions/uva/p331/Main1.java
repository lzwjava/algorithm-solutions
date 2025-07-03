package com.algorithm.solutions.uva.p331;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main1 {

    BufferedReader in;
    PrintWriter out;

    Main1() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    class Ans {
        int[] arr;

        Ans(int[] arr) {
            this.arr = arr;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Ans ans = (Ans) o;
            return Arrays.equals(arr, ans.arr);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(arr);
        }
    }

    HashSet<Ans> set;

    void permutation(int[] arr, int[] nums, int[] sorted, int cur, int cnt, int n) {
        if (cur == cnt) {
            int[] ns = nums.clone();
            for (int i = 0; i < cnt; i++) {
                int x = arr[i];
                int tmp = ns[x];
                ns[x] = ns[x + 1];
                ns[x + 1] = tmp;
            }
            boolean ok = true;
            for (int i = 0; i < n; i++) {
                if (sorted[i] != ns[i]) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                set.add(new Ans(arr.clone()));
            }
            return;
        }
        for (int i = 0; i < n - 1; i++) {
            arr[cur] = i;
            permutation(arr, nums, sorted, cur + 1, cnt, n);
        }
    }

    void solve() throws IOException {
        int caseNum = 1;
        while (true) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(st.nextToken());
            if (n == 0) {
                break;
            }
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }
            int[] sorted = nums.clone();
            int cnt = 0;
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (sorted[i] > sorted[j]) {
                        int tmp = sorted[i];
                        sorted[i] = sorted[j];
                        sorted[j] = tmp;
                        cnt++;
                    }
                }
            }
            int[] arr = new int[cnt];
            int total = 0;
            set = new HashSet<Ans>();
            if (cnt == 0) {
                total = 0;
            } else {
                permutation(arr, nums, sorted, 0, cnt, n);
                total = set.size();
            }
            out.append(String.format("There are %d swap maps for input data set %d.\n", total, caseNum));
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
//            outStream = new PrintStream("2.out");
            System.setIn(inStream);
//            System.setOut(outStream);
        }

        Main1 main = new Main1();
        main.solve();
        main.close();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
