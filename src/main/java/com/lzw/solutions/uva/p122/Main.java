package com.lzw.solutions.uva.p122;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

public class Main {

    class Node {
        int v;
        Node left;
        Node right;

        Node() {
            v = 0;
            left = null;
            right = null;
        }
    }

    class Pair {
        int v;
        String path;

        Pair(String s) {
            int mid = s.indexOf(",");
            int start = s.indexOf("(");
            int end = s.indexOf(")");
            v = Integer.parseInt(s.substring(start + 1, mid));
            path = s.substring(mid + 1, end);
        }
    }

    boolean insert(Node root, int v, String path) {
        if (path.equals("")) {
            if (root.v == 0) {
                root.v = v;
                return true;
            } else {
                return false;
            }
        } else {
            char ch = path.charAt(0);
            if (ch == 'L') {
                if (root.left == null) {
                    root.left = new Node();
                }
                return insert(root.left, v, path.substring(1));
            } else {
                if (root.right == null) {
                    root.right = new Node();
                }
                return insert(root.right, v, path.substring(1));
            }
        }
    }

    private boolean checkValid(Main.Node root) {
        if (root.v == 0) {
            return false;
        }
        if (root.left != null) {
            if (!checkValid(root.left)) {
                return false;
            }
        }
        if (root.right != null) {
            if (!checkValid(root.right)) {
                return false;
            }
        }
        return true;
    }

    void solve() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            ArrayList<Pair> pairs = new ArrayList<>();
            while (true) {
                String s = sc.next();
                if (s.equals("()")) {
                    break;
                }
                Pair p = new Pair(s);
                pairs.add(p);
            }
            Node root = new Node();
            boolean ok = true;
            for (Pair p : pairs) {
                boolean result = insert(root, p.v, p.path);
                if (!result) {
                    ok = false;
                    break;
                }
            }
            if (!ok || !checkValid(root)) {
                System.out.println("not complete");
                continue;
            }

            ArrayBlockingQueue<Node> queue = new ArrayBlockingQueue<>(pairs.size());
            queue.add(root);
            boolean first = true;
            while (!queue.isEmpty()) {
                Node node = queue.poll();
                if (!first) {
                    System.out.print(" ");
                } else {
                    first = false;
                }
                System.out.print(node.v);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            System.out.println();
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {
        FileInputStream inStream = null;
        PrintStream outStream = null;
        boolean isLocal = System.getProperty("os.name").equals("Mac OS X");
        if (isLocal) {
            inStream = new FileInputStream("1.in");
            // outStream = new PrintStream("1.out");
            System.setIn(inStream);
            // System.setOut(outStream);
        }

        new Main().solve();
    }
}
