import java.io.PrintWriter;
import java.util.Random;
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

    class Segment {
        int l, r, x;

        Segment(int l, int r, int x) {
            this.l = l;
            this.r = r;
            this.x = x;
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
            int[] a = new int[n];
            while (true) {
                for (int i = 0; i < n; i++) {
                    a[i] = random.nextInt(10);
                }
                boolean ok = true;
                for (int i = 0; i < m; i++) {
                    Segment s = segments[i];
                    int xor = s.l;
                    for (int j = s.l + 1; j <= s.r; j++) {
                        xor = xor ^ a[j];
                    }
                    if (xor == s.x) {
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    break;
                }
            }
            ans = 0;
            permutation(a, 0, n, 0, -1);
            out.append(String.format("%d\n", ans));
        }
    }
}