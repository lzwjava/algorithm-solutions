import java.util.*;

public class MainPlus {

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
        int[] as = new int[n];
        for (int i = 0; i < n; i++) {
            as[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            Integer c = map.get(as[i]);
            if (c == null) {
                c = 0;
            }
            c++;
            map.put(as[i], c);
        }
        ArrayList<Item> list = new ArrayList<>();
        for (Integer key : map.keySet()) {
            Integer c = map.get(key);
            list.add(new Item(key, c));
        }
        Collections.sort(list);
        int pts = 0;
        while (list.size() != 0) {
            Item item = list.get(0);
            pts += item.v * item.c;
            list.remove(item);
            list.remove(new Item(item.v + 1, 0));
            list.remove(new Item(item.v - 1, 0));
        }
        System.out.println(pts);
    }

    public static void main(String[] args) {
        new MainPlus().solve();
    }

}
