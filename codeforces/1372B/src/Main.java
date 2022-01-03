import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

    int lcm(int a, int b) {
        return a / gcd(a, b) * b;
    }

    class Pair {
        int a, b;

        Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    Pair cal(int n) {
        int minLcm = Integer.MAX_VALUE;
        int ma = 0, mb = 0;
        for (int a = 1; a < n; a++) {
            int b = n - a;
            int lcm = lcm(a, b);
            if (lcm < minLcm) {
                minLcm = lcm;
                ma = a;
                mb = b;
            }
        }
        return new Pair(ma, mb);
    }

    ArrayList<Integer> list;

    boolean isPrime(int x) {
        if (x <= 1) {
            return false;
        }
        int sx = (int) Math.sqrt(x);
        for (Integer p : list) {
            if (p > sx) {
                break;
            }
            if (x % p == 0) {
                return false;
            }
        }
        return true;
    }

    void calPrimes() {
        int maxn = 46340;
        boolean[] primes = new boolean[maxn];
        Arrays.fill(primes, true);
        primes[0] = primes[1] = false;
        for (int i = 4; i < maxn; i += 2) {
            primes[i] = false;
        }
        int sm = (int) Math.sqrt(maxn);
        for (int i = 3; i <= sm; i += 2) {
            if (primes[i]) {
                for (int j = i * i; j < maxn; j += i) {
                    primes[j] = false;
                }
            }
        }
        list = new ArrayList<>();
        for (int i = 0; i < maxn; i++) {
            if (primes[i]) {
                list.add(i);
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

    Pair cal1(int n) {
        int a = 0, b = 0;
        if (n % 2 == 0) {
            a = b = n / 2;
        } else if (n % 3 == 0) {
            a = n / 3;
            b = n / 3 * 2;
        } else if (isPrime(n)) {
            a = 1;
            b = n - 1;
        } else {
            List<Factor> fs = calFactors(n);
            List<Integer> ps = new ArrayList<>();
            permutation(fs, ps, 0, fs.size(), 1);
            Collections.sort(ps);
            
        }
        return new Pair(a, b);
    }

    void permutation(List<Factor> fs, List<Integer> ps, int cur, int n, int product) {
        if (cur == n) {
            ps.add(product);
            return;
        }
        Factor f = fs.get(cur);
        for (int i = 0; i <= f.c; i++) {
            permutation(fs, ps, cur + 1, n, product * (int) Math.pow(f.p, i));
        }
    }


    void solve() throws IOException {
        calPrimes();
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            int n = Integer.parseInt(in.readLine());
            Pair pair = cal(n);
            out.append(String.format("%d %d\n", pair.a, pair.b));
        }
        for (int i = 1; i < 50; i++) {
            out.append(String.format("%d: ", i));
            Pair pair = cal(i);
            out.append(String.format("%d %d\n", pair.a, pair.b));
        }
    }
}