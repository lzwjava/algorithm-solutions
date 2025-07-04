package com.lzw.solutions.uva.p10062;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    class Elem implements Comparable<Elem> {
        Character ch;
        Integer count;

        Elem(Character ch, Integer count) {
            this.ch = ch;
            this.count = count;
        }

        @Override
        public int compareTo(Main.Elem o) {
            if (count != o.count) {
                return count.compareTo(o.count);
            } else {
                return o.ch.compareTo(ch);
            }
        }
    }

    void solve() throws IOException {
        boolean first = true;
        while (true) {
            String line = in.readLine();
            if (line == null) {
                break;
            }
            HashMap<Character, Elem> map = new HashMap<>();
            for (int i = 0; i < line.length(); i++) {
                Character ch = line.charAt(i);
                Elem elem = map.get(ch);
                if (elem == null) {
                    elem = new Elem(ch, 0);
                    map.put(ch, elem);
                }
                elem.count++;
            }
            ArrayList<Elem> list = new ArrayList<>();
            list.addAll(map.values());
            Collections.sort(list);
            if (first) {
                first = false;
            } else {
                out.append('\n');
            }
            for (Elem elem : list) {
                out.append(String.format("%d %d\n", (int) elem.ch, elem.count));
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

        Main main = new Main();
        main.solve();
        main.close();
    }
}
