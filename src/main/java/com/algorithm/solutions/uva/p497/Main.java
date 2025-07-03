package com.algorithm.solutions.uva.p497;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    ArrayList<Integer> list;

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        in.readLine();
        while (t > 0) {
            list = new ArrayList<Integer>();
            while (true) {
                String line = in.readLine();
                if (line == null || line.isEmpty()) {
                    break;
                }
                int a = Integer.parseInt(line);
                list.add(a);
            }
            int n = list.size();
            int[] d = new int[n];
            int[] fa = new int[n];
            for (int i = 0; i < n; i++) {
                fa[i] = i;
            }
            Arrays.fill(d, 1);
            for (int i = 1; i < n; i++) {
                int v = 0;
                for (int j = 0; j < i; j++) {
                    if (list.get(j) < list.get(i)) {
                        int v0 = d[j] + 1;
                        if (v0 > v) {
                            v = v0;
                            fa[i] = j;
                        }
                    }
                }
                if (v > d[i]) {
                    d[i] = v;
                }
            }
            int max = 0;
            int maxi = 0;
            for (int i = 0; i < n; i++) {
                if (d[i] > max) {
                    max = d[i];
                    maxi = i;
                }
            }
            out.append(String.format("Max hits: %d\n", max));
            print(fa, maxi);
            t--;
            if (t != 0) {
                out.append('\n');
            }
        }
    }

    void print(int fa[], int i) {
        if (fa[i] != i) {
            print(fa, fa[i]);
        }
        out.append(String.format("%d\n", list.get(i)));
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
