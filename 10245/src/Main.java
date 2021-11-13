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

        @Override
        public int compareTo(Point o) {
            if (x != o.x) {
                return Integer.compare(x, o.x);
            } else {
                return Integer.compare(y, o.y);
            }
        }
    }

    double dist(Point a, Point b) {
        return Math.hypot(a.x - b.x, a.y - b.y);
    }

    double cal(Point[] points, int i, int j) {
        if (j - i == 1) {
            return dist(points[i], points[j]);
        } else if (j - i == 2) {
            double max = 0;
            for (int u = i; u < j; u++) {
                for (int v = u + 1; v <= j; v++) {
                    double d = dist(points[u], points[v]);
                    if (max < d) {
                        max = d;
                    }
                }
            }
            return max;
        } else {
            // >= 4
            int mid = (i + j) / 2;
            double d1 = cal(points, i, mid);
            double d2 = cal(points, mid + 1, j);
            double md = Math.min(d1, d2);

            for (int u = mid; u >= i; u--) {
                for (int v = mid + 1; v <= j; v++) {
                    Point a = points[u];
                    Point b = points[v];
                    if (b.x - a.x >= md) {
                        break;
                    }
                    if (Math.abs(b.y - a.y) >= md) {
                        continue;
                    }
                    double d = dist(a, b);
                    if (d < md) {
                        md = d;
                    }
                }
            }
            return md;
        }
    }

    void solve() throws IOException {
        while (true) {
            int n = Integer.parseInt(in.readLine());
            if (n == 0) {
                break;
            }
            Point[] points = new Point[n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                points[i] = new Point(x, y);
            }
            Arrays.sort(points);
            double ans = cal(points, 0, n - 1);
            if (Double.compare(ans, 10000) < 0) {
                out.append(String.format("%.4f\n", ans));
            } else {
                out.append("INFINITY\n");
            }
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
