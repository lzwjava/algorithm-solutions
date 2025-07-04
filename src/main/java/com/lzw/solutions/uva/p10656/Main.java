package com.lzw.solutions.uva.p10656;

import java.io.*;
import java.util.ArrayList;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

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
            int max = 0;
            ArrayList<Integer> indices = new ArrayList<Integer>();
            for (int i = 0; i < n; i++) {
                if (nums[i] > 0) {
                    max += nums[i];
                    indices.add(i);
                }
            }
            if (indices.size() == 0) {
                indices.add(0);
            }
            for (int i = 0; i < indices.size(); i++) {
                if (i != 0) {
                    out.append(" ");
                }
                out.append(String.valueOf(nums[indices.get(i)]));
            }
            out.append('\n');
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
            outStream = new PrintStream("1.out");
            System.setIn(inStream);
            System.setOut(outStream);
        }

        Main main = new Main();
        main.solve();
        main.close();
    }
}
