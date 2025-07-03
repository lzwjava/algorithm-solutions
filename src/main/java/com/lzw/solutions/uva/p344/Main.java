package com.lzw.solutions.uva.p344;

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
    
    String roman(int x) {
        StringBuilder sb = new StringBuilder();
        while (x > 0) {
            if (x >= 100) {
                x -= 100;
                sb.append('c');
                continue;
            }
            if (x >= 90) {
                x -= 90;
                sb.append("xc");
                continue;
            }
            if (x >= 50) {
                x -= 50;
                sb.append('l');
                continue;
            }
            if (x >= 40) {
                x -= 40;
                sb.append("xl");
                continue;
            }
            if (x >= 10) {
                x -= 10;
                sb.append('x');
                continue;
            }
            if (x >= 9) {
                x -= 9;
                sb.append("ix");
                continue;
            }
            if (x >= 5) {
                x -= 5;
                sb.append('v');
                continue;
            }
            if (x >= 4) {
                x -= 4;
                sb.append("iv");
                continue;
            }
            if (x >= 1) {
                x -= 1;
                sb.append('i');
                continue;
            }
        }
        return sb.toString();       
    }
   
    void solve() throws IOException {
        // out.append(roman(49));
        while (true) {
            int n = Integer.parseInt(in.readLine());
            if (n == 0) {
                break;
            }
            int i = 0, v = 0, x = 0, l = 0, c = 0;
            for (int y = 1; y <= n; y++) {
                String s = roman(y);
                for (int z = 0; z < s.length(); z++) {
                    char ch = s.charAt(z);
                    switch (ch) {
                        case 'i':
                            i++;
                            break;
                        case 'v':
                            v++;
                            break;
                        case 'x':
                            x++;
                            break;
                        case 'l':
                            l++;
                            break;
                        case 'c':
                            c++;
                            break;
                        default:
                            assert (false);
                            break;
                    }
                }
            }
            out.append(String.format("%d: %d i, %d v, %d x, %d l, %d c\n", n, i, v, x, l, c));
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
