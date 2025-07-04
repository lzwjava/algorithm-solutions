package com.lzw.solutions.uva.p12504;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    HashMap<String, String> parse(String dict) {
        String key = "";
        String value = "";
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < dict.length(); i++) {
            char ch = dict.charAt(i);
            if (ch == '{') {
                continue;
            } else if (Character.isLowerCase(ch)) {
                key += ch;
            } else if (Character.isDigit(ch)) {
                value += ch;
            } else if (ch == ',' || ch == '}') {
                if (key.isEmpty() || value.isEmpty()) {
                    continue;
                }
                assert (key.length() > 0);
                assert (value.length() > 0);
                map.put(key, value);
                key = "";
                value = "";
            }
        }
        return map;
    }

    void work() {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t > 0) {
            String oldDict = sc.next();
            String newDict = sc.next();
            HashMap<String, String> oldMap = parse(oldDict);
            HashMap<String, String> newMap = parse(newDict);

            ArrayList<String> newKeys = new ArrayList<>();

            Set<String> oldKeySet = new HashSet<>(oldMap.keySet());
            Set<String> newKeySet = new HashSet<>(newMap.keySet());

            Set<String> newTmpKeySet = new HashSet<>(newKeySet);
            newTmpKeySet.removeAll(oldKeySet);

            newKeys.addAll(newTmpKeySet);
            Collections.sort(newKeys);

            Set<String> oldTmpKeySet = new HashSet<>(oldKeySet);

            oldTmpKeySet.removeAll(newKeySet);
            ArrayList<String> removedKeys = new ArrayList<>(oldTmpKeySet);
            Collections.sort(removedKeys);

            ArrayList<String> changedKeys = new ArrayList<>();
            Set<String> changedTmpKeySet = new HashSet<>(oldKeySet);
            changedTmpKeySet.retainAll(newKeySet);

            for (String key : changedTmpKeySet) {
                if (!oldMap.get(key).equals(newMap.get(key))) {
                    changedKeys.add(key);
                }
            }
            Collections.sort(changedKeys);

            if (newKeys.size() == 0 && removedKeys.size() == 0 && changedKeys.size() == 0) {
                System.out.println("No changes");
            } else {
                if (newKeys.size() > 0) {
                    System.out.println("+" + String.join(",", newKeys));
                }
                if (removedKeys.size() > 0) {
                    System.out.println("-" + String.join(",", removedKeys));
                }
                if (changedKeys.size() > 0) {
                    System.out.println("*" + String.join(",", changedKeys));
                }
            }
            System.out.println();
            t--;
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {

        new Main().work();
    }
}
