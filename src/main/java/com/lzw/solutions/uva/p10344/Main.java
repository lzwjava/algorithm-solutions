package com.lzw.solutions.uva.p10344;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int cal(int a, int b, int op) {
        if (op == 0) {
            return a + b;
        } else if (op == 1) {
            return a - b;
        } else if (op == 2) {
            return a * b;
        }
        return 0;
    }

    void permutationOperator(int[] gens, int[] ops, int cur, int n) {
        if (possible) {
            return;
        }
        if (cur == n) {
            int m1 = cal(gens[0], gens[1], ops[0]);
            int m2 = cal(m1, gens[2], ops[1]);
            int m3 = cal(m2, gens[3], ops[2]);
            int m4 = cal(m3, gens[4], ops[3]);
            if (m4 == 23) {
                possible = true;
            }
            return;
        }
        for (int i = 0; i < 3; i++) {
            ops[cur] = i;
            permutationOperator(gens, ops, cur + 1, n);
        }
    }

    void permutation(int[] nums, int[] gens, int cur, int n) {
        if (possible) {
            return;
        }
        if (cur == n) {
            int[] ops = new int[4];
            permutationOperator(gens, ops, 0, 4);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                int c1 = 0, c2 = 0;
                for (int j = 0; j < n; j++) {
                    if (nums[j] == nums[i]) {
                        c1++;
                    }
                }
                for (int j = 0; j < cur; j++) {
                    if (gens[j] == nums[i]) {
                        c2++;
                    }
                }
                if (c2 < c1) {
                    gens[cur] = nums[i];
                    permutation(nums, gens, cur + 1, n);
                }
            }
        }
    }

    boolean possible;

    void solve() throws IOException {
        while (true) {
            int[] nums = new int[5];
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int i = 0; i < 5; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }
            boolean ok = true;
            for (int i = 0; i < 5; i++) {
                if (nums[i] != 0) {
                    ok = false;
                }
            }
            if (ok) {
                break;
            }
            Arrays.sort(nums);
            int[] gens = new int[5];
            possible = false;
            permutation(nums, gens, 0, 5);
            if (possible) {
                out.append("Possible\n");
            } else {
                out.append("Impossible\n");
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
        FileInputStream inStream = null;
        PrintStream outStream = null;
        boolean isLocal = System.getProperty("os.name").equals("Mac OS X");
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
