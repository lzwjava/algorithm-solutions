package com.lzw.solutions.codeforces.p231A;

import java.io.*;
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
        int[][] nums = new int[n][3];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int j = 0; j < 3; j++) {
                nums[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            int sure = 0;
            for (int j = 0; j < 3; j++) {
                if (nums[i][j] == 1) {
                    sure++;
                }
            }
            if (sure >= 2) {
                cnt++;
            }
        }
        out.append(String.format("%d\n", cnt));
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
