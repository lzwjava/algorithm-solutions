package com.lzw.solutions.uva.p128;

import java.io.*;

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
            if (line.equals("#")) {
                break;
            }
            char[] chs = line.toCharArray();
            int g = 34943;
            int crc = 0;
            for (char ch : chs) {
                crc = (crc * 256 + ch) % g;
            }
            int a = ((g - ((crc * 256) % g * 256)) % g + g) % g;
            out.append(String.format("%02X %02X\n", a / 256, a % 256));
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

        Main main = new Main();
        main.solve();
        main.close();
    }
}
