package com.lzw.solutions.uva.p10282;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void solve() throws IOException {
        HashMap<String, String> dict = new HashMap<>();
        while (true) {
            String s = in.readLine();
            if (s.isEmpty()) {
                break;
            }
            StringTokenizer st = new StringTokenizer(s);
            String a = st.nextToken();
            String b = st.nextToken();
            dict.put(b, a);
        }
        while (true) {
            String s = in.readLine();
            if (s == null) {
                break;
            }
            String a = dict.get(s);
            if (a != null) {
                out.append(a);
            } else {
                out.append("eh");
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
