import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    class Particle implements Comparable<Particle> {
        int x, y, r;
        double rad;

        Particle() {
        }

        Particle(int x, int y, int r) {
            this.x = x;
            this.y = y;
            this.r = r;
        }

        @Override
        public int compareTo(Particle o) {
            return Double.compare(rad, o.rad);
        }
    }

    boolean left(Particle a, Particle b) {
        return a.x * b.y - a.y * b.x >= 0;
    }

    Particle[] op;
    int n;

    int cal() {
        if (n <= 2) {
            return 2;
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            Particle[] p = new Particle[n - 1];
            int k = 0;
            Particle pi = op[i];
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    Particle pj = op[j];
                    Particle pk = p[k] = new Particle();
                    pk.x = pj.x - pi.x;
                    pk.y = pj.y - pi.y;
                    if (pj.r == 1) {
                        pk.x = -pk.x;
                        pk.y = -pk.y;
                    }
                    pk.rad = Math.atan2(pk.y, pk.x);
                    k++;
                }
            }
            Arrays.sort(p);

            int l = 0, r = 0, cnt = 2;
            while (l < k) {
                if (r == l) {
                    r = (r + 1) % k;
                    cnt++;
                }
                while (r != l && left(p[l], p[r])) {
                    r = (r + 1) % k;
                    cnt++;
                }
                cnt--;
                l++;
                ans = Integer.max(ans, cnt);
            }
        }
        return ans;
    }

    void solve() throws IOException {
        while (true) {
            n = Integer.parseInt(in.readLine());
            if (n == 0) {
                break;
            }
            op = new Particle[n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                op[i] = new Particle(x, y, r);
            }
            int ans = cal();
            out.append(String.format("%d\n", ans));
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