package com.algorithm.solutions.uva.p11000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;

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
            int n = Integer.parseInt(s);
            if (n == -1) {
                break;
            }
            long female = 1;
            long male = 0;
            for (int i = 0; i < n; i++) {
                long newFemale = 0;
                long newMale = 0;

                // female give birth
                newMale += female;
                female = 1;

                // male give birth
                newMale += male;
                newFemale += male;
                male = 0;

                newMale += male;
                newFemale += female;

                male = newMale;
                female = newFemale;
            }
            out.append(String.format("%d %d\n", male, male+female));
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
