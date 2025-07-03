package com.lzw.solutions.uva.p1608;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void close() throws IOException {
        in.close();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        //        m.test();
        m.close();
    }

    int[] parseArray(String s) {
        StringTokenizer st = new StringTokenizer(s);
        int n = st.countTokens();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        return a;
    }

    boolean cal(int[] a) {
        int n = a.length;
        boolean boring = false;
        for (int i = 0; i < n && !boring; i++) {
            for (int j = i; j < n; j++) {
                Map<Integer, Integer> map = new HashMap<>();
                for (int k = i; k <= j; k++) {
                    Integer cnt = map.get(a[k]);
                    if (cnt == null) {
                        cnt = 0;
                    }
                    cnt++;
                    map.put(a[k], cnt);
                }
                boolean ok = false;
                for (int key : map.keySet()) {
                    int cnt = map.get(key);
                    if (cnt == 1) {
                        ok = true;
                        break;
                    }
                }
                if (!ok) {
                    boring = true;
                    break;
                }
            }
        }
        return boring;
    }

    void count(Map<Integer, Integer> map, int v) {
        Integer cnt = map.get(v);
        if (cnt == null) {
            cnt = 0;
        }
        cnt++;
        map.put(v, cnt);
    }

    boolean cal1(int[] a) {
        int n = a.length;
        for (int len = 1; len <= n / 2; len++) {
            int ei = n - 2 * len + 1;
            for (int i = 0; i < ei; i++) {
                int j = i + len - 1;
                //                 consecutive same
                //                 i+2*len-1<n
                //                 i < n-2*len+1
                //                 if (j + len >= n) {
                //                    break;
                //                 }
                int k;
                Map<Integer, Integer> map = new HashMap<>();
                for (k = i; k <= j; k++) {
                    int nk = k + len;
                    count(map, a[k]);
                    count(map, a[nk]);
                }
                if (len == 3 && i == 0) {
                    out.append('\n');
                }
                if (unique(map)) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean unique(Map<Integer, Integer> map) {
        for (int key : map.keySet()) {
            Integer cnt = map.get(key);
            if (cnt == 1) {
                return false;
            }
        }
        return true;
    }

    boolean cal2(int[] a) {
        int n = a.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            List<Integer> ps = map.get(a[i]);
            if (ps == null) {
                ps = new ArrayList<>();
                map.put(a[i], ps);
            }
            ps.add(i);
        }
        boolean[] vis = new boolean[n];
        for (int i = 0; i < n; i++) {
            List<Integer> list = map.get(a[i]);
            if (list.size() == 1) {
                vis[i] = true;
            }
        }
        List<Range> list = new ArrayList<>();
        int p = -1;
        for (int i = 0; i < n; i++) {
            if (vis[i]) {
                if (p != -1) {
                    list.add(new Range(p, i - 1));
                }
                p = -1;
            } else {
                if (p != -1) {
                    continue;
                } else {
                    p = i;
                }
            }
        }
        if (p != -1) {
            list.add(new Range(p, n - 1));
        }
        boolean boring = false;
        for (Range range : list) {
            for (int i = range.l; i <= range.r; i++) {
                for (int j = i; j <= range.r; j++) {
                    Map<Integer, Integer> m = new HashMap<>();
                    for (int k = i; k <= j; k++) {
                        Integer cnt = m.get(a[k]);
                        if (cnt == null) {
                            cnt = 0;
                        }
                        cnt++;
                        m.put(a[k], cnt);
                    }
                    boolean ok = false;
                    for (int key : m.keySet()) {
                        int cnt = m.get(key);
                        if (cnt == 1) {
                            ok = true;
                            break;
                        }
                    }
                    if (!ok) {
                        boring = true;
                        break;
                    }
                }
            }
        }
        return boring;
    }

    class Range {
        int l, r;

        Range(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }

    int[] leftPos, rightPos;

    // [left, right], non-boring
    boolean judge(int left, int right) {
        if (left >= right) {
            return true;
        }
        int mid = (right - left) / 2;
        for (int i = 0; i <= mid; i++) {
            int leftIdx = left + i;
            int rightIdx = right - i;
            if (leftPos[leftIdx] < left && rightPos[leftIdx] > right) {
                return judge(left, leftIdx - 1) && judge(leftIdx + 1, right);
            }
            if (leftPos[rightIdx] < left && rightPos[rightIdx] > right) {
                return judge(left, rightIdx - 1) && judge(rightIdx + 1, right);
            }
        }
        return false;
    }

    boolean cal3(int[] a) {
        int n = a.length;
        leftPos = new int[n];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            Integer pos = map.get(a[i]);
            if (pos != null) {
                leftPos[i] = pos;
            } else {
                leftPos[i] = -1;
            }
            map.put(a[i], i);
        }
        rightPos = new int[n];
        map = new HashMap<>();
        for (int i = n - 1; i >= 0; i--) {
            Integer pos = map.get(a[i]);
            if (pos != null) {
                rightPos[i] = pos;
            } else {
                rightPos[i] = n;
            }
            map.put(a[i], i);
        }
        return !judge(0, n - 1);
    }

    void test() {
        // boring: two consecutive same,
        // 4 1 5 1 5
        // 4 5 5 3 2
        Random random = new Random();
        while (true) {
            int n = random.nextInt(10);
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = random.nextInt(10) + 1;
            }
            boolean boring = cal(a);
            boolean boring1 = cal3(a);
            assert boring == boring1;
        }
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            int n = Integer.parseInt(in.readLine());
            int[] a = parseArray(in.readLine());
            boolean boring = cal3(a);
            if (boring) {
                out.append("boring\n");
            } else {
                out.append("non-boring\n");
            }
        }
    }
}
