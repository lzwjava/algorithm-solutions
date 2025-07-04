package com.lzw.solutions.uva.p10943;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main1 {

    BufferedReader in;
    PrintWriter out;

    Main1() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    BigInteger total;

    BigInteger factorial(int n) {
        if (n == 1) {
            return BigInteger.valueOf(1);
        }
        return factorial(n - 1).multiply(BigInteger.valueOf(n));
    }

    void permutation(int[] nums, int cur, int k, int n, int s) {
        if (cur == k) {
            if (s == n) {
                BigInteger tmp = BigInteger.valueOf(1);
                int equal = 1;
                for (int i = 1; i < k; i++) {
                    if (nums[i] == nums[i - 1]) {
                        equal++;
                    } else {
                        if (equal > 1) {
                            tmp.multiply(factorial(equal));
                            equal = 1;
                        } else {
                            equal = 1;
                        }
                    }
                }
                if (equal > 1) {
                    tmp.multiply(factorial(equal));
                }
                BigInteger sub = factorial(k).divide(tmp);
                total.add(sub);
            }
            return;
        }
        int bi;
        if (cur == 0) {
            bi = 0;
        } else {
            bi = nums[cur - 1];
        }
        for (int i = bi; i <= n; i++) {
            nums[cur] = i;
            int ns = s + nums[cur];
            if (ns <= n) {
                permutation(nums, cur + 1, k, n, ns);
            }
        }
    }

    void solve() throws IOException {
        while (true) {
            String s = in.readLine();
            StringTokenizer st = new StringTokenizer(s);
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            if (n == 0 && k == 0) {
                break;
            }
            int nums[] = new int[k];
            total = BigInteger.valueOf(0);
            permutation(nums, 0, k, n, 0);
            out.append(
                    String.format("%d\n", total.mod(BigInteger.valueOf(1000000)).longValue()));
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

        Main1 main = new Main1();
        main.solve();
        main.close();
    }
}
