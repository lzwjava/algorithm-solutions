package com.lzw.solutions.uva.p11734;

import java.io.*;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int readInt() throws IOException {
        return Integer.parseInt(in.readLine());
    }

    void solve() throws IOException {
        int t = readInt();
        for (int u = 0; u < t; u++) {
            String a = in.readLine();
            String b = in.readLine();
            String na = a.replace(" ", "");
            String nb = b.replace(" ", "");
            out.append(String.format("Case %d: ", u + 1));
            if (a.equals(b)) {
                out.append("Yes");
            } else if (na.equals(nb)) {
                out.append("Output Format Error");
            } else {
                out.append("Wrong Answer");
            }
            out.append('\n');
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
