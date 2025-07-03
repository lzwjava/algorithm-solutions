package com.algorithm.solutions.uva.p11060;

import java.io.*;
import java.util.*;

public class MainMinus {

    BufferedReader in;
    PrintWriter out;

    MainMinus() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    Stack<Integer> stack;
    int n, m;
    ArrayList<Integer>[] list;
    ArrayList<String> beverages;

    boolean dfs(boolean[] vis, int x, int tx) {
        vis[x] = true;
        if (x == tx) {
            return true;
        }
        for (Integer y : list[x]) {
            if (!vis[y]) {
                if (dfs(vis, y, tx)) {
                    return true;
                }
            }
        }
        return false;
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

            ArrayList<Integer> ansList = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                ansList.add(i);
            }
            Collections.sort(ansList, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    if (o1 == 5 || o2 == 5) {
                        System.out.println();
                    }
                    boolean[] vis = new boolean[n];
                    boolean child = dfs(vis, o1, o2);
                    if (child) {
                        return -1;
                    }
                    Arrays.fill(vis, false);
                    boolean parent = dfs(vis, o1, o2);
                    if (parent) {
                        return 1;
                    }
                    return Integer.compare(o1, o2);
                }
            });

            out.append(String.format("Case #%d: Dilbert should drink beverages in this order:", caseNum));
            for (int i = 0; i < ansList.size(); i++) {
                out.append(String.format(" %s", beverages.get(ansList.get(i))));
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

        MainMinus main = new MainMinus();
        main.solve();
        main.close();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
