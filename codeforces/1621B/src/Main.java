import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

    void close() {
        out.flush();
        out.close();
    }

    class Segment implements Comparable<Segment> {
        int l, r, c;

        Segment(int l, int r, int c) {
            this.l = l;
            this.r = r;
            this.c = c;
        }


        @Override
        public int compareTo(Segment o) {
            return Integer.compare(l, o.l);
        }

        @Override
        protected Segment clone() {
            return new Segment(l, r, c);
        }
    }

    boolean intersect(Segment a, Segment b) {
        if (a.compareTo(b) > 0) {
            Segment t = a;
            a = b;
            b = t;
        }
        return a.r >= b.l;
    }

    class Selection {
        int cnt;
        Segment one;
        Segment left, right;

        Segment maxSegment() {
            if (cnt == 1) {
                return one;
            } else {
                return new Segment(left.l, right.r, left.c + right.c);
            }
        }
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            int n = Integer.parseInt(in.readLine());
            Segment[] segments = new Segment[n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                int l = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                segments[i] = new Segment(l, r, c);
            }
            List<Segment> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                list.add(segments[0]);
            }
            out.append(String.format("%d\n", selection.maxSegment().c));
        }
    }
}