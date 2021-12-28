import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
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

    class Range implements Comparable<Range> {
        int l, r;

        Range(int l, int r) {
            this.l = l;
            this.r = r;
        }

        boolean contain(Range a, Range b) {
            return b.l >= a.l && b.r <= a.r;
        }

        @Override
        public int compareTo(Range o) {
            if (contain(this, o)) {
                return -1;
            } else if (contain(o, this)) {
                return 1;
            } else {
                return Integer.compare(l, o.l);
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Range range = (Range) o;
            return l == range.l && r == range.r;
        }

        @Override
        public int hashCode() {
            return Objects.hash(l, r);
        }
    }

    Range[] rs;
    int n;

    boolean exist(Range r, int from) {
        for (int i = from; i < n; i++) {
            if (rs[i].equals(r)) {
                return true;
            }
        }
        return false;
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            n = Integer.parseInt(in.readLine());
            rs = new Range[n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                int l = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                rs[i] = new Range(l, r);
            }
            Arrays.sort(rs);

            int[] ns = new int[n];
            for (int i = n - 1; i >= 0; i--) {
                Range range = rs[i];
                if (range.l == range.r) {
                    ns[i] = range.l;
                } else {
                    int j;
                    for (j = range.l; j <= range.r; j++) {
                        boolean ok = true;
                        if (range.l <= j - 1) {
                            Range left = new Range(range.l, j - 1);
                            if (!exist(left, i + 1)) {
                                ok = false;
                                continue;
                            }
                        }
                        if (range.r + 1 <= range.r) {
                            Range right = new Range(range.r + 1, range.r);
                            if (!exist(right, i + 1)) {
                                ok = false;
                                break;
                            }
                        }
                        if (ok) {
                            break;
                        }
                    }
                    ns[i] = j;
                }
            }
            for (int i = 0; i < n; i++) {
                Range range = rs[i];
                out.append(String.format("%d %d %d\n", range.l, range.r, ns[i]));
            }
            out.append('\n');
        }
    }

}