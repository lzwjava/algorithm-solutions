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

    class Item {
        int p, q;

        Item(int p, int q) {
            this.p = p;
            this.q = q;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Item item = (Item) o;
            return p == item.p && q == item.q;
        }

        @Override
        public int hashCode() {
            return Objects.hash(p, q);
        }
    }

    void solve() throws IOException {
        calPrimes();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        List<Factor> fs = calFactors(x);
        List<Integer> as = new ArrayList<>();
        for (int i = 0; i < fs.size(); i++) {
            Factor f = fs.get(i);
            for (int j = 0; j < f.c; j++) {
                as.add(f.p);
            }
        }
        int an = as.size();
        Set<Item> set = new HashSet<>();
        for (int i = 0; i < 1 << an; i++) {
            int p = 1;
            for (int j = 0; j < an; j++) {
                if ((i & (1 << j)) != 0) {
                    p *= as.get(j);
                }
            }
            int q = x / p;
            if (p <= n && q <= n) {
                set.add(new Item(p, q));
            }
        }
        out.append(String.format("%d\n", set.size()));
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}