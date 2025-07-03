package com.algorithm.solutions.uva.p392;

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
        while (true) {
            String line = in.readLine();
            if (line == null) {
                break;
            }
            StringTokenizer st = new StringTokenizer(line);
            int[] nums = new int[9];
            for (int i = 0; i < 9; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }
            StringBuilder sb = new StringBuilder();
            boolean first = true;
            for (int i = 0; i < 9; i++) {
                if (nums[i] != 0) {
                    int a = nums[i];
                    if (Math.abs(a) == 1 && i != 8) {
                        if (first) {
                            if (a == -1) {
                                sb.append("-");
                            }
                            first = false;
                        } else {
                            if (a == 1) {
                                sb.append(" + ");
                            } else {
                                sb.append(" - ");
                            }
                        }
                    } else {
                        if (first) {
                            sb.append(String.format("%d", a));
                            first = false;
                        } else {
                            if (a >= 0) {
                                sb.append(" + ");
                            } else {
                                sb.append(" - ");
                            }
                            sb.append(String.format("%d", Math.abs(a)));
                        }
                    }
                    if (i < 8) {
                        sb.append("x");
                        if (i < 7) {
                            sb.append(String.format("^%d", 7 - i + 1));
                        }
                    }
                }
            }
            if (first) {
                sb.append("0");
            }
            out.append(String.format("%s\n", sb.toString()));
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
            outStream = new PrintStream("2.out");
            System.setIn(inStream);
            System.setOut(outStream);
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
