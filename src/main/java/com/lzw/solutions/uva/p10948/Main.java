package com.lzw.solutions.uva.p10948;

import java.io.*;
import java.util.Arrays;

public class Main {

    BufferedReader in;
    PrintWriter out;
    boolean[] primes;

    void calPrimes() {
        int maxn = 1000001;
        primes = new boolean[maxn];
        Arrays.fill(primes, true);
        primes[0] = primes[1] = false;
        for (int i = 4; i < maxn; i += 2) {
            primes[i] = false;
        }
        int sm = (int) Math.sqrt(maxn);
        for (int i = 3; i <= sm; i += 2) {
            if (primes[i]) {
                for (int j = i * i; j < maxn; j += i) {
                    primes[j] = false;
                }
            }
        }
    }

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void solve() throws IOException {
        calPrimes();
        while (true) {
            int n = Integer.parseInt(in.readLine());
            if (n == 0) {
                break;
            }
            boolean found = false;
            for (int i = 2; i <= n / 2; i++) {
                int j = n - i;
                if (primes[i] && primes[j]) {
                    found = true;
                    out.append(String.format("%d:\n", n));
                    out.append(String.format("%d+%d\n", i, j));
                    break;
                }
            }
            if (!found) {
                out.append(String.format("%d:\n", n));
                out.append(String.format("NO WAY!\n"));
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
            inStream = new FileInputStream("2.in");
            // outStream = new PrintStream("1.out");
            System.setIn(inStream);
            // System.setOut(outStream);
        }

        Main main = new Main();
        main.solve();
        main.close();
    }
}
