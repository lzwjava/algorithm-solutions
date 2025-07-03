package com.lzw.solutions.uva.p821;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.concurrent.ArrayBlockingQueue;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    class Pair {
        int a, b;

        Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    class Node {
        int id;
        int dist;

        Node(int id, int dist) {
            this.id = id;
            this.dist = dist;
        }
    }

    void solve() throws IOException {
        int caseNum = 1;
        while (true) {
            String line = in.readLine();
            StringTokenizer st = new StringTokenizer(line);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (a == 0 && b == 0) {
                break;
            }
            ArrayList<Pair> pairs = new ArrayList<>();
            Pair p = new Pair(a, b);
            pairs.add(p);
            while (st.hasMoreTokens()) {
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                if (a == 0 && b == 0) {
                    break;
                }
                Pair p1 = new Pair(a, b);
                pairs.add(p1);
            }
            HashSet<Integer> nodes = new HashSet<>();
            for (Pair pair : pairs) {
                nodes.add(pair.a);
                nodes.add(pair.b);
            }
            ArrayList<Integer> nodeList = new ArrayList<>();
            nodeList.addAll(nodes);
            Collections.sort(nodeList);

            int n = nodeList.size();
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                int v = nodeList.get(i);
                map.put(v, i);
            }
            ArrayList<Integer>[] adjList = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                adjList[i] = new ArrayList<>();
            }
            for (Pair pair : pairs) {
                int ia = map.get(pair.a);
                int ib = map.get(pair.b);
                adjList[ia].add(ib);
            }
            int sum = 0;
            for (int i = 0; i < n; i++) {
                ArrayBlockingQueue<Node> queue = new ArrayBlockingQueue<>(n);
                Node node = new Node(i, 0);
                queue.add(node);
                int[] dists = new int[n];
                Arrays.fill(dists, -1);
                dists[i] = 0;
                while (!queue.isEmpty()) {
                    Node start = queue.poll();
                    for (Integer adjNode : adjList[start.id]) {
                        if (dists[adjNode] == -1) {
                            int dist = start.dist + 1;
                            dists[adjNode] = dist;
                            Node newNode = new Node(adjNode, dist);
                            queue.add(newNode);
                        }
                    }
                }
                for (int j = 0; j < n; j++) {
                    if (j != i) {
                        sum += dists[j];
                    }
                }
            }
            int routes = n * (n - 1);
            double avgLen = sum * 1.0 / routes;
            out.append(String.format("Case %d: average length between pages = %.3f clicks\n", caseNum, avgLen));
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

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
