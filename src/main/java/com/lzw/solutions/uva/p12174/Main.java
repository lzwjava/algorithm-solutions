package com.lzw.solutions.uva.p12174;

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

    int cal1(int s, int n, int[] x) {
        int m = Integer.min(s, n);
        int total = 0;
        for (int i = 1; i <= m; i++) {
            int group = (n - i + s - 1) / s + 1;
            int[] groupSize = new int[group];
            groupSize[0] = i;
            int tn = n - i;
            for (int j = 1; j < group; j++) {
                if (tn >= s) {
                    groupSize[j] = s;
                    tn -= s;
                } else {
                    groupSize[j] = tn;
                }
            }
            int p = 0;
            boolean ok = true;
            for (int j = 0; j < group; j++) {
                Set<Integer> set = new HashSet<>();
                for (int k = 0; k < groupSize[j]; k++) {
                    if (set.contains(x[p])) {
                        ok = false;
                        break;
                    } else {
                        set.add(x[p++]);
                    }
                }
                if (!ok) {
                    break;
                }
            }
            if (ok) {
                total++;
            }
        }
        if (n <= s && total == n) {
            total = s;
        }
        return total;
    }

    int cal2(int s, int n, int[] x) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] leftPos = new int[n];
        for (int i = 0; i < n; i++) {
            Integer p = map.get(x[i]);
            if (p == null) {
                leftPos[i] = -1;
            } else {
                leftPos[i] = p;
            }
            map.put(x[i], i);
        }
        List<Range> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (leftPos[i] != -1) {
                if (i - leftPos[i] < s) {
                    int len = i - leftPos[i];
                    int start = (leftPos[i] + 1) % s;
                    int end = (start + len - 1) % s;
                    List<Range> rs = new ArrayList<>();
                    if (end < start) {
                        rs.add(new Range(start, s - 1));
                        rs.add(new Range(0, end));
                    } else {
                        rs.add(new Range(start, end));
                    }
                    if (list.size() == 0) {
                        list = rs;
                    } else {
                        List<Range> ns = new ArrayList<>();
                        for (Range a : list) {
                            for (Range b : rs) {
                                Range inter = intersect(a, b);
                                if (inter.possible()) {
                                    ns.add(inter);
                                }
                            }
                        }
                        list = ns;
                        if (list.size() == 0) {
                            return 0;
                        }
                    }
                }
            }
        }
        if (list.size() == 0) {
            return s;
        } else {
            int ans = 0;
            for (Range r : list) {
                ans += r.len();
            }
            return ans;
        }
    }

    boolean contain(Range a, Range b) {
        return a.start <= b.start && b.end <= a.end;
    }

    Range intersect(Range a, Range b) {
        if (contain(a, b)) {
            return b;
        } else if (contain(b, a)) {
            return a;
        } else {
            int max = Integer.max(a.start, b.start);
            int min = Integer.min(a.end, b.end);
            return new Range(max, min);
        }
    }

    class Range {
        int start;
        int end;

        int len() {
            return end - start + 1;
        }

        boolean possible() {
            return start <= end;
        }

        Range(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    boolean check(int s, int n, int[] x, int pos) {
        int j = pos;
        boolean ok = true;
        while (j > 0) {
            int md = Integer.min(j + 1, s);
            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < md; i++) {
                if (set.contains(x[j])) {
                    ok = false;
                    break;
                }
                set.add(x[j]);
                j--;
            }
            if (!ok) {
                break;
            }
        }
        if (!ok) {
            return false;
        }
        j = pos + 1;
        while (j < n) {
            int md = Integer.min(n - j, s);
            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < md; i++) {
                if (set.contains(x[j])) {
                    ok = false;
                    break;
                }
                set.add(x[j]);
                j++;
            }
            if (!ok) {
                break;
            }
        }
        return ok;
    }

    void test() {
        Random random = new Random();
        while (true) {
            int s = random.nextInt(10) + 1;
            int n = random.nextInt(20) + 1;
            int[] x = new int[n];
            for (int i = 0; i < n; i++) {
                x[i] = random.nextInt(s) + 1;
            }
            int ans1 = cal1(s, n, x);
            int ans2 = cal2(s, n, x);
            assert ans1 == ans2;
        }
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            StringTokenizer st = new StringTokenizer(in.readLine());
            int s = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int[] x = parseArray(in.readLine());
            int total = cal2(s, n, x);
            out.append(String.format("%d\n", total));
        }
    }
}
