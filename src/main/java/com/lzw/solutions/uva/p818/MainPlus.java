package com.lzw.solutions.uva.p818;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class MainPlus {

    BufferedReader in;
    PrintWriter out;

    MainPlus() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    class Pair implements Comparable<Pair> {
        int a, b;

        Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Pair o) {
            return Integer.compare(a, o.a);
        }
    }

    ArrayList<Pair> list;
    int n;
    int m;

    int[] parent;
    int[] rank;
    int min;

    int find(int x) {
        if (parent[x] == x) {
            return x;
        } else {
            return find(parent[x]);
        }
    }

    void union(int x, int y) {
        int fx = find(x);
        int fy = find(y);
        if (fx == fy) {
            return;
        }
        if (rank[fx] < rank[fy]) {
            parent[fx] = fy;
        } else {
            parent[fy] = fx;
            if (rank[fx] == rank[fy]) {
                rank[fx]++;
            }
        }
    }

    void count(Map<Integer, Integer> map, int a) {
        Integer count = map.get(a);
        if (count == null) {
            count = 0;
        }
        count++;
        map.put(a, count);
    }

    void judge(boolean[] cut, int cnt) {
        parent = new int[n + 1];
        rank = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        boolean cyclic = false;

        List<Pair> pairs = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            if (!cut[i]) {
                Pair p = list.get(i);
                pairs.add(p);
            }
        }
        for (Pair p : pairs) {
            int fa = find(p.a);
            int fb = find(p.b);
            if (fa == fb) {
                cyclic = true;
                break;
            } else {
                union(p.a, p.b);
            }
        }
        if (!cyclic) {
            Set<Integer> set = new HashSet<>();
            for (int i = 1; i <= n; i++) {
                int fi = find(i);
                set.add(fi);
            }
            int group = set.size();
            boolean single = true;
            for (int g : set) {
                List<Pair> groupPairs = new ArrayList<>();
                for (Pair p : pairs) {
                    int ga = find(p.a);
                    if (ga == g) {
                        groupPairs.add(p);
                    }
                }
                if (groupPairs.size() == 0) {
                    continue;
                }
                Map<Integer, Integer> map = new HashMap<>();
                int gn = groupPairs.size();
                for (int i = 0; i < gn; i++) {
                    Pair p = groupPairs.get(i);
                    count(map, p.a);
                    count(map, p.b);
                }
                int c1 = 0, c2 = 0, c3 = 0;
                for (int key : map.keySet()) {
                    int c = map.get(key);
                    if (c == 1) {
                        c1++;
                    } else if (c == 2) {
                        c2++;
                    } else {
                        c3++;
                    }
                }
                if (c3 > 0 || c1 != 2) {
                    single = false;
                    break;
                }
            }
            if (single) {
                int link = group / 2;
                int ans;
                if (cnt >= link) {
                    ans = cnt;
                } else {
                    ans = cnt + link - cnt;
                }
                if (ans < min) {
                    min = ans;
                }
            }
        }
    }

    void solve() throws IOException {
        int caseNum = 1;
        while (true) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            n = Integer.parseInt(st.nextToken());
            if (n == 0) {
                break;
            }
            list = new ArrayList<>();
            while (st.hasMoreTokens()) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                if (a == -1 && b == -1) {
                    break;
                }
                list.add(new Pair(a, b));
            }
            m = list.size();
            min = Integer.MAX_VALUE;
            for (int i = 0; i < 1 << m; i++) {
                boolean[] cut = new boolean[m];
                int cnt = 0;
                if (i != 0) {
                    String binary = Integer.toBinaryString(i);
                    int bn = binary.length();

                    for (int j = 0; j < bn; j++) {
                        int digit = binary.charAt(bn - 1 - j) - '0';
                        boolean c = digit == 1;
                        cut[m - 1 - j] = c;
                        if (c) {
                            cnt++;
                        }
                    }
                }
                judge(cut, cnt);
            }
            out.append(String.format("Set %d: Minimum links to open is %d\n", caseNum, min));
            caseNum++;
        }
    }

    void close() throws IOException {
        in.close();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        MainPlus m = new MainPlus();
        m.solve();
        m.close();
    }
}