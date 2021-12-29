import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    Scanner in;
    PrintWriter out;

    Main() {
        in = new Scanner(System.in);
        out = new PrintWriter(System.out);
    }

    public static void main(String[] args) {
        Main m = new Main();
        m.solve();
        m.close();
    }

    void close() {
        out.flush();
        out.close();
    }

    class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    int dist(Point a, Point b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

    void solve() {
        int t = in.nextInt();
        while (t > 0) {
            t--;
            int n = in.nextInt();
            int m = in.nextInt();
            int ans;
            if (n == 1 && m == 1) {
                ans = 0;
            } else if (n == 1 || m == 1) {
                ans = 1;
            } else {
                ans = 2;
            }
//            List<Point> ps = new ArrayList<>();
//            ps.add(new Point(1, 1));
//            ps.add(new Point(3, 1));
//            for (int i = 1; i <= n; i++) {
//                for (int j = 1; j <= m; j++) {
//                    Point p = new Point(i, j);
//                    int d1 = dist(p, ps.get(0));
//                    int d2 = dist(p, ps.get(1));
//                    out.append(String.format("%d %d %d %d\n", i, j, d1, d2));
//                }
//            }

            out.append(String.format("%d\n", ans));
        }
    }
}