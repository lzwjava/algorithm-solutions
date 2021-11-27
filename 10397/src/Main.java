import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int readInt() throws IOException {
        return Integer.parseInt(in.readLine());
    }

    class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    class Pair {
        int a, b;

        Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    class Edge implements Comparable<Edge> {
        int a, b;
        double d;

        Edge(int a, int b, double d) {
            this.a = a;
            this.b = b;
            this.d = d;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(d, o.d);
        }
    }

    int[] parent;
    int[] rank;

    int find(int x) {
        if (parent[x] == x) {
            return x;
        } else {
            return find(parent[x]);
        }
    }

    void union(int x, int y) {
        int fx = find(x);
        int fy = find(y);
        if (fx == fy) {
            return;
        } else {
            if (rank[fx] < rank[fy]) {
                parent[fx] = fy;
            } else {
                parent[fy] = fx;
                if (rank[fx] == rank[fy]) {
                    rank[fx]++;
                }
            }
        }
    }

    void solve() throws IOException {
        while (true) {
            String s = in.readLine();
            if (s == null) {
                break;
            }
            int n = Integer.parseInt(s);
            Point[] points = new Point[n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                points[i] = new Point(x, y);
            }
            int m = readInt();
            Pair[] pairs = new Pair[m];
            for (int i = 0; i < m; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;
                pairs[i] = new Pair(a, b);
            }
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
            for (Pair pair : pairs) {
                union(pair.a, pair.b);
            }
            ArrayList<Edge> edges = new ArrayList<Edge>();
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    Point pi = points[i];
                    Point pj = points[j];
                    double d = Math.hypot(pi.x - pj.x, pi.y - pj.y);
                    edges.add(new Edge(i, j, d));
                }
            }
            Collections.sort(edges);

            double ans = 0;
            for (int u = 0; u < edges.size(); u++) {
                boolean ok = true;
                int f0 = find(0);
                for (int i = 1; i < n; i++) {
                    int fi = find(i);
                    if (fi != f0) {
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    break;
                }
                Edge edge = edges.get(u);
                int fa = find(edge.a);
                int fb = find(edge.b);
                if (fa != fb) {
                    union(edge.a, edge.b);
                    ans += edge.d;
                }
            }
            out.append(String.format("%.2f\n", ans));
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
