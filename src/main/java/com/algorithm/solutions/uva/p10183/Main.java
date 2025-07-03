package com.algorithm.solutions.uva.p10183;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    BigInteger[] fs;
    
    BigInteger f(int n) {
        BigInteger cache = fs[n];
        if (cache != null) {
            return cache;
        }
        BigInteger v;
        if (n == 1) {
            v= BigInteger.valueOf(1);
        } else if (n == 2) {
            v= BigInteger.valueOf(2);
        } else {
            v= f(n - 1).add(f(n - 2));                    
        }
        fs[n] = v;
        return v;
    }
   
    void solve() throws IOException {
        int maxn = 500;
        fs = new BigInteger[maxn];
        fs[0] = BigInteger.valueOf(0);        
        f(maxn - 1);
        // double ta = Math.log10(v.doubleValue());
        // out.append(String.format("%.3f\n", ta));
        while (true) {
            String s = in.readLine();
            StringTokenizer st = new StringTokenizer(s);
            BigInteger a = new BigInteger(st.nextToken());
            BigInteger b = new BigInteger(st.nextToken());
            BigInteger zero = BigInteger.valueOf(0);
            if (a.compareTo(zero) == 0 && b.compareTo(zero) == 0) {
                break;
            }
            int ia = Arrays.binarySearch(fs, a);
            int ib = Arrays.binarySearch(fs, b);
            int len;
            if (ia >= 0 && ib >= 0) {
                len = ib - ia + 1;                
            } else if (ia < 0 && ib > 0) {
                ia = -(ia + 1);
                len = ib - ia + 1;             
            } else if (ia >= 0 && ib < 0) {
                ib = -(ib + 1);
                len = ib - ia;
            } else {
                ia = -(ia + 1);
                ib = -(ib + 1);
                len = ib - ia;
            }
            if (a.compareTo(zero) == 0) {
                len--;
            }
            out.append(String.format("%d\n", len));
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

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
