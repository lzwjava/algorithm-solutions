package com.algorithm.solutions.uva.p1152;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            in.readLine();
            int n = Integer.parseInt(in.readLine());
            long[][] nums = new long[4][n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                for (int j = 0; j < 4; j++) {
                    nums[j][i] = Integer.parseInt(st.nextToken());
                }
            }
            Map<Long, Integer> map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    long s = nums[0][i] + nums[1][j];
                    Integer count = map.get(s);
                    if (count == null) {
                        count = 0;
                    }
                    count++;
                    map.put(s, count);
                }
            }
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    long s = -nums[2][i] - nums[3][j];
                    Integer count = map.get(s);
                    if (count != null) {
                        cnt += count;
                    }
                }
            }
            out.append(String.format("%d\n", cnt));
            t--;
            if (t != 0) {
                out.append('\n');
            }
        }
    }

    void close() throws IOException {
        in.close();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }
}