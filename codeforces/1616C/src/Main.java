import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
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

    int[] parseArray(String s) {
        StringTokenizer st = new StringTokenizer(s);
        int n = st.countTokens();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        return a;
    }

    boolean good(int[] a) {
        int n = a.length;
        for (int l = 0; l < n; l++) {
            for (int r = l; r < n; r++) {
                int sum = sum(l, r);
                int right = (a[l] + a[r]) * (r - l + 1);
                if (sum * 2 != right) {
                    return false;
                }
            }
        }
        return true;
    }

    int[] sums;

    int sum(int i, int j) {
        if (i == 0) {
            return sums[j];
        } else {
            return sums[j] - sums[i - 1];
        }
    }

    class Item {
        double d;
        double start;

        Item(double d, double start) {
            this.d = d;
            this.start = start;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Item item = (Item) o;
            return Double.compare(item.d, d) == 0 && Double.compare(item.start, start) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(d, start);
        }
    }

    boolean change(Set<Item> ds, int[] b, int op) {
        int n = b.length;
        if (b.length == 1) {
            return true;
        }
        for (Item it : ds) {
            int c = 0;
            for (int i = 0; i < n; i++) {
                double v = it.start + i * it.d;
                if (Double.compare(v, b[i]) != 0) {
                    c++;
                    if (c > op) {
                        break;
                    }
                }
            }
            if (c <= op) {
                return true;
            }
        }
        return false;
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            int n = Integer.parseInt(in.readLine());
            int[] a = parseArray(in.readLine());
            int s = 0;
            sums = new int[n];
            for (int i = 0; i < n; i++) {
                s += a[i];
                sums[i] = s;
            }
            Set<Item> ds = new HashSet<>();
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    double d = (a[j] - a[i]) * 1.0 / (j - i);
                    double start = a[i] - i * d;
                    ds.add(new Item(d, start));
                }
            }
            int left = 0;
            int right = n;
            while (left < right) {
                int mid = (left + right) / 2;
                int[] b = a.clone();
                if (change(ds, b, mid)) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            out.append(String.format("%d\n", left));
        }
    }
}