package com.lzw.solutions.uva.p661;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void solve() throws IOException {
        int caseNum = 1;
        while (true) {
            String s = in.readLine();
            StringTokenizer st = new StringTokenizer(s);
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (n == 0 && m == 0 && c == 0) {
                break;
            }
            int[] consumptions = new int[n];
            for (int i = 0; i < n; i++) {
                consumptions[i] = Integer.parseInt(in.readLine());
            }
            int[] operations = new int[m];
            for (int i = 0; i < m; i++) {
                operations[i] = Integer.parseInt(in.readLine());
            }
            boolean states[] = new boolean[n];
            boolean ok = true;
            int maxConsumption = 0;
            for (int i = 0; i < m; i++) {
                int index = operations[i] - 1;
                states[index] = !states[index];
                int total = 0;
                for (int j = 0; j < n; j++) {
                    if (states[j]) {
                        total += consumptions[j];
                    }
                }
                if (total > c) {
                    ok = false;
                    break;
                }
                if (total > maxConsumption) {
                    maxConsumption = total;
                }
            }
            out.append(String.format("Sequence %d\n", caseNum));
            if (ok) {
                out.append("Fuse was not blown.\n");
                out.append(String.format("Maximal power consumption was %d amperes.\n", maxConsumption));
            } else {
                out.append("Fuse was blown.\n");
            }
            out.append('\n');
            caseNum++;
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
