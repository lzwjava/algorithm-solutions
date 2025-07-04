package com.lzw.solutions.uva.p834;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

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
            if (s == null) {
                break;
            }
            StringTokenizer st = new StringTokenizer(s);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ArrayList<Integer> nums = new ArrayList<>();
            while (a % b != 0) {
                int c = a / b;
                nums.add(c);
                a -= c * b;
                int t = a;
                a = b;
                b = t;
            }
            int c = a / b;
            nums.add(c);

            out.append("[");
            for (int i = 0; i < nums.size(); i++) {
                out.append(String.valueOf(nums.get(i)));
                if (i == 0) {
                    out.append(";");
                } else if (i == nums.size() - 1) {
                    continue;
                } else {
                    out.append(",");
                }
            }
            out.append("]\n");
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
