package com.algorithm.solutions.uva.p10056;

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
        int s = Integer.parseInt(in.readLine());
        while (s > 0) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(st.nextToken());
            double p = Double.parseDouble(st.nextToken());
            int I = Integer.parseInt(st.nextToken());

            double ans = 0;
            for (int i = 1; ; i++) {
                if (i % n == I % n) {
                    double np = Math.pow(1 - p, i - 1);
                    double fp = np * p;
                    if (fp < 1e-6) {
                        break;
                    }
                    ans += fp;
                }
            }

            out.append(String.format("%.4f\n", ans));

            s--;
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
