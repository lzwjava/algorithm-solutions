package com.algorithm.solutions.uva.p10911;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    class Point implements Comparable<Point> {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        double dist(Point b) {
            return Math.hypot(x - b.x, y - b.y);
        }

        double distToZero() {
            return Math.hypot(x, y);
        }

        @Override
        public int compareTo(Point o) {
            return Double.compare(distToZero(), o.distToZero());
        }
    }

    double minDist;

    void permutation(int[] nums, boolean[] vis, int cur, int m, int n, double dist) {
        if (dist > minDist) {
            return;
        }
        if (cur == n) {
            if (dist < minDist) {
                minDist = dist;
            }
            return;
        }
        int si;
        if (cur == 0) {
            si = 0;
        } else {
            si = nums[2 * (cur - 1)];
        }
        for (int i = si; i < m; i++) {
            if (!vis[i]) {
                vis[i] = true;
                for (int j = i + 1; j < m; j++) {
                    if (!vis[j]) {
                        vis[j] = true;
                        nums[2 * cur] = i;
                        nums[2 * cur + 1] = j;
                        double d = map[i][j];
                        permutation(nums, vis, cur + 1, m, n, dist + d);
                        vis[j] = false;
                    }
                }
                vis[i] = false;
            }
        }
    }

    Point[] points;
    double[][] map;

    void solve() throws IOException {
        int caseNum = 1;
        while (true) {
            int n = Integer.parseInt(in.readLine());
            if (n == 0) {
                break;
            }
            int m = n * 2;
            points = new Point[m];
            for (int i = 0; i < m; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                st.nextToken();
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                points[i] = new Point(x, y);
            }
            Arrays.sort(points);
            map = new double[m][m];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < m; j++) {
                    map[i][j] = points[i].dist(points[j]);
                }
            }

            minDist = Integer.MAX_VALUE;
            int[] nums = new int[m];
            boolean[] vis = new boolean[m];
            permutation(nums, vis, 0, m, n, 0);
            out.append(String.format("Case %d: %.2f\n", caseNum, minDist));
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
            outStream = new PrintStream("1.out");
            System.setIn(inStream);
            System.setOut(outStream);
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
