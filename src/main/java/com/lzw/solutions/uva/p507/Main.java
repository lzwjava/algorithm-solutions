package com.lzw.solutions.uva.p507;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int readInt() throws NumberFormatException, IOException {
        String s = in.readLine();
        return Integer.parseInt(s.trim());
    }

    void solve() throws IOException {
        int b = readInt();
        int caseNum = 1;
        while (b > 0) {
            int s = readInt();
            int[] nums = new int[s - 1];
            for (int i = 0; i < s - 1; i++) {
                nums[i] = readInt();
            }
            int sum = 0;
            int st = 0;
            int max = Integer.MIN_VALUE;
            int maxSt = 0;
            int maxEnd = 0;
            for (int i = 0; i < s - 1; i++) {
                sum += nums[i];
                if (sum < 0) {
                    sum = 0;
                    st = i + 1;
                }
                if (sum > max || (sum == max && (i - st + 1) > (maxEnd - maxSt + 1))) {
                    max = sum;
                    maxSt = st;
                    maxEnd = i;
                }
            }
            if (max > 0) {
                out.append(String.format(
                        "The nicest part of route %d is between stops %d and %d\n", caseNum, maxSt + 1, maxEnd + 2));
            } else {
                out.append(String.format("Route %d has no nice parts\n", caseNum));
            }
            caseNum++;
            b--;
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
