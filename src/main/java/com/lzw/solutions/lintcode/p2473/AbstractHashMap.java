package com.lzw.solutions.lintcode.p2473;

import java.util.HashMap;
import java.util.Set;

public class AbstractHashMap {
    private HashMap<Integer, Integer> map;

    public AbstractHashMap(int baseSize) {
        map = new HashMap<Integer, Integer>(baseSize);
    }

    public void put(int key, int value) {
        if (map.containsKey(key))
            map.replace(key, value);
        else
            map.put(key, value);
    }

    public int get(int key) {
        return map.get(key);
    }

    public int size() {
        return map.keySet().size();
    }

    public Set<Integer> keySet() {
        return map.keySet();
    }
}