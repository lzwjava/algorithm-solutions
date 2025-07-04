package com.lzw.solutions.uva.p10905;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    String readLine() throws IOException {
        while (true) {
            String line = in.readLine();
            line = line.trim();
            if (!line.isEmpty()) {
                return line;
            }
        }
    }

    void solve() throws IOException {
        while (true) {
            int n = Integer.parseInt(readLine());
            if (n == 0) {
                break;
            }
            StringTokenizer st = new StringTokenizer(readLine());
            ArrayList<String> nums = new ArrayList<String>();
            for (int i = 0; i < n; i++) {
                nums.add(st.nextToken());
            }
            Collections.sort(nums, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    String s1 = String.format("%s%s", o1, o2);
                    String s2 = String.format("%s%s", o2, o1);
                    return s2.compareTo(s1);
                }
            });
            StringBuilder sb = new StringBuilder();
            for (String num : nums) {
                sb.append(num);
            }
            out.append(String.format("%s\n", sb.toString()));
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
        boolean isLocal = System.getenv("LOCAL_JUDGE") != null;
        if (isLocal) {
            inStream = new FileInputStream("1.in");
            outStream = new PrintStream("1.out");
            System.setIn(inStream);
            System.setOut(outStream);
        }

        Main main = new Main();
        main.solve();
        main.close();
    }
}
