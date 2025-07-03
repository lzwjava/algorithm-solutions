package com.lzw.solutions.uva.p10070;

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
    
    int mod(BigInteger year, int x) {
        return year.mod(BigInteger.valueOf(x)).intValue();
    }
    
    boolean leap(BigInteger year) {
        return (mod(year, 4) == 0 && mod(year, 100) != 0) || mod(year, 400) == 0;
    }
    
    boolean huluculu(BigInteger year) {
        return mod(year, 15) == 0;
    }
    
    boolean bulukulu(BigInteger year) {        
        return leap(year) && mod(year, 55) == 0;
    }
   
    void solve() throws IOException {
        boolean first = true;
        while (true) {
            String line = in.readLine();
            if (line == null || line.isEmpty()) {
                break;
            }
            if (first) {
                first = false;
            } else {
                out.append('\n');
            }
            BigInteger n = new BigInteger(line);
            boolean leap = leap(n);
            boolean huluculu = huluculu(n);
            boolean bulukulu = bulukulu(n);
            boolean normal = !leap && !huluculu && !bulukulu;
            if (leap) {
                out.append("This is leap year.\n");
            }
            if (huluculu) {
                out.append("This is huluculu festival year.\n");
            }
            if (bulukulu) {
                out.append("This is bulukulu festival year.\n");
            }
            if (normal) {
                out.append("This is an ordinary year.\n");
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
        boolean isLocal = System.getProperty("os.name").equals("Mac OS X");        
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
