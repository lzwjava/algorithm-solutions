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

    class Edge implements Comparable<Edge> {
        int a, b, c;

        Edge(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(c, o.c);
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
        int px = find(x);
        int py = find(y);
        if (px == py) {
            return;
        }
        if (rank[px] < rank[py]) {
            parent[px] = py;
        } else {
            parent[py] = px;
            if (rank[px] == rank[py]) {
                rank[px]++;
            }
        }
    }

    Edge[] edges;
    int n, m;
    boolean[] used;

    int cal(boolean[] valid, boolean flag) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
        int ans = 0;
        boolean found = false;
        for (int i = 0; i < m; i++) {
            if (!valid[i]) {
                continue;
            }
            Edge e = edges[i];
            int pa = find(e.a);
            int pb = find(e.b);
            if (pa != pb) {
                if (flag) {
                    used[i] = true;
                }
                union(e.a, e.b);
                ans += e.c;
                boolean ok = true;
                int p0 = find(0);
                for (int j = 1; j < n; j++) {
                    if (find(j) != p0) {
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    found = true;
                    break;
                }
            }
        }
        if (found) {
            return ans;
        } else {
            return Integer.MAX_VALUE;
        }
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            edges = new Edge[m];
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(in.readLine());
                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;
                int c = Integer.parseInt(st.nextToken());
                edges[i] = new Edge(a, b, c);
            }
            Arrays.sort(edges);

            boolean[] valid = new boolean[m];
            Arrays.fill(valid, true);
            used = new boolean[m];
            int v1 = cal(valid, true);
            int v2 = Integer.MAX_VALUE;
            for (int i = 0; i < m; i++) {
                if (used[i]) {
                    valid[i] = false;
                    int v = cal(valid, false);
                    if (v < v2) {
                        v2 = v;
                    }
                    valid[i] = true;
                }
            }

            out.append(String.format("%d %d\n", v1, v2));
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
