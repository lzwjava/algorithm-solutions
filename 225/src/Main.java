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

    Set<Point> vis;
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
        int x1 = 0, x2 = 0, y1 = 0, y2 = 0;
        for (Point p : vis) {
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
        for (Point p : vis) {
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
                System.out.print(grid[x][y]);
            }
            System.out.println();
        }
        System.out.println();
    }

    void dfs(Point st, int d, int len, ArrayList<Integer> path) {
        for (int k = -1; k <= 1; k++) {
            if (k == 0) {
                continue;
            }
            int nd = (d + k + 4) % 4;

            ArrayList<Point> points = points(st, nd, len);
            int pn = points.size();
            boolean ok = true;
            for (int i = 0; i < pn; i++) {
                Point p = points.get(i);
                if (i == pn - 1 && p.equals(zeroPoint) && len == longest) {
                    continue;
                }
                if (vis.contains(p) || isBlocked(p)) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                path.add(nd);
                vis.addAll(points);
                Point last = points.get(pn - 1);
                if (len == longest) {
                    if (last.equals(zeroPoint)) {
                        addToList(path);
                        cnt++;
                    }
                } else {
                    dfs(last, nd, len + 1, path);
                }
                vis.removeAll(points);
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
            vis = new HashSet<>();
            cnt = 0;
            ways = new ArrayList<>();
            ArrayList<Integer> path = new ArrayList<>();
            for (int d = 0; d < 2; d++) {
                vis.add(zeroPoint);
                dfs(zeroPoint, d, 1, path);
                vis.remove(zeroPoint);
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
