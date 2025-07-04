package com.lzw.solutions.uva.p482;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
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
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            in.readLine();

            StringTokenizer st = new StringTokenizer(in.readLine());
            ArrayList<Integer> ps = new ArrayList<>();
            while (st.hasMoreTokens()) {
                ps.add(Integer.parseInt(st.nextToken()));
            }
            ArrayList<String> nums = new ArrayList<>();
            st = new StringTokenizer(in.readLine());
            while (st.hasMoreTokens()) {
                nums.add(st.nextToken());
            }
            int len = ps.size();
            for (int i = 0; i < len - 1; i++) {
                for (int j = i + 1; j < len; j++) {
                    if (ps.get(i) > ps.get(j)) {
                        // swap
                        Integer tmp = ps.get(i);
                        ps.set(i, ps.get(j));
                        ps.set(j, tmp);

                        String num = nums.get(i);
                        nums.set(i, nums.get(j));
                        nums.set(j, num);
                    }
                }
            }
            for (int i = 0; i < nums.size(); i++) {
                out.append(String.valueOf(nums.get(i))).append('\n');
            }
            t--;
            if (t != 0) {
                out.append('\n');
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
            // outStream = new PrintStream("1.out");
            System.setIn(inStream);
            // System.setOut(outStream);
        }

        Main main = new Main();
        main.solve();
        main.close();
    }
}
