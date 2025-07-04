package com.lzw.solutions.uva.p10603;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    class Node implements Comparable<Node> {
        int v[];
        int dist;

        Node() {
            v = new int[3];
        }

        Node(int a, int b, int c) {
            v = new int[3];
            v[0] = a;
            v[1] = b;
            v[2] = c;
            dist = 0;
        }

        Node(Node n) {
            v = Arrays.copyOf(n.v, n.v.length);
            dist = n.dist;
        }

        @Override
        public int compareTo(Main.Node o) {
            return dist - o.dist;
        }
    }

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            String s = in.readLine();
            StringTokenizer st = new StringTokenizer(s);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int vols[] = new int[] {a, b, c};
            PriorityQueue<Node> pq = new PriorityQueue<>();
            Node node = new Node(0, 0, c);
            pq.add(node);
            int amount = c;
            boolean vis[][] = new boolean[amount + 1][amount + 1];
            vis[0][0] = true;
            int gap = Integer.MAX_VALUE;
            Node gapNode = null;
            while (!pq.isEmpty()) {
                Node n = pq.poll();
                for (int i = 0; i < 3; i++) {
                    if (n.v[i] <= d && d - n.v[i] < gap) {
                        gap = d - n.v[i];
                        gapNode = n;
                    }
                }
                if (gap == 0) {
                    break;
                }
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (i != j) {
                            // i to j
                            if (n.v[i] == 0 || n.v[j] == vols[j]) {
                                // i empty or j full
                                continue;
                            }
                            Node n1 = new Node(n);
                            int delta = n.v[i] < vols[j] - n.v[j] ? n.v[i] : vols[j] - n.v[j];
                            n1.v[i] = n.v[i] - delta;
                            n1.v[j] = n.v[j] + delta;
                            n1.dist = n.dist + delta;

                            if (!vis[n1.v[0]][n1.v[1]]) {
                                vis[n1.v[0]][n1.v[1]] = true;
                                pq.add(n1);
                            }
                        }
                    }
                }
            }
            out.append(String.format("%d %d\n", gapNode.dist, d - gap));
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
