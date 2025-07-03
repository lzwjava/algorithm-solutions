package com.algorithm.solutions.uva.p11060;

import java.io.*;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class MainPlus {

    BufferedReader in;
    PrintWriter out;

    MainPlus() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    Stack<Integer> stack;
    int n, m;
    ArrayList<Integer>[] list;
    ArrayList<String> beverages;

    void dfs(boolean[] vis, int x) {
        vis[x] = true;
        for (Integer y : list[x]) {
            if (!vis[y]) {
                dfs(vis, y);
            }
        }
        stack.add(x);
    }

    void bfs(boolean[] vis, int x, Map<Integer, Integer> ds) {
        vis[x] = true;
        ds.put(x, 0);
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(vis.length);
        queue.add(x);
        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (Integer y : list[v]) {
                if (!vis[y]) {
                    vis[y] = true;
                    ds.put(y, ds.get(v) + 1);
                    queue.add(y);
                }
            }
        }
    }

    class Beverage implements Comparable<Beverage> {
        String name;
        int index;
        int dist;
        int group;

        Beverage(String name, int index, int dist, int group) {
            this.name = name;
            this.index = index;
            this.dist = dist;
            this.group = group;
        }

        @Override
        public int compareTo(Beverage o) {
            if (group == o.group) {
                return Integer.compare(dist, o.dist);
            } else {
                return Integer.compare(index, o.index);
            }
        }
    }

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
//            Collections.sort(beverages);
            m = Integer.parseInt(in.readLine());
            list = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                list[i] = new ArrayList<Integer>();
            }
            for (int i = 0; i < m; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                String a = st.nextToken();
                String b = st.nextToken();
                int ia = beverages.indexOf(a);
                int ib = beverages.indexOf(b);
                list[ia].add(ib);
            }
            stack = new Stack<Integer>();

            boolean[] vis = new boolean[n];
            for (int i = 0; i < n; i++) {
                if (!vis[i]) {
                    dfs(vis, i);
                }
            }

            ArrayList<ArrayList<Integer>> map = new ArrayList<ArrayList<Integer>>();

            ArrayList<Beverage> ansList = new ArrayList<>();

            Arrays.fill(vis, false);
            int group = 0;
            while (!stack.isEmpty()) {
                Integer top = stack.pop();
                if (!vis[top]) {
                    Map<Integer, Integer> ds = new HashMap<>();
                    bfs(vis, top, ds);

                    for (Map.Entry<Integer, Integer> entry : ds.entrySet()) {
                        String name = beverages.get(entry.getKey());
                        out.append(String.format("%s %d\n", name, entry.getValue()));
                        ansList.add(new Beverage(name, entry.getKey(), entry.getValue(), group));
                    }
                    out.append('\n');
                    group++;
                }
            }
//            for (ArrayList<Integer> list : map) {
//                for (Integer v : list) {
//                    out.append(String.format("%s\n", beverages.get(v)));
//                }
//                out.append('\n');
//            }

            Collections.sort(ansList);

//            ArrayList<Integer> ans = new ArrayList<Integer>();

//            int mapn = map.size();
//            int[] indexes = new int[mapn];
//
//            for (int i = 0; i < n; i++) {
//                int minIndex = 0;
//                int minValue = Integer.MAX_VALUE;
//                for (int j = 0; j < mapn; j++) {
//                    if (indexes[j] < map.get(j).size()) {
//                        int v = map.get(j).get(indexes[j]);
//                        if (v < minValue) {
//                            minValue = v;
//                            minIndex = j;
//                        }
//                    }
//                }
//                ans.add(minValue);
//                indexes[minIndex]++;
//            }
            out.append(String.format("Case #%d: Dilbert should drink beverages in this order:", caseNum));
            for (int i = 0; i < ansList.size(); i++) {
                out.append(String.format(" %s", ansList.get(i).name));
            }
            out.append('\n');
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
        FileInputStream inStream = null;
        PrintStream outStream = null;
        boolean isLocal = System.getenv("LOCAL_JUDGE") != null;
        if (isLocal) {
            inStream = new FileInputStream("2.in");
            // outStream = new PrintStream("1.out");
            System.setIn(inStream);
            // System.setOut(outStream);
        }

        MainPlus main = new MainPlus();
        main.solve();
        main.close();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
