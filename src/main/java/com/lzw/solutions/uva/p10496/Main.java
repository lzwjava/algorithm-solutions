package com.lzw.solutions.uva.p10496;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    Point readPoint() throws IOException {
        StringTokenizer st = new StringTokenizer(in.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        return new Point(x, y);
    }

    int n, m;

    Point st;

    Point[] points;

    int minDist;

    void permutation(int[] nums, boolean[] vis, int cur, int k, int dist) {
        if (dist > minDist) {
            return;
        }
        if (cur == k) {
            int finalDist = dist + calDist(points[nums[cur - 1]], st);
            if (finalDist < minDist) {
                minDist = finalDist;
            }
            return;
        }
        for (int i = 0; i < k; i++) {
            if (!vis[i]) {
                vis[i] = true;
                nums[cur] = i;
                Point last;
                if (cur == 0) {
                    last = st;
                } else {
                    last = points[nums[cur - 1]];
                }
                int ndist = dist + calDist(points[i], last);
                permutation(nums, vis, cur + 1, k, ndist);
                vis[i] = false;
            }
        }
    }

    private int calDist(Point a, Point b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            Point point = readPoint();
            n = point.x;
            m = point.x;
            st = readPoint();
            int k = Integer.parseInt(in.readLine());
            points = new Point[k];
            for (int i = 0; i < k; i++) {
                points[i] = readPoint();
            }
            int[] nums = new int[k];
            boolean[] vis = new boolean[k];
            minDist = Integer.MAX_VALUE;
            permutation(nums, vis, 0, k, 0);
            out.append(String.format("The shortest path has length %d\n", minDist));
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
            inStream = new FileInputStream("1.in");
            // outStream = new PrintStream("1.out");
            System.setIn(inStream);
            // System.setOut(outStream);
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
