import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
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
        int id, l, r, c;

        Segment(int id, int l, int r, int c) {
            this.id = id;
            this.l = l;
            this.r = r;
            this.c = c;
        }


        @Override
        public int compareTo(Segment o) {
            if (l != o.l) {
                return Integer.compare(l, o.l);
            } else {
                return Integer.compare(c, o.c);
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
                segments[i] = new Segment(i, l, r, c);
            }
            PriorityQueue<Segment> left = new PriorityQueue<>();
            PriorityQueue<Segment> right = new PriorityQueue<>(new Comparator<Segment>() {
                @Override
                public int compare(Segment o1, Segment o2) {
                    if (o1.r != o2.r) {
                        return Integer.compare(o2.r, o1.r);
                    } else {
                        return Integer.compare(o1.c, o2.c);
                    }
                }
            });
            for (int i = 0; i < n; i++) {
                left.add(segments[i]);
                right.add(segments[i]);
                Segment leftPeek = left.peek();
                Segment rightPeek = right.peek();
                int coin;
                if (leftPeek.id == rightPeek.id) {
                    coin = leftPeek.c;
                } else {
                    if (rightPeek.l == leftPeek.l) {
                        coin = rightPeek.c;
                    } else if (leftPeek.r == rightPeek.r) {
                        coin = leftPeek.c;
                    } else {
                        coin = leftPeek.c + rightPeek.c;
                    }
                }
                out.append(String.format("%d\n", coin));
            }
        }
    }
}