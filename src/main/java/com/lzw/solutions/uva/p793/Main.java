package com.lzw.solutions.uva.p793;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;
    int n;
    int parent[];
    int rank[];

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int find(int x) {
        if (x != parent[x]) {
            return find(parent[x]);
        } else {
            return x;
        }
    }

    void union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px == py) {
            return;
        }
        if (rank[px] > rank[py]) {
            parent[py] = px;
        } else {
            parent[px] = py;
            if (rank[px] == rank[py]) {
                rank[py]++;
            }
        }
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        in.readLine();
        while (t > 0) {
            n = Integer.parseInt(in.readLine());
            int s = 0, us = 0;
            parent = new int[n + 1];
            rank = new int[n + 1];
            for (int i = 0; i < n; i++) {
                parent[i + 1] = i + 1;
                rank[i + 1] = 0;
            }
            while (true) {
                String str = in.readLine();
                if (str == null || str.isEmpty()) {
                    break;
                }
                StringTokenizer st = new StringTokenizer(str);
                String op = st.nextToken();
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                char ch = op.charAt(0);
                if (ch == 'c') {
                    union(a, b);
                } else if (ch == 'q') {
                    int pa = find(a);
                    int pb = find(b);
                    if (pa == pb) {
                        s++;
                    } else {
                        us++;
                    }
                } else {
                    assert (false);
                }
            }
            out.append(String.format("%d,%d\n", s, us));
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
