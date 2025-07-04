package com.lzw.solutions.uva.p10963;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    class Pair {
        int y1, y2;

        Pair(int y1, int y2) {
            this.y1 = y1;
            this.y2 = y2;
        }
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            in.readLine();
            int w = Integer.parseInt(in.readLine());
            ArrayList<Pair> pairs = new ArrayList<Pair>();
            for (int i = 0; i < w; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                int y1 = Integer.parseInt(st.nextToken());
                int y2 = Integer.parseInt(st.nextToken());
                pairs.add(new Pair(y1, y2));
            }
            int diff = pairs.get(0).y1 - pairs.get(0).y2;
            boolean ok = true;
            for (int i = 1; i < w; i++) {
                Pair p = pairs.get(i);
                int diff1 = p.y1 - p.y2;
                if (diff1 != diff) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                out.append("yes\n");
            } else {
                out.append("no\n");
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

        Main main = new Main();
        main.solve();
        main.close();
    }
}
