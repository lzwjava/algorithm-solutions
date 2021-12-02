import java.io.PrintWriter;
import java.util.*;

public class Main {

    PrintWriter out;
    Scanner in;

    Main() {
        in = new Scanner(System.in);
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
    Point zeroPoint = new Point(0, 0);
    int cnt;
    List<String> ways = new ArrayList<>();

    ArrayList<Point> points(Point st, int d, int len) {
        ArrayList<Point> list = new ArrayList<>();
        for (int i = 1; i <= len; i++) {
            int px = st.x + dx[d] * i;
            int py = st.y + dy[d] * i;
            Point p = new Point(px, py);
            list.add(p);
        }
        return list;
    }

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

    void printPoints() {
        List<Point> points = allVisPoints();
        int x1 = 0, x2 = 0, y1 = 0, y2 = 0;
        for (Point p : points) {
            x1 = Integer.min(x1, p.x);
            y1 = Integer.max(y1, p.y);
            x2 = Integer.max(x2, p.x);
            y2 = Integer.min(y2, p.y);
        }
        int xl = x2 - x1 + 3;
        int yl = y1 - y2 + 3;
        char[][] grid = new char[xl][yl];
        for (int x = 0; x < xl; x++) {
            Arrays.fill(grid[x], '.');
        }
        for (Point p : points) {
            int dx = y1 - p.y;
            int dy = p.x - x1;
            if (p.x == 0 && p.y == 0) {
                grid[dx][dy] = '0';
            } else {
                grid[dx][dy] = 'x';
            }
        }
        for (int x = 0; x < xl; x++) {
            for (int y = 0; y < yl; y++) {
                out.append(grid[x][y]);
            }
            out.append('\n');
        }
        out.append('\n');
    }

    void dfs(Point st, int d, int len, ArrayList<Integer> path) {
        for (int k = -1; k <= 1; k++) {
            if (k == 0) {
                continue;
            }
            int nd = (d + k + 4) % 4;

            boolean ok = true;

            ArrayList<Point> points = new ArrayList<>();
            for (int i = 1; i <= len; i++) {
                int px = st.x + dx[d] * i;
                int py = st.y + dy[d] * i;
                Point p = new Point(px, py);
                if (i == len && p.equals(zeroPoint) && len == longest) {
                    //
                } else {
                    if (isVis(p)) {
                        ok = false;
                        break;
                    }
                }
                points.add(p);
            }

            int pn = points.size();
            
            if (ok) {
                path.add(nd);
                visPoints(points);
                Point last = points.get(pn - 1);
                if (len == longest) {
                    if (last.equals(zeroPoint)) {
                        addToList(path);
                        cnt++;
                    }
                } else {
                    dfs(last, nd, len + 1, path);
                }
                removeVisPoints(points);
                path.remove(path.size() - 1);
            }
        }
    }

    boolean isBlocked(Point a) {
        for (int i = 0; i < blockedPoints.length; i++) {
            Point b = blockedPoints[i];
            if (b.equals(a)) {
                return true;
            }
        }
        return false;
    }

    List<Point> allVisPoints() {
        List<Point> points = new ArrayList<>();
        for (int x = 0; x < maxn; x++) {
            for (int y = 0; y < maxn; y++) {
                if (vis[x][y]) {
                    int ox = x - base;
                    int oy = y - base;
                    points.add(new Point(ox, oy));
                }
            }
        }
        return points;
    }

    void visPoints(ArrayList<Point> ps) {
        for (Point a : ps) {
            visPoint(a);
        }
    }

    void removeVisPoints(ArrayList<Point> ps) {
        for (Point a : ps) {
            removeVis(a);
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

    int longest;
    int blocked;
    Point[] blockedPoints;

    void setBlocked() {
        for (Point p : blockedPoints) {
            visPoint(p);
        }
    }

    void solve() {
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
            cnt = 0;
            ways = new ArrayList<>();
            setBlocked();
            ArrayList<Integer> path = new ArrayList<>();
            for (int d = 0; d < 2; d++) {
                visPoint(zeroPoint);
                dfs(zeroPoint, d, 1, path);
                removeVis(zeroPoint);
            }
            Collections.sort(ways);
            for (String w : ways) {
                out.append(w).append('\n');
            }
            out.append(String.format("Found %d golygon(s).\n\n", cnt));
            t--;
        }
    }

    void close() {
        out.flush();
        out.close();
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
        main.close();
    }

}