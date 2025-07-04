package com.lzw.solutions.uva.p11577;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
            String line = in.readLine();
            Map<Character, Integer> map = new HashMap<>();
            int maxCount = 0;
            for (int i = 0; i < line.length(); i++) {
                char ch = line.charAt(i);
                if (Character.isAlphabetic(ch)) {
                    char lch = Character.toLowerCase(ch);
                    Integer count = map.get(lch);
                    if (count == null) {
                        count = 0;
                    }
                    count++;
                    map.put(lch, count);
                    if (count > maxCount) {
                        maxCount = count;
                    }
                }
            }
            ArrayList<Character> list = new ArrayList<>();
            for (Character key : map.keySet()) {
                Integer count = map.get(key);
                if (count == maxCount) {
                    list.add(key);
                }
            }
            Collections.sort(list);
            for (char ch : list) {
                out.append(ch);
            }
            out.append('\n');
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
