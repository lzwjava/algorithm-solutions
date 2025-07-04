package com.lzw.solutions.uva.p11060;

import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    Stack<Integer> stack;
    int n, m;
    ArrayList<Integer>[] list;
    ArrayList<String> beverages;

    void solve() throws IOException {
        int caseNum = 1;
        while (true) {
            String line = in.readLine();
            if (line == null) {
                break;
            }
            n = Integer.parseInt(line);
            beverages = new ArrayList<String>();
            for (int i = 0; i < n; i++) {
                beverages.add(in.readLine());
            }
            m = Integer.parseInt(in.readLine());
            list = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                list[i] = new ArrayList<Integer>();
            }
            int[] inDegrees = new int[n];
            for (int i = 0; i < m; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                String a = st.nextToken();
                String b = st.nextToken();
                int ia = beverages.indexOf(a);
                int ib = beverages.indexOf(b);
                list[ia].add(ib);
                inDegrees[ib]++;
            }
            PriorityQueue<Integer> queue = new PriorityQueue<>();
            for (int i = 0; i < n; i++) {
                if (inDegrees[i] == 0) {
                    queue.add(i);
                }
            }
            out.append(String.format("Case #%d: Dilbert should drink beverages in this order:", caseNum));
            while (!queue.isEmpty()) {
                Integer x = queue.poll();
                out.append(String.format(" %s", beverages.get(x)));
                for (Integer y : list[x]) {
                    inDegrees[y]--;
                    if (inDegrees[y] == 0) {
                        queue.add(y);
                    }
                }
            }
            out.append(".\n\n");
            in.readLine();
            caseNum++;
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
