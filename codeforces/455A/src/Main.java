import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    Map<Integer, Integer> map = new HashMap<>();

    class Item implements Comparable<Item> {
        int v;
        int c;

        Item(int v, int c) {
            this.v = v;
            this.c = c;
        }

        int points() {
            Integer c1 = map.get(v - 1);
            if (c1 == null) {
                c1 = 0;
            }
            Integer c2 = map.get(v + 1);
            if (c2 == null) {
                c2 = 0;
            }
            return v * c - (v - 1) * c1 - (v + 1) * c2;
        }

        @Override
        public int compareTo(Item o) {
            return Integer.compare(o.points(), points());
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Item item = (Item) o;
            return v == item.v;
        }

        @Override
        public int hashCode() {
            return Objects.hash(v);
        }
    }

    void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int maxn = 100005;
        int[] cnt = new int[maxn];
        for (int i = 0; i < n; i++) {
            int v = in.nextInt();
            cnt[v]++;
        }
        long[] f = new long[maxn];
        for (int i = 1; i < maxn; i++) {
            f[i] = (long) i * cnt[i];
            if (i - 2 >= 0) {
                f[i] += f[i - 2];
            }
            if (f[i] < f[i - 1]) {
                f[i] = f[i - 1];
            }
        }
        System.out.println(f[maxn - 1]);
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
