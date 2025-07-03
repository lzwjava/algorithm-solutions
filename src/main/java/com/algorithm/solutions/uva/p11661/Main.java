package com.algorithm.solutions.uva.p11661;

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
            String line = in.readLine();
            int L = Integer.parseInt(line);
            if (L == 0) {
                break;
            }
            line = in.readLine();
            assert (line.length() == L);
            char lastCh = ' ';
            int lastPos = -1;
            int dist = Integer.MAX_VALUE;
            for (int i = 0; i < L; i++) {
                char ch = line.charAt(i);
                if (ch == '.') {
                    continue;
                } else if (ch == 'Z') {
                    dist = 0;
                    break;
                } else {
                    assert (ch == 'R' || ch == 'D');
                    if (lastPos == -1) {
                        lastPos = i;
                        lastCh = ch;
                    } else {
                        if (ch == 'R') {
                            if (lastCh == 'D') {
                                int d = i - lastPos;
                                if (d < dist) {
                                    dist = d;
                                }
                            }
                        } else {
                            if (lastCh == 'R') {
                                int d = i - lastPos;
                                if (d < dist) {
                                    dist = d;
                                }
                            }
                        }
                        lastPos = i;
                        lastCh = ch;
                    }
                }
            }
            out.append(String.format("%d\n", dist));
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
