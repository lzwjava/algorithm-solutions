package nowcoder.nc93;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeMap;

public class Solution {

    public int[] LRU(int[][] operators, int k) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        TreeMap<Integer, Integer> map = new TreeMap<>();

        ArrayList<Integer> ans = new ArrayList<Integer>();
        for (int[] op : operators) {
            int type = op[0];
            int key = op[1];
            if (type == 1) {
                int value = op[2];
                if (map.size() >= k) {
                    Integer target = linkedList.poll();
                    map.put(target, -1);
                }
                map.put(key, value);
                linkedList.add(key);
            } else {
                Integer v = map.get(key);
                if (v == null) {
                    v = -1;
                }
                ans.add(v);
                if (v != -1) {
                    linkedList.remove((Integer) key);
                    linkedList.add(key);
                }
            }
        }
        return ans.stream().mapToInt(i -> i).toArray();
    }

}