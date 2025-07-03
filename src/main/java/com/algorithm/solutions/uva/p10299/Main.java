package com.algorithm.solutions.uva.p10299;

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

    int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    ArrayList<Integer> list;

    void calPrimes() {
        int maxn = 46340;
        boolean[] primes = new boolean[maxn];
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
        list = new ArrayList<>();
        for (int i = 0; i < maxn; i++) {
            if (primes[i]) {
                list.add(i);
            }
        }
    }

    private ArrayList<Integer> calFactor(int a) {
        ArrayList<Integer> factors = new ArrayList<>();
        int si = (int) Math.sqrt(a);
        for (int i = 0; i < list.size(); i++) {
            int pi = list.get(i);
            if (pi > si) {
                break;
            }
            if (a % pi == 0) {
                factors.add(pi);
                while (a % pi == 0) {
                    a /= pi;
                }
            }
        }
        if (a != 1) {
            factors.add(a);
        }
        return factors;
    }

    void solve() throws IOException {
        calPrimes();
        while (true) {
            int n = Integer.parseInt(in.readLine());
            if (n == 0) {
                break;
            }
            int ans;
            if (n == 1) {
                ans = 0;
            } else if (n == 2) {
                ans = 1;
            } else {
                ArrayList<Integer> factors = calFactor(n);
                int t = n;
                for (Integer factor : factors) {
                    t -= t / factor;
                }
                ans = t;
            }
            out.append(String.format("%d\n", ans));
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
