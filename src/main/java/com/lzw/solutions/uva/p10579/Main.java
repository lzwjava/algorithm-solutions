package com.lzw.solutions.uva.p10579;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.math.BigInteger;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }
    
    BigInteger f(int x) {
        BigInteger cache = nums[x];
        if (cache != null) {
            return cache;
        }
        if (x == 1) {
            return BigInteger.valueOf(1);
        }
        if (x == 2) {
            return BigInteger.valueOf(1);
        }
        BigInteger b1 = f(x - 1);
        BigInteger b2 = f(x - 2);
        BigInteger result = b1.add(b2);
        nums[x] = result;
        return result;
    }

    BigInteger[] nums;
   
    void solve() throws IOException {
        while (true) {
            String line = in.readLine();
            if (line == null) {
                break;
            }            
            int n = Integer.parseInt(line);
            nums = new BigInteger[10000];            
            BigInteger ans = f(n);
            out.append(String.format("%s\n", ans.toString()));
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
