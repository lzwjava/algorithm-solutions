package com.lzw.solutions.uva.p324;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.HashMap;

public class Main {

    BufferedReader in;
    PrintWriter out;
    HashMap<Integer, BigInteger> factorials;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    BigInteger calFactoris(int n) {
        if (n == 0) {
            return BigInteger.valueOf(1);
        }
        BigInteger bi = factorials.get(n);
        if (bi != null) {
            return bi;
        }
        bi = calFactoris(n - 1);
        BigInteger res = bi.multiply(BigInteger.valueOf(n));
        factorials.put(n, res);
        return res;
    }

    void solve() throws IOException {
        factorials = new HashMap<>();
        while (true) {
            int n = Integer.parseInt(in.readLine());
            if (n == 0) {
                break;
            }
            BigInteger bn = calFactoris(n);
            int nums[] = new int[10];
            String s = bn.toString(10);
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                int index = ch - '0';
                nums[index]++;
            }
            out.append(String.format("%d! --\n", n));
            for (int i = 0; i < 10; i++) {
                out.append(String.format("   (%d)%5d", i, nums[i]));
                if ((i + 1) % 5 == 0) {
                    out.append('\n');
                }
            }
            // out.append('\n');
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
