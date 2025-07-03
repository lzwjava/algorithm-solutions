package com.lzw.solutions.uva.p374;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Main {

    long bigMod(int base, int pow, int mod) {
        if (pow == 0) {
            return 1 % mod;
        } else if (pow == 1) {
            return base % mod;
        }
        long half = bigMod(base, pow / 2, mod);
        long res = ((half % mod) * (half % mod)) % mod;
        if (pow % 2 == 0) {
            return res;
        } else {
            return (res * base) % mod;
        }
    }

    int readInt(BufferedReader br) throws IOException {
        while (true) {
            String str = br.readLine();
            if (str == null) {
                throw new IOException("end");
            }
            if (str.isEmpty()) {
                continue;
            }
            return Integer.parseInt(str);
        }
    }
   
    void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            int b = readInt(br);
            int p = readInt(br);
            int m = readInt(br);
            long r = bigMod(b, p, m);
            System.out.println(r);
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
        
        try {
            new Main().solve();
        } catch (IOException e) {

        }
        
        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
