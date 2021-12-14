import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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

    long gcd(long a, long b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

    long calGcd(boolean even) {
        int st;
        if (even) {
            st = 0;
        } else {
            st = 1;
        }
        long g = -1;
        for (int i = st; i < n; i += 2) {
            if (g == -1) {
                g = a[i];
            } else {
                g = gcd(g, a[i]);
                if (g == 1) {
                    break;
                }
            }
        }
        return g;
    }

    boolean checkGcd(boolean even, long g) {
        int st;
        if (even) {
            st = 0;
        } else {
            st = 1;
        }
        for (int i = st; i < n; i += 2) {
            if (a[i] % g == 0) {
                return false;
            }
        }
        return true;
    }

    int n;
    long[] a;

    long solve2() {
        long max = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] > max) {
                max = a[i];
            }
        }
        for (long d = max; d >= 1; d--) {
            boolean[] div = new boolean[n];
            for (int i = 0; i < n; i++) {
                div[i] = a[i] % d == 0;
            }
            boolean ok = true;
            boolean even = div[0];
            for (int i = 0; i < n; i += 2) {
                if (div[i] != even) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                if (n > 1) {
                    boolean odd = div[1];
                    if (even == odd) {
                        ok = false;
                    } else {
                        for (int i = 1; i < n; i += 2) {
                            if (div[i] != odd) {
                                ok = false;
                                break;
                            }
                        }
                    }
                }
            }
            if (ok) {
                return d;
            }
        }
        return 0;
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            n = Integer.parseInt(in.readLine());
            a = new long[n];
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Long.parseLong(st.nextToken());
            }

            long g1 = calGcd(false);
            long g2 = calGcd(true);

            long ans = 0;
            if (g1 != g2) {
                if (g1 > g2 && checkGcd(true, g1)) {
                    ans = g1;
                } else if (g2 > g1 && checkGcd(false, g2)) {
                    ans = g2;
                }
            } else {
                ans = 0;
            }
            assert (ans == solve2());
            out.append(String.format("%d\n", ans));
            t--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}