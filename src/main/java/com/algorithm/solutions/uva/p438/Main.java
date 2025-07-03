package com.algorithm.solutions.uva.p438;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }
   
    void solve() throws IOException {
        while (true) {
            String s = in.readLine();
            if (s == null) {
                break;
            }
            StringTokenizer st = new StringTokenizer(s);            
            double x1, y1, x2, y2, x3, y3;
            x1 = Double.parseDouble(st.nextToken());
            y1 = Double.parseDouble(st.nextToken());
            x2 = Double.parseDouble(st.nextToken());
            y2 = Double.parseDouble(st.nextToken());
            x3 = Double.parseDouble(st.nextToken());
            y3 = Double.parseDouble(st.nextToken());        
            
            double mx1 = (x1 + x2) / 2;
            double my1 = (y1 + y2) / 2;
            double k1;
            int type1;
            if (x1 != x2 && y1 != y2){
                k1 = (y2 - y1) / (x2 - x1);
                k1 = -1 / k1;
                type1 = 0;
            } else if (x1==x2){
                // y = my1
                k1 = 0;
                type1 = 1;
            } else {
                // y1== y2
                // x = mx1
                k1 = Double.POSITIVE_INFINITY;
                type1 = 2;
            }

            // y - y1 = k (x - x1)
            // y - y2 = k (x - x2)
            double mx2 = (x2 + x3) / 2;
            double my2 = (y2 + y3) / 2;
            double k2;
            int type2;
            if (x2 != x3 && y2 != y3) {
                k2 = (y3 - y2) / (x3 - x2);
                k2 = -1 / k2;
                type2 = 0;
            } else if (x2 == x3) {
                // y = my2
                k2 = 0;
                type2 = 1;
            } else {
                // y2 == y3
                // x = mx2
                k2 = Double.POSITIVE_INFINITY;
                type2 = 2;
            }
            // y - my1 = k1(x-mx1)
            // y - my2 = k2(x-mx2)            
            // -my1 + my2 = k1*x - k1*mx1 - k2*x +k2*mx2
            // -my1+my2+k1*mx1-k2*mx2 = (k1-k2) x
            double x = 0, y =0;
            if (type1 == 0 && type2 == 0) {
                x = (-my1 + my2 + k1 * mx1 - k2 * mx2) / (k1 - k2);
                y = k1 * (x - mx1) + my1;                                      
            } else if (type1 == 1 && type2 == 0) {
                y = my1;
                // my1 - my2 = k2(x-mx2)                
                // (my1-my2)/k2
                x = (y-my2) / k2 + mx2;
            } else if (type1 == 0 && type2 == 1) {
                y = my2;
                x = (y-my1) / k1 + mx1;
            } else if (type1 == 2 && type2 == 0) {
                x = mx1;
                y = k2 * (x - mx2) + my2;
            } else if (type1 == 0 && type2 == 2) {
                x = mx2;
                y = k1 * (x - mx1) + my1;
            } else if (type1 == 1 && type2 == 2) {
                y = my1;
                x = mx2;
            } else if (type1 == 2 && type2 == 1) {
                x = mx1;
                y = my2;
            } else {
                assert (false);
            }
            double r = Math.sqrt(Math.pow(x - x1, 2) + Math.pow(y - y1, 2));
            double c = 2 * Math.PI * r;
            if (Double.isNaN(c)) {
                System.out.println();
            }
            out.append(String.format("%.2f\n", c));     
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
