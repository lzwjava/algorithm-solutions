package com.algorithm.solutions.uva.p104;

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
            int n = Integer.parseInt(line.trim());
//            double[][] table = new double[n][n];
            double[][][] d = new double[n + 1][n][n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                for (int j = 0; j < n; j++) {
                    double v;
                    if (i == j) {
                        v = 1.0;
                    } else {
                        v = Double.parseDouble(st.nextToken());
                    }
                    d[1][i][j] = v;
//                    table[i][j] = v;
                }
            }
            boolean found = false;
            int[][][] q = new int[n + 1][n][n];
            int l, i = 0, j = 0;
            for (l = 2; l <= n; l++) {
                for (i = 0; i < n; i++) {
                    for (j = 0; j < n; j++) {
                        double mv = 0;
                        int mk = 0;
                        for (int k = 0; k < n; k++) {
                            double v = d[l - 1][i][k] * d[1][k][j];
                            if (v > mv) {
                                mk = k;
                                mv = v;
                            }
                        }
                        q[l][i][j] = mk;
                        d[l][i][j] = mv;
                        if (i == j && mv > 1.01) {
                            found = true;
                            break;
                        }
                    }
                    if (found) {
                        break;
                    }
                }
                if (found) {
                    break;
                }
            }
            if (!found) {
                out.append("no arbitrage sequence exists\n");
            } else {
                print(q, l, i, j);
                out.append(String.format("%d", i + 1));
                out.append('\n');
            }
        }
    }

    void print(int[][][] q, int l, int i, int j) {
        if (l == 1) {
            out.append(String.format("%d ", i + 1));
        } else {
            int k = q[l][i][j];
            print(q, l - 1, i, k);
            print(q, 1, k, j);
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

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
