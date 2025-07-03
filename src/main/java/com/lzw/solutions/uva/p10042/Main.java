package com.lzw.solutions.uva.p10042;

import java.io.*;
import java.util.ArrayList;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    ArrayList<Integer> primes;

    void calPrimes() {
        primes = new ArrayList<Integer>();
        primes.add(2);
        for (int i = 3; i <= 31622; i += 2) {
            int si = (int) Math.sqrt(i);
            boolean ok = true;
            for (int j = 0; j < primes.size(); j++) {
                int pj = primes.get(j);
                if (pj > si) {
                    break;
                }
                if (i % pj == 0) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                primes.add(i);
            }
        }
    }

    private ArrayList<Integer> calFactor(int a) {
        ArrayList<Integer> factors = new ArrayList<>();
        int si = (int) Math.sqrt(a);
        for (int i = 0; i < primes.size(); i++) {
            int pi = primes.get(i);
            if (pi > si) {
                break;
            }
            if (a % pi == 0) {
                while (a % pi == 0) {
                    a /= pi;
                    factors.add(pi);
                }
            }
        }
        if (a != 1) {
            factors.add(a);
        }
        return factors;
    }

    int sum(int x) {
        int sum = 0;
        while (x != 0) {
            sum += x % 10;
            x /= 10;
        }
        return sum;
    }

    void solve() throws IOException {
        calPrimes();
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            int n = Integer.parseInt(in.readLine());
            while (true) {
                n++;
                ArrayList<Integer> factors = calFactor(n);
                if (factors.size() == 1) {
                    continue;
                }
                int s1 = sum(n);
                int s2 = 0;
                for (Integer f : factors) {
                    s2 += sum(f);
                }
                if (s1 == s2) {
                    break;
                }
            }
            out.append(String.format("%d\n", n));
            t--;
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
