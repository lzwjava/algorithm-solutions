package com.lzw.solutions.uva.p496;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    // as is subset of bs
    boolean subset(int[] as, int[] bs) {
        for (int i = 0; i < as.length; i++) {
            if (Arrays.binarySearch(bs, as[i]) < 0) {
                return false;
            }
        }
        return true;
    }

    void solve() throws IOException {
        while (true) {
            String line = in.readLine();
            if (line == null) {
                break;
            }
            StringTokenizer st = new StringTokenizer(line);
            int an = st.countTokens();
            int[] as = new int[an];
            for (int i = 0; i < an; i++) {
                as[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(in.readLine());
            int bn = st.countTokens();
            int[] bs = new int[bn];
            for (int i = 0; i < bn; i++) {
                bs[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(as);
            Arrays.sort(bs);
            int min = Math.min(an, bn);
            boolean equal = true;
            for (int i = 0; i < min; i++) {
                if (as[i] != bs[i]) {
                    equal = false;
                    break;
                }
            }
            if (an == bn && equal) {
                out.append("A equals B\n");
                continue;
            }
            if (subset(as, bs)) {
                out.append("A is a proper subset of B\n");
                continue;
            }
            if (subset(bs, as)) {
                out.append("B is a proper subset of A\n");
                continue;
            }
            boolean hasCommon = false;
            for (int i = 0; i < an; i++) {
                if (Arrays.binarySearch(bs, as[i]) >= 0) {
                    hasCommon = true;
                    break;
                }
            }
            if (hasCommon) {
                out.append("I'm confused!\n");
            } else {
                out.append("A and B are disjoint\n");
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
