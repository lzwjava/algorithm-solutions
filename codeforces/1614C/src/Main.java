import java.io.PrintWriter;
import java.util.*;

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

    class Segment implements Comparable<Segment> {
        int l, r, x;

        Segment(int l, int r, int x) {
            this.l = l;
            this.r = r;
            this.x = x;
        }

        boolean contain(Segment a, Segment b) {
            return b.l >= a.l && b.r <= a.r;
        }

        int len() {
            return r - l + 1;
        }

        @Override
        public int compareTo(Segment o) {
            if (contain(this, o)) {
                return 1;
            } else if (contain(o, this)) {
                return -1;
            }
            return Integer.compare(len(), o.len());
        }
    }

    long ans;

    int mod = 1000000007;

    void permutation(int[] a, int st, int n, int cur, int xor) {
        if (st >= n) {
            return;
        }
        if (xor != -1) {
            ans = (ans + xor) % mod;
        }
        for (int i = st; i < n; i++) {
            int nxor;
            if (xor == -1) {
                nxor = a[i];
            } else {
                nxor = xor ^ a[i];
            }
            permutation(a, st + 1, n, cur + 1, nxor);
        }
    }

    void solve() {
        int t = in.nextInt();
        Random random = new Random();
        while (t > 0) {
            t--;
            int n = in.nextInt();
            int m = in.nextInt();
            Segment[] segments = new Segment[m];
            for (int i = 0; i < m; i++) {
                int l = in.nextInt() - 1;
                int r = in.nextInt() - 1;
                int x = in.nextInt();
                segments[i] = new Segment(l, r, x);
            }
            Arrays.sort(segments);
            int[] a = new int[n];
            Arrays.fill(a, -1);
            for (int i = 0; i < m; i++) {
                Segment s = segments[i];
                if (s.l == s.r) {
                    a[s.l] = s.x;
                } else {
                    List<Integer> list = new ArrayList<>();
                    for (int j = s.l; j <= s.r; j++) {
                        if (a[j] != -1) {
                            list.add(a[j]);
                        }
                    }
                    int len = s.len();
                    int rest = len - list.size();
                    if (rest > 0) {
                        int[] p = new int[rest];
                        while (true) {
                            for (int j = 0; j < rest; j++) {
                                p[j] = random.nextInt(20);
                            }
                            int or = p[0];
                            for (int j = 1; j < rest; j++) {
                                or = or | p[j];
                            }
                            for (int y : list) {
                                or = or | y;
                            }
                            if (or == s.x) {
                                break;
                            }
                        }
                        int q = 0;
                        for (int j = s.l; j <= s.r; j++) {
                            if (a[j] == -1) {
                                a[j] = p[q];
                                q++;
                            }
                        }
                    }
                }
            }
            ans = 0;
            permutation(a, 0, n, 0, -1);
            out.append(String.format("%d\n", ans));
            out.flush();
        }
    }
}