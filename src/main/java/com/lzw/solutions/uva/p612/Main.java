package com.lzw.solutions.uva.p612;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
        int M = Integer.parseInt(in.readLine());
        while (M > 0) {
            in.readLine();
            String s = in.readLine();
            StringTokenizer st = new StringTokenizer(s);
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            ArrayList<String> strs = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                String str = in.readLine();
                strs.add(str);
            }
            Collections.sort(strs, new Comparator<String>() {

                HashMap<String, Integer> map = new HashMap<>();

                int unsortedness(String s) {
                    Integer count = map.get(s);
                    if (count != null) {
                        return count;
                    }
                    int sum = 0;
                    for (int i = 0; i < s.length() - 1; i++) {
                        char chi = s.charAt(i);
                        for (int j = i + 1; j < s.length(); j++) {
                            char chj = s.charAt(j);
                            if (chi > chj) {
                                sum++;
                            }
                        }
                    }
                    map.put(s, sum);
                    return sum;
                }

                @Override
                public int compare(String o1, String o2) {
                    int u1 = unsortedness(o1);
                    int u2 = unsortedness(o2);
                    return u1 - u2;
                }
            });
            for (String str : strs) {
                out.append(String.format("%s\n", str));
            }
            M--;
            if (M != 0) {
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

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
