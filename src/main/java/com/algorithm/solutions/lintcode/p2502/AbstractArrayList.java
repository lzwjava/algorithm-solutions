package com.lintcode;

import java.util.LinkedList;
import java.util.List;

public class AbstractArrayList {
    private List<Integer> list;

    public AbstractArrayList() {
        list = new LinkedList<Integer>();
    }

    public void append(int element) {
        list.add(element);
    }

    public int get(int index) {
        return list.get(index);
    }

    public int size() {
        return list.size();
    }
}