package com.lzw.solutions.uva.p10714;

import java.io.*;
import java.util.StringTokenizer;

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
        while (t > 0) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int len = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int[] nums = new int[n];
            st = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }

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

        Main main = new Main();
        main.solve();
        main.close();
    }
}
