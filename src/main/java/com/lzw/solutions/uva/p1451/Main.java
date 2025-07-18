package com.lzw.solutions.uva.p1451;

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

    int n, L;
    int[] a;
    int[] sums;

    void test() {
        Random random = new Random();
        while (true) {
            int n = 10;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                sb.append(String.format("%d", random.nextInt(2)));
            }
            String s = sb.toString();
            //            s = "0111111100";
            int L = random.nextInt(n) + 1;
            Result r = cal(n, L, s);
            StringBuilder rsb = new StringBuilder();
            for (int i = r.start; i <= r.end; i++) {
                rsb.append(String.format("%c", s.charAt(i)));
            }
            String rs = rsb.toString();
            int len = r.end - r.start + 1;
            Result r1 = cal2(n, L, s);
            assert r.equals(r1);
            out.append(String.format("%s\n%s\n", s, rs));
            out.append('\n');
        }
    }

    class Result {
        int start, end;

        Result(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Result result = (Result) o;
            return start == result.start && end == result.end;
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, end);
        }
    }

    Result cal(int n, int L, String s) {
        this.n = n;
        this.n = L;
        a = new int[n];
        int sum = 0;
        sums = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = s.charAt(i) - '0';
            sum += a[i];
            sums[i] = sum;
        }
        double maxAvg = -1;
        int maxD = 0, maxI = 0;
        for (int d = L; d <= n; d++) {
            // j-i+1=d
            // n-1-i+1=d
            // n-i=d
            // i=n-d
            for (int i = 0; i <= n - d; i++) {
                int j = i + d - 1;
                int si = sum(i, j);
                double avg = si * 1.0 / d;
                if (Double.compare(avg, maxAvg) > 0) {
                    maxAvg = avg;
                    maxD = d;
                    maxI = i;
                }
            }
        }
        return new Result(maxI, maxI + maxD - 1);
    }

    Result cal1(int n, int L, String s) {
        this.n = n;
        this.L = L;
        a = new int[n];
        int sum = 0;
        sums = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = s.charAt(i) - '0';
            sum += a[i];
            sums[i] = sum;
        }
        double maxAvg = 0;
        int maxD = 0, maxI = 0;
        int maxOne = 0;
        int d = L;
        for (int i = 0; i <= n - d; i++) {
            int j = i + d - 1;
            int si = sum(i, j);
            double avg = si * 1.0 / d;
            if (Double.compare(avg, maxAvg) > 0
                    || (Double.compare(avg, maxAvg) == 0 && Double.compare(avg, 1.0) != 0 && ones(i, j) > maxOne)) {
                maxAvg = avg;
                maxD = d;
                maxI = i;
                maxOne = ones(i, j);
            }
        }
        int start = maxI;
        int end = maxI + maxD - 1;
        if (Double.compare(maxAvg, 1.0) < 0) {
            while (start - 1 >= 0 && a[start - 1] == 1) {
                start--;
            }
            while (end + 1 < n && a[end + 1] == 1) {
                end++;
            }
        }
        return new Result(start, end);
    }

    int compareAverage(int x1, int x2, int x3, int x4) {
        return sum(x1, x2) * (x4 - x3 + 1) - sum(x3, x4) * (x2 - x1 + 1);
    }

    Result cal2(int n, int L, String s) {
        this.n = n;
        this.L = L;
        a = new int[n];
        int sum = 0;
        sums = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = s.charAt(i) - '0';
            sum += a[i];
            sums[i] = sum;
        }
        int ansL = 0, ansR = L - 1;
        ArrayDeque<Integer> q = new ArrayDeque<>(n);
        for (int t = L; t <= n; t++) {
            boolean ok;
            do {
                ok = false;
                if (q.size() > 1) {
                    Iterator<Integer> iterator = q.descendingIterator();
                    int a1 = iterator.next();
                    int a2 = iterator.next();
                    if (compareAverage(a2, t - L - 1, a1, t - L - 1) >= 0) {
                        q.removeLast();
                        ok = true;
                    }
                }
            } while (ok);
            q.add(t - L);
            do {
                ok = false;
                if (q.size() > 1) {
                    Iterator<Integer> iterator = q.iterator();
                    int a1 = iterator.next();
                    int a2 = iterator.next();
                    if (compareAverage(a1, t - 1, a2, t - 1) <= 0) {
                        q.removeFirst();
                        ok = true;
                    }
                }
            } while (ok);
            int qf = q.getFirst();
            int c = compareAverage(qf, t - 1, ansL, ansR);
            if (c > 0 || (c == 0 && t - 1 - qf < ansR - ansL)) {
                ansL = qf;
                ansR = t - 1;
            }
        }
        return new Result(ansL, ansR);
    }

    int ones(int i, int j) {
        int s = 0;
        while (i - 1 >= 0 && a[i - 1] == 1) {
            s++;
            i--;
        }
        while (j + 1 < n && a[j + 1] == 1) {
            s++;
            j++;
        }
        return s;
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            StringTokenizer st = new StringTokenizer(in.readLine());
            n = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            String s = in.readLine();
            Result r = cal2(n, L, s);
            out.append(String.format("%d %d\n", r.start + 1, r.end + 1));
        }
    }

    int sum(int i, int j) {
        if (i == 0) {
            return sums[j];
        } else {
            return sums[j] - sums[i - 1];
        }
    }
}
