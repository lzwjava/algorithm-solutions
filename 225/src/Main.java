import java.util.*;

public class Main {

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

    void print(ArrayList<Integer> path) {
        StringBuilder sb = new StringBuilder();
        for (int d : path) {
            sb.append(dirToChar(d));
        }
        System.out.println(sb.toString());
    }
    
    void dfs(Point st, int d, int len, ArrayList<Integer> path) {
        for (int k = -1; k <= 1; k++) {
            if (k == 0) {
                continue;
            }
            int nd = (d + k + 4) % 4;

            ArrayList<Point> points = points(st, nd, len);
            int pn = points.size();
            Point last = points.get(pn - 1);
            if (last.equals(zeroPoint) && len == longest) {
                path.add(nd);
                print(path);
                cnt++;
                path.remove(path.size() - 1);
            } else if (len < longest) {
                boolean ok = true;
                for (int i = 0; i < pn; i++) {
                    Point p = points.get(i);
                    if (vis.contains(p) || isBlocked(p)) {
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    path.add(nd);
                    vis.addAll(points);
                    dfs(last, nd, len + 1, path);
                    vis.removeAll(points);
                    path.remove(path.size() - 1);
                }
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
            vis.add(zeroPoint);
            ArrayList<Integer> path = new ArrayList<>();
            for (int d = 0; d < 4; d++) {
                dfs(zeroPoint, d, 1, path);
            }
            System.out.println(String.format("Found %d golygon(s).", cnt));
            t--;
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
