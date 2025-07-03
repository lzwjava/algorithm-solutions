package com.lzw.solutions.uva.p10611;

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

    void solve() throws IOException {
        int n = Integer.parseInt(in.readLine());
        int nums[] = new int[n];
        String s = in.readLine();
        StringTokenizer st = new StringTokenizer(s);
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        int q = Integer.parseInt(in.readLine());
        s = in.readLine();
        st = new StringTokenizer(s);
        for (int i = 0; i < q; i++) {
            int h = Integer.parseInt(st.nextToken());
            int pos = Arrays.binarySearch(nums, h);
            if (pos < 0) {
                pos = -(pos + 1);
            }
            int lp = pos;
            if (lp > n - 1) {
                lp = n - 1;
            }
            for (; lp >= 0; lp--) {
                if (nums[lp] < h) {
                    break;
                }
            }
            int rp = pos;
            if (rp < 0) {
                rp = 0;
            }
            for (; rp < n; rp++) {
                if (nums[rp] > h) {
                    break;
                }
            }
            String lps, rps;
            if (lp >= 0) {
                lps = String.valueOf(nums[lp]);
            } else {
                lps = "X";
            }
            if (rp < n) {
                rps = String.valueOf(nums[rp]);
            } else {
                rps = "X";
            }
            out.append(String.format("%s %s\n", lps, rps));
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
