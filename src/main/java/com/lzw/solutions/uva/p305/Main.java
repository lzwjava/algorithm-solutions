package com.lzw.solutions.uva.p305;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int cal(int k) {
        int n = 2 * k;
        int m;
        for (m = 1; ; m++) {
            ArrayList<Integer> nums = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                nums.add(i + 1);
            }
            int cur = 0;
            boolean ok = true;
            for (int i = 0; i < k; i++) {
                cur = (cur - 1 + m) % nums.size();
                if (nums.get(cur) <= k) {
                    ok = false;
                    break;
                }
                nums.remove(cur);
            }
            if (ok) {
                break;
            }
        }
        return m;
    }

    void solve() throws IOException {
        int[] ans = new int[] {0, 2, 7, 5, 30, 169, 441, 1872, 7632, 1740, 93313, 459901, 1358657, 2504881};
        // for (int k = 1; k < 14; k++) {
        // int m = cal(k);
        // out.append(String.format("%d,", m));
        // }
        while (true) {
            int k = Integer.parseInt(in.readLine());
            if (k == 0) {
                break;
            }
            int m = ans[k];
            out.append(String.format("%d\n", m));
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
    }
}
