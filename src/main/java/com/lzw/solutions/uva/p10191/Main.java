package com.lzw.solutions.uva.p10191;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    static class DayTime implements Comparable<DayTime> {
        int h;
        int m;

        DayTime(int h, int m) {
            this.h = h;
            this.m = m;
        }

        static DayTime fromStr(String s) {
            String[] splits = s.split(":");
            int h = Integer.parseInt(splits[0]);
            int m = Integer.parseInt(splits[1]);
            return new DayTime(h, m);
        }

        int gap(DayTime b) {
            return b.h * 60 + b.m - (h * 60 + m);
        }

        @Override
        public String toString() {
            return String.format("%02d:%02d", h, m);
        }

        @Override
        public int compareTo(DayTime o) {
            if (h != o.h) {
                return Integer.compare(h, o.h);
            } else {
                return Integer.compare(m, o.m);
            }
        }
    }

    class Duration {
        DayTime start, end;

        Duration(DayTime start, DayTime end) {
            this.start = start;
            this.end = end;
        }
    }

    String minToStr(int m) {
        int hh = m / 60;
        int mm = m % 60;
        StringBuilder sb = new StringBuilder();
        if (hh > 0) {
            sb.append(String.format("%d hours", hh));
        }
        if (hh > 0 && mm >= 0) {
            sb.append(" and ");
        }
        if (mm >= 0) {
            sb.append(String.format("%d minutes", mm));
        }
        return sb.toString();
    }

    void solve() throws IOException {
        int caseNum = 1;
        while (true) {
            String line = in.readLine();
            if (line == null) {
                break;
            }
            int n = Integer.parseInt(line);
            Duration[] durations = new Duration[n];
            for (int i = 0; i < n; i++) {
                String s = in.readLine();
                StringTokenizer st = new StringTokenizer(s);
                DayTime start = DayTime.fromStr(st.nextToken());
                DayTime end = DayTime.fromStr(st.nextToken());
                durations[i] = new Duration(start, end);
            }
            HashSet<DayTime> set = new HashSet<>();

            DayTime start = new DayTime(10, 0);
            DayTime end = new DayTime(18, 0);

            set.add(start);
            set.add(end);
            for (Duration duration : durations) {
                set.add(duration.start);
                set.add(duration.end);
            }

            ArrayList<DayTime> list = new ArrayList<>();
            list.addAll(set);
            Collections.sort(list);
            int m = list.size();
            boolean[] used = new boolean[m];
            for (Duration duration : durations) {
                int i = Collections.binarySearch(list, duration.start);
                int j = Collections.binarySearch(list, duration.end);
                for (int k = i; k < j; k++) {
                    used[k] = true;
                }
            }

            int maxGap = 0;
            DayTime maxStart = start;

            for (int i = 0; i < m - 1; i++) {
                if (!used[i]) {
                    DayTime a = list.get(i);
                    DayTime b = list.get(i + 1);
                    int gap = a.gap(b);
                    if (gap > maxGap) {
                        maxGap = gap;
                        maxStart = a;
                    }
                }
            }
            out.append(String.format(
                    "Day #%d: the longest nap starts at %s and will last for %s.\n",
                    caseNum, maxStart.toString(), minToStr(maxGap)));
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

        Main main = new Main();
        main.solve();
        main.close();
    }
}
