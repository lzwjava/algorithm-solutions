package com.lzw.solutions.uva.p481;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class Main1 {

    BufferedReader in;
    PrintWriter out;

    Main1() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    HashMap<Integer, Result> map;

    class Result {
        int len;
        Node next;

        Result(int len, Node next) {
            this.len = len;
            this.next = next;
        }
    }

    // start with i, max len subsequence
    int dp(ArrayList<Integer> nums, Node root, int i) {
        Result v = map.get(i);
        if (v != null) {
            root.next = v.next;
            return v.len;
        }
        // choose i
        int max = 1;
        for (int j = i + 1; j < nums.size(); j++) {
            if (nums.get(i) < nums.get(j)) {
                Node next = new Node();
                next.i = j;
                int len = dp(nums, next, j) + 1;
                if (len >= max) {
                    root.next = next;
                    max = len;
                }
            }
        }

        v = new Result(max, root.next);
        map.put(i, v);
        return max;
    }

    class Node {
        int i;
        Node next;

        Node() {}
    }

    void solve() throws IOException {
        ArrayList<Integer> nums = new ArrayList<>();
        while (true) {
            String line = in.readLine();
            if (line == null) {
                break;
            }
            line = line.trim();
            int num = Integer.parseInt(line);
            nums.add(num);
        }
        int n = nums.size();
        int max = 0;
        Node root = null;
        map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            Node node = new Node();
            node.i = i;
            int len = dp(nums, node, i);
            if (len >= max) {
                max = len;
                root = node;
            }
        }
        out.append(String.format("%d\n-\n", max));
        print(nums, root);
    }

    void print(ArrayList<Integer> nums, Node root) {
        out.append(String.format("%d\n", nums.get(root.i)));
        if (root.next != null) {
            print(nums, root.next);
        }
    }

    void close() throws IOException {
        if (in != null) {
            in.close();
        }
        if (out != null) {
            out.flush();
            out.close();
        }
    }

    public static void Main1(String[] args) throws Exception {

        Main1 main1 = new Main1();
        main1.solve();
        main1.close();
    }
}
