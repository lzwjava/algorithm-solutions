package com.lzw.solutions.uva.p10226;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        in.readLine();
        while (t > 0) {
            HashMap<String, Integer> trees = new HashMap<>();
            int total = 0;
            while (true) {
                String name = in.readLine();
                if (name == null || name.isEmpty()) {
                    break;
                }
                Integer count = trees.get(name);
                if (count == null) {
                    count = 0;
                }
                count++;
                trees.put(name, count);
                total++;
            }
            ArrayList<String> names = new ArrayList<>();
            names.addAll(trees.keySet());
            Collections.sort(names);
            for (String name : names) {
                Integer count = trees.get(name);
                out.append(String.format("%s %.4f\n", name, count * 100.0 / total));
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
