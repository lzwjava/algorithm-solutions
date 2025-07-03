package com.lzw.solutions.uva.p12646;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    char indexToChar(int index) {
        switch (index) {
            case 0:
                return 'A';
            case 1:
                return 'B';
            case 2:
                return 'C';
        }
        return ' ';
    }

    void solve() throws IOException {
        while (true) {
            String line = in.readLine();
            if (line == null) {
                break;
            }
            StringTokenizer st = new StringTokenizer(line);
            int[] nums = new int[3];
            int[] cnts = new int[2];
            for (int i = 0; i < 3; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
                cnts[nums[i]]++;
            }
            if (cnts[0] == 3 || cnts[1] == 3) {
                out.append("*\n");
            } else {
                int win;
                if (cnts[0] == 2) {
                    win = 1;
                } else {
                    win = 0;
                }
                for (int i = 0; i < 3; i++) {
                    if (nums[i] == win) {
                        out.append(String.format("%c\n", indexToChar(i)));
                        break;
                    }
                }
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
