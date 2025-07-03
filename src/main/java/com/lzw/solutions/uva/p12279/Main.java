package com.lzw.solutions.uva.p12279;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void solve() throws IOException {
        int caseNum = 1;
        Scanner sc = new Scanner(System.in);
        while (true) {
            // int n = Integer.parseInt(in.readLine());
            int n = sc.nextInt();
            if (n == 0) {
                break;
            }
            // String line = in.readLine();
            // StringTokenizer st = new StringTokenizer(line);
            int count = 0;
            for (int i = 0; i < n; i++) {
                // int num = Integer.parseInt();
                int num = sc.nextInt();
                if (num > 0) {
                    count++;
                } else {
                    count--;
                }
            }
            out.append(String.format("Case %d: %d\n", caseNum, count));
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
