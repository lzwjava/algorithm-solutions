package com.lzw.solutions.uva.p531;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    ArrayList<String> readParagraph() throws IOException {
        ArrayList<String> words = new ArrayList<>();
        while (true) {
            String s = in.readLine();
            if (s == null) {
                return null;
            }
            if (s.equals("#")) {
                break;
            }
            StringTokenizer st = new StringTokenizer(s);
            while (st.hasMoreTokens()) {
                words.add(st.nextToken());
            }
        }
        return words;
    }

    int dp(int i, int j) {
        if (i < 0 || j < 0) {
            return 0;
        }
        int v = map[i][j];
        if (v != -1) {
            return v;
        }
        if (a.get(i).equals(b.get(j))) {
            v = dp(i - 1, j - 1) + 1;
        } else {
            int v1 = dp(i - 1, j);
            int v2 = dp(i, j - 1);
            v = Integer.max(v1, v2);
        }
        map[i][j] = v;
        return v;
    }

    int mapv(int i, int j) {
        if (i < 0 || j < 0) {
            return 0;
        }
        return map[i][j];
    }

    void print(int i, int j, StringBuilder sb) {
        if (i < 0 || j < 0) {
            return;
        }
        if (mapv(i, j) == mapv(i - 1, j - 1) + 1 && a.get(i).equals(b.get(j))) {
            print(i - 1, j - 1, sb);
            sb.append(a.get(i)).append(' ');
            return;
        }
        if (mapv(i, j) == mapv(i - 1, j)) {
            print(i - 1, j, sb);
        } else {
            print(i, j - 1, sb);
        }
    }

    ArrayList<String> a;
    ArrayList<String> b;
    int an;
    int bn;
    int[][] map;

    void solve() throws IOException {
        while (true) {
            a = readParagraph();
            if (a == null) {
                break;
            }
            b = readParagraph();
            an = a.size();
            bn = b.size();
            map = new int[an][bn];
            for (int i = 0; i < an; i++) {
                Arrays.fill(map[i], -1);
            }

            int ans = dp(an - 1, bn - 1);
            StringBuilder sb = new StringBuilder();
            print(an - 1, bn - 1, sb);
            //        out.append(String.format("%d\n", ans));
            out.append(String.format("%s\n", sb.toString().trim()));
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
            inStream = new FileInputStream("2.in");
            outStream = new PrintStream("1.out");
            System.setIn(inStream);
            System.setOut(outStream);
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
