package com.lzw.solutions.uva.p429;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.concurrent.ArrayBlockingQueue;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    boolean adjacent(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }
        int d = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                d++;
                if (d > 1) {
                    break;
                }
            }
        }
        return d == 1;
    }

    class State {
        int idx;
        int dist;

        State(int idx, int dist) {
            this.idx = idx;
            this.dist = dist;
        }
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        in.readLine();
        while (t > 0) {
            ArrayList<String> words = new ArrayList<String>();
            while (true) {
                String word = in.readLine();
                if (word.equals("*")) {
                    break;
                }
                words.add(word);
            }
            int n = words.size();
            ArrayList<Integer>[] adjNodes = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                adjNodes[i] = new ArrayList<Integer>();
            }
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    boolean is = adjacent(words.get(i), words.get(j));
                    if (is) {
                        adjNodes[i].add(j);
                        adjNodes[j].add(i);
                    }
                }
            }

            while (true) {
                String line = in.readLine();
                if (line == null || line.isEmpty()) {
                    break;
                }
                StringTokenizer st = new StringTokenizer(line);
                String a = st.nextToken();
                String b = st.nextToken();
                int ia = words.indexOf(a);
                int ib = words.indexOf(b);
                ArrayBlockingQueue<State> queue = new ArrayBlockingQueue<State>(n);
                queue.add(new State(ia, 0));
                boolean[] vis = new boolean[n];
                vis[ia] = true;
                int ans = 0;
//                int[] fa = new int[n];
//                for (int i = 0; i < n; i++) {
//                    fa[i] = i;
//                }
                while (!queue.isEmpty()) {
                    State state = queue.poll();
                    for (Integer v : adjNodes[state.idx]) {
                        if (!vis[v]) {
                            if (v == ib) {
                                ans = state.dist + 1;
                                break;
                            }
                            vis[v] = true;
//                            fa[v] = state.idx;
                            queue.add(new State(v, state.dist + 1));
                        }
                    }
                    if (ans != 0) {
                        break;
                    }
                }
                out.append(String.format("%s %s %d\n", a, b, ans));
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
        boolean isLocal = System.getenv("LOCAL_JUDGE") != null;
        if (isLocal) {
            inStream = new FileInputStream("2.in");
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
