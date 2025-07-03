package com.algorithm.solutions.uva.p11715;

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
        int caseNum = 1;
        while (true) {
            String line = in.readLine();
            StringTokenizer st = new StringTokenizer(line);
            int type = Integer.parseInt(st.nextToken());
            if (type == 0) {
                break;
            }
            double d1 = Double.parseDouble(st.nextToken());
            double d2 = Double.parseDouble(st.nextToken());            
            double d3 = Double.parseDouble(st.nextToken());
            out.append(String.format("Case %d: ", caseNum));
            if (type == 1) {
                double u = d1, v = d2, t = d3;
                double a = (v - u) / t;
                double s = (u + v) / 2 * t;
                out.append(String.format("%.3f %.3f\n", s, a));                
            } else if (type == 2) {
                double u = d1, v = d2, a = d3;
                double t = (v - u) / a;
                double s = (u + v) / 2 * t;
                out.append(String.format("%.3f %.3f\n", s, t));                                
            } else if (type == 3) {
                double u = d1, a = d2, s = d3;
                double v = Math.sqrt(u * u + 2 * a * s);
                double t = (v - u) / a;                
                out.append(String.format("%.3f %.3f\n", v, t));                                                
            } else {                
                double v = d1, a = d2, s = d3;
                double u = Math.sqrt(v * v - 2 * a * s);
                double t = (v - u) / a;
                out.append(String.format("%.3f %.3f\n", u, t));                                
            }
            caseNum++;
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
