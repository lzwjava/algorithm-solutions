package com.algorithm.solutions.uva.p12325;

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
        int t = Integer.parseInt(in.readLine());
        int caseNum = 1;
        while (t > 0) {
            String str = in.readLine();
            StringTokenizer st = new StringTokenizer(str);
            int n = Integer.parseInt(st.nextToken());
            int s[] = new int[2];
            int v[] = new int[2];
            for (int i = 0; i < 2; i++) {
                s[i] = Integer.parseInt(st.nextToken());
                v[i] = Integer.parseInt(st.nextToken());
            }
            int limit = 10000;
            long max = 0;
            if (n / s[0] < limit) {
                int t0 = n / s[0];
                for (int i = 0; i <= t0; i++) {
                    int t1 = (n - i * s[0]) / s[1];
                    long value = (long)i * v[0] + (long)t1 * v[1];
                    if (value > max) {
                        max = value;
                    }
                }                  
            } else if (n / s[1] < limit) {
                int t1 = n / s[1];
                for (int i = 0; i <= t1; i++) {
                    int t0 = (n - i * s[1]) / s[0];
                    long value = (long)t0 * v[0] + (long)i * v[1];
                    if (value > max) {
                        max = value;
                    }
                }                    
            } else {
                if (s[1] * v[0] > s[0] * v[1]) {
                    for (int i = 0; i <= s[0] - 1; i++) {
                        int t0 = (n - i * s[1]) / s[0];
                        long value = (long)t0 * v[0] + (long)i * v[1];
                        if (value > max) {
                            max = value;
                        }
                    }
                } else {
                    for (int i = 0; i <= s[1] - 1; i++) {
                        int t1 = (n - i * s[0]) / s[1];
                        long value = (long)i * v[0] + (long)t1 * v[1];
                        if (value > max) {
                            max = value;
                        }
                    }
                }
            }

            out.append(String.format("Case #%d: %d\n", caseNum, max));
            caseNum++;
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
