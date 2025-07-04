package com.lzw.solutions.uva.p11530;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
        String strs[] = new String[] {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz", " "};
        HashMap<Character, Integer> map = new HashMap<>();
        for (String s : strs) {
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                map.put(ch, i + 1);
            }
        }
        int caseNum = 1;
        while (t > 0) {
            String line = in.readLine();
            int total = 0;
            for (int i = 0; i < line.length(); i++) {
                char ch = line.charAt(i);
                total += map.get(ch);
            }
            out.append(String.format("Case #%d: %d\n", caseNum, total));
            caseNum++;
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
