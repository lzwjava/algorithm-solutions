import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    List<Integer> list;

    void calPrimes() {
        int maxn = 31625;
        boolean[] prime = new boolean[maxn];
        Arrays.fill(prime, true);
        prime[0] = prime[1] = false;
        for (int i = 4; i < maxn; i += 2) {
            prime[i] = false;
        }
        list = new ArrayList<>();
        list.add(2);
        for (int i = 3; i < maxn; i += 2) {
            if (prime[i]) {
                list.add(i);
                if (i * i > 0) {
                    for (int j = i * i; j < maxn; j += i) {
                        prime[j] = false;
                    }
                }
            }
        }
    }

    class Factor {
        int p, c;

        Factor(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }

    List<Factor> calFactors(int x) {
        List<Factor> fs = new ArrayList<>();
        int sx = (int) Math.sqrt(x);
        for (int p : list) {
            if (p > sx) {
                break;
            }
            if (x % p == 0) {
                int c = 0;
                while (x % p == 0) {
                    x /= p;
                    c++;
                }
                fs.add(new Factor(p, c));
            }
            if (x == 1) {
                break;
            }
        }
        if (x != 1) {
            fs.add(new Factor(x, 1));
        }
        return fs;
    }

    void close() throws IOException {
        in.close();
        out.flush();
        out.close();
    }

    int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

    boolean distinct(int a, int b, int c) {
        return a != b && a != c && b != c;
    }

    void solve() throws IOException {
        calPrimes();
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            int n = Integer.parseInt(in.readLine());
            List<Factor> fs = calFactors(n);
            fs.add(new Factor(1, 1));
            boolean found = false;
            for (Factor f : fs) {
                for (int i = 0; i * f.p + f.p < n; i++) {
                    int a = i * f.p;
                    int b = n - f.p - a;
                    if (b >= 0 && distinct(a, b, f.p) && gcd(a, b) == f.p) {
                        found = true;
                        out.append(String.format("%d %d %d\n", a, b, f.p));
                        break;
                    }
                }
                if (found) {
                    break;
                }
            }
            t--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}