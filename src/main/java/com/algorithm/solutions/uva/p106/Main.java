package com.algorithm.solutions.uva.p106;

import java.io.*;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    boolean isInteger(double z) {
        return Math.abs(z - Math.round(z)) < 1e-8;
    }

    int gcd(int x, int y) {
        if (y == 0) {
            return x;
        }
        return gcd(y, x % y);
    }

    boolean relativePrime(int x, int y, int z) {
        return gcd(x, y) == 1 && gcd(y, z) == 1 && gcd(x, z) == 1;
    }

    void solve() throws IOException {
        while (true) {
            String line = in.readLine();
            if (line == null) {
                break;
            }
            int N = Integer.parseInt(line);
            int count = 0;
            int sn = (int) Math.sqrt(N);
            boolean[] used = new boolean[N + 1];
            for (int s = 1; s <= sn; s++) {
                for (int r = s + 1; r <= sn; r++) {
                    int rr = r * r;
                    int ss = s * s;
                    int x = rr - ss;
                    int y = 2 * r * s;
                    int z = rr + ss;
                    for (int k = 1; k * z <= N; k++) {
                        used[k * x] = true;
                        used[k * y] = true;
                        used[k * z] = true;
                    }
                    if (z <= N && gcd(r, s) == 1 && r % 2 != s % 2) {
                        count++;
                    }
                }
            }
            int p = 0;
            for (int i = 1; i <= N; i++) {
                if (!used[i]) {
                    p++;
                }
            }
            out.append(String.format("%d %d\n", count, p));
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
