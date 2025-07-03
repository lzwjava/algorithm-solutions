package com.algorithm.solutions.uva.p225;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Main {

    PrintWriter out;

    Main() {
        out = new PrintWriter(System.out);
    }

    class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    // {n,e,s, w}
    int[] dx = new int[]{0, 1, 0, -1};
    int[] dy = new int[]{1, 0, -1, 0};

    int maxn = 500;
    int base = 250;
    boolean[][] vis;
    boolean[][] bk;
    Point zeroPoint = new Point(0, 0);
    Set<String> ways;

    char dirToChar(int d) {
        return "nesw".charAt(d);
    }

    void addToList(ArrayList<Integer> path) {
        StringBuilder sb = new StringBuilder();
        for (int d : path) {
            sb.append(dirToChar(d));
        }
        ways.add(sb.toString());
    }

    void dfs(Point st, int d, int len, ArrayList<Integer> path) {
        for (int k = -1; k <= 1; k++) {
            if (k == 0) {
                continue;
            }
            int nd = (d + k + 4) % 4;

            boolean ok = true;
            for (int i = 1; i <= len; i++) {
                int px = st.x + dx[nd] * i;
                int py = st.y + dy[nd] * i;
                Point p = new Point(px, py);
                if (isBlocked(p)) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                path.add(nd);
                int lx = st.x + dx[nd] * len;
                int ly = st.y + dy[nd] * len;
                Point last = new Point(lx, ly);
                if (len == longest) {
                    if (last.equals(zeroPoint)) {
                        addToList(path);
                    }
                } else {
                    if (!isVis(last)) {
                        visPoint(last);
                        int size = longest - (len + 1) + 1;
                        int rest = (len + 1 + longest) * size / 2;

                        int q = Math.abs(last.x) + Math.abs(last.y);
                        if (q <= rest) {
                            dfs(last, nd, len + 1, path);
                        }
                        removeVis(last);
                    }
                }
                path.remove(path.size() - 1);
            }
        }
    }

    void visPoint(Point a) {
        vis[a.x + base][a.y + base] = true;
    }

    void removeVis(Point a) {
        vis[a.x + base][a.y + base] = false;
    }

    boolean isVis(Point a) {
        return vis[a.x + base][a.y + base];
    }

    void block(Point a) {
        bk[a.x + base][a.y + base] = true;
    }

    boolean isBlocked(Point a) {
        return bk[a.x + base][a.y + base];
    }

    int longest;
    int blocked;
    Point[] blockedPoints;

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            longest = in.nextInt();
            blocked = in.nextInt();
            blockedPoints = new Point[blocked];
            for (int i = 0; i < blocked; i++) {
                int x = in.nextInt();
                int y = in.nextInt();
                blockedPoints[i] = new Point(x, y);
            }
            vis = new boolean[maxn][maxn];
            bk = new boolean[maxn][maxn];
            ways = new HashSet<>();
            ArrayList<Integer> path = new ArrayList<>();
            for (Point p : blockedPoints) {
                block(p);
            }
            if (longest == 7 || longest == 8 || longest == 15 || longest == 16) {
                for (int d = 0; d < 2; d++) {
                    visPoint(zeroPoint);
                    dfs(zeroPoint, d, 1, path);
                    removeVis(zeroPoint);
                }
                ArrayList<String> list = new ArrayList<>();
                list.addAll(ways);
                Collections.sort(list);
                for (String w : list) {
                    out.append(w).append('\n');
                }
            }
            out.append(String.format("Found %d golygon(s).\n\n", ways.size()));
            t--;
        }
    }

    void close() {
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws FileNotFoundException {
//        System.setOut(new PrintStream(new FileOutputStream("1.out")));
        Main main = new Main();
        main.solve();
        main.close();
    }

}