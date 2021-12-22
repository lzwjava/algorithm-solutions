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

    void close() throws IOException {
        in.close();
        out.flush();
        out.close();
    }

    int maxn = 100000001;
    boolean[] isPrime;
    List<Integer> list;

    void calPrimes() {
        isPrime = new boolean[maxn];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        list = new ArrayList<>();
        list.add(2);
        for (int i = 4; i < maxn; i += 2) {
            isPrime[i] = false;
        }
        int si = (int) Math.sqrt(maxn * 10);
        for (int i = 3; i <= si; i += 2) {
            if (isPrime[i]) {
                list.add(i);
                for (int j = i * i; j < maxn; j += i) {
                    isPrime[j] = false;
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

    int cal(int n, int k) {
        if (k < 1000) {
            while (n % k != 0) {
                k--;
            }
            return n / k;
        }
        int on = n;
        List<Factor> fs = new ArrayList<>();
        boolean found = false;
        for (int x : list) {
            if (x > (int) Math.sqrt(n)) {
                break;
            }
            if (n % x == 0) {
                int c = 0;
                int p = 1;
                while (n % x == 0) {
                    n /= x;
                    p *= x;
                    c++;
                }
                fs.add(new Factor(x, c));

                if (on / p <= k) {
                    found = true;
                    break;
                }
            }
        }
        if (!found) {
            if (n != 1) {
                fs.add(new Factor(n, 1));
            }
        }
        List<Integer> ps = new ArrayList<>();
        permutation(fs, ps, 0, fs.size(), 1);
        Collections.sort(ps);
        for (int i = 0; i < ps.size(); i++) {
            int p = ps.get(i);
            if (on / p <= k) {
                return p;
            }
        }
        return on;
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

    boolean isPrime(int x) {
        if (x < maxn) {
            return isPrime[x];
        }
        for (int y : list) {
            if (x % y == 0) {
                return false;
            }
        }
        return true;
    }

    void solve() throws IOException {
        calPrimes();
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            StringTokenizer st = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int ans;
            if (n <= k) {
                ans = 1;
            } else {
                if (isPrime(n)) {
                    ans = n;
                } else {
                    ans = cal(n, k);
                }
            }
            out.append(String.format("%d\n", ans));
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}