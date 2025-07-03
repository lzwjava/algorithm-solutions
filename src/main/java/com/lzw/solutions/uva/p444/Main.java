package com.lzw.solutions.uva.p444;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.HashSet;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    String reverseAscii(int code) {
        String a = String.format("%d", code);
        return new StringBuilder(a).reverse().toString();
    }

    char asciiToChar(String ascii) {
        String rs = new StringBuilder(ascii).reverse().toString();
        int a = Integer.parseInt(rs);
        return (char) a;
    }

    void solve() throws IOException {
        HashSet<String> codes = new HashSet<>();
        for (int i = 65; i <= 90; i++) {
            codes.add(reverseAscii(i));
        }
        for (int i = 97; i <= 122; i++) {
            codes.add(reverseAscii(i));
        }
        for (int i = 32; i <= 63; i++) {
            codes.add(reverseAscii(i));
        }
        while (true) {
            String s = in.readLine();
            if (s == null) {
                break;
            }
            if (s.length() == 0) {
                out.append(String.format("%s\n", s));
                continue;
            }
            char ch0 = s.charAt(0);
            boolean isDigit = Character.isDigit(ch0);
            if (!isDigit) {
                StringBuilder sb = new StringBuilder();
                for (int i = s.length() - 1; i >= 0; i--) {
                    char ch = s.charAt(i);
                    String a = String.format("%d", (int) ch);
                    String ra = new StringBuilder(a).reverse().toString();
                    sb.append(ra);
                }
                out.append(String.format("%s\n", sb.toString()));
            } else {
                StringBuilder sb = new StringBuilder();
                StringBuilder fsb = new StringBuilder();
                for (int i = 0; i < s.length(); i++) {
                    sb.append(s.charAt(i));
                    String str = sb.toString();
                    if (codes.contains(str)) {
                        fsb.append(asciiToChar(str));
                        sb.setLength(0);
                    }
                }
                out.append(String.format("%s\n", fsb.reverse().toString()));
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
