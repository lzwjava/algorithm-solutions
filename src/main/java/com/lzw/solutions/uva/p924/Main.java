package com.lzw.solutions.uva.p924;

import java.io.*;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    ArrayList<Integer>[] list;
    int e;

    void solve() throws IOException {
        e = Integer.parseInt(in.readLine());
        list = new ArrayList[e];
        for (int i = 0; i < e; i++) {
            list[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < e; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(st.nextToken());
            for (int j = 0; j < n; j++) {
                int a = Integer.parseInt(st.nextToken());
                list[i].add(a);
            }
        }
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            int s = Integer.parseInt(in.readLine());
            int[] dists = new int[e];
            Arrays.fill(dists, -1);
            dists[s] = 0;
            ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(e);
            queue.add(s);
            while (!queue.isEmpty()) {
                int u = queue.poll();
                for (Integer v : list[u]) {
                    if (dists[v] == -1) {
                        dists[v] = dists[u] + 1;
                        queue.add(v);
                    }
                }
            }
            Map<Integer, Integer> map = new HashMap<Integer, Integer>();
            int maxCount = -1;
            int distOfMaxCount = Integer.MAX_VALUE;
            int total = 0;
            for (int i = 0; i < e; i++) {
                if (i == s) {
                    continue;
                }
                int d = dists[i];
                if (d != -1) {
                    total++;
                    Integer count = map.get(d);
                    if (count == null) {
                        count = 0;
                    }
                    count++;
                    map.put(d, count);
                    if (count > maxCount || (count == maxCount && d < distOfMaxCount)) {
                        maxCount = count;
                        distOfMaxCount = d;
                    }
                }
            }
            if (total == 0) {
                out.append(String.format("%d\n", 0));
            } else {
                out.append(String.format("%d %d\n", maxCount, distOfMaxCount));
            }
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
        boolean isLocal = System.getenv("LOCAL_JUDGE") != null;
        if (isLocal) {
            inStream = new FileInputStream("2.in");
            outStream = new PrintStream("2.out");
            System.setIn(inStream);
            System.setOut(outStream);
        }

        Main main = new Main();
        main.solve();
        main.close();
    }
}
