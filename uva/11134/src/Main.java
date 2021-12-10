import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int n;
    boolean found;

    class Edge implements Comparable<Edge> {
        int l, r, id;

        Edge(int l, int r) {
            this.l = l;
            this.r = r;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(r, o.r);
        }
    }

    Edge[] xs, ys;

    int lowerBound(List<Integer> list, int key) {
        int idx = Collections.binarySearch(list, key);
        if (idx < 0) {
            return Math.abs(idx) - 1;
        } else {
            while (idx > 0) {
                if (list.get(idx - 1) == key)
                    idx--;
                else
                    return idx;
            }
            return idx;
        }
    }

    void solve() throws IOException {
        while (true) {
            n = Integer.parseInt(in.readLine());
            if (n == 0) {
                break;
            }
            xs = new Edge[n];
            ys = new Edge[n];

            List<Integer> xlist = new ArrayList<>();
            List<Integer> ylist = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                int xl = Integer.parseInt(st.nextToken()) - 1;
                int yl = Integer.parseInt(st.nextToken()) - 1;
                int xr = Integer.parseInt(st.nextToken()) - 1;
                int yr = Integer.parseInt(st.nextToken()) - 1;
                xs[i] = new Edge(xl, xr);
                ys[i] = new Edge(yl, yr);
                xs[i].id = i;
                ys[i].id = i;
                xlist.add(i);
                ylist.add(i);
            }

            int maxn = 5005;

            xlist.add(maxn);
            ylist.add(maxn);

            Arrays.sort(xs);
            Arrays.sort(ys);

            boolean ok = true;
            int[] idx = new int[n];
            int[] idy = new int[n];
            for (int i = 0; i < n; i++) {
                int ix = lowerBound(xlist, xs[i].l);
                int tx = ix;
                if (xlist.get(tx) > xs[i].r) {
                    ok = false;
                    break;
                }
                idx[xs[i].id] = tx;
                xlist.remove((Integer) tx);

                int iy = lowerBound(ylist, ys[i].l);
                int ty = iy;
                if (ty > ys[i].r) {
                    ok = false;
                    break;
                }
                idy[ys[i].id] = ty;
                ylist.remove((Integer) ty);
            }
            if (ok) {
                for (int i = 0; i < n; i++) {
                    out.append(String.format("%d %d\n", idx[i] + 1, idy[i] + 1));
                }
            } else {
                out.append("IMPOSSIBLE\n");
            }
        }
    }

    void close() throws IOException {
        in.close();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }
}