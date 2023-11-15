package nowcoder.nc93;

import java.util.*;

public class SolutionMinus {

    class Item {
        int key;
        long nano;

        Item(int key, long nano) {
            this.key = key;
            this.nano = nano;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Item item = (Item) o;
            return key == item.key;
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }
    }

    public int[] LRU(int[][] operators, int k) {
        PriorityQueue<Item> queue = new PriorityQueue<>(new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return Long.compare(o1.nano, o2.nano);
            }
        });
        TreeMap<Integer, Integer> map = new TreeMap<>();

        ArrayList<Integer> ans = new ArrayList<Integer>();
        for (int[] op : operators) {
            int type = op[0];
            int key = op[1];
            if (type == 1) {
                if (map.size() >= k) {
                    Item item = queue.poll();
                    Integer k1 = item.key;
                    map.remove(k1);
                }
                int value = op[2];
                queue.remove(new Item(key, 0));
                queue.add(new Item(key, System.nanoTime()));
                map.put(key, value);
            } else {
                queue.remove(new Item(key, 0));
                queue.add(new Item(key, System.nanoTime()));
                Integer v = map.get(key);
                if (v == null) {
                    v = -1;
                }
                ans.add(v);
            }
        }
        return ans.stream().mapToInt(i -> i).toArray();
    }

}