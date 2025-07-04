package com.lzw.solutions.uva.p11235;

import java.io.*;
import java.util.*;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int[] nums;
    int n;

    class Elem implements Comparable<Elem> {
        int num;
        int count;
        int i;
        int j;

        Elem(int num, int count, int i, int j) {
            this.num = num;
            this.count = count;
            this.i = i;
            this.j = j;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Elem elem = (Elem) o;
            return num == elem.num;
        }

        @Override
        public int hashCode() {
            return Objects.hash(num);
        }

        @Override
        public int compareTo(Elem o) {
            return Integer.compare(num, o.num);
        }
    }

    int indexOfList(ArrayList<Elem> list, int v) {
        return Collections.binarySearch(list, new Elem(v, 0, 0, 0));
    }

    class Node {
        int cnt;
        Node left;
        Node right;
        int x;
        int y;
    }

    Node buildTree(List<Elem> list, int x, int y) {
        int len = list.size();
        if (len == 1) {
            Node node = new Node();
            node.cnt = list.get(0).count;
            node.x = x;
            node.y = y;
            return node;
        }
        Node root = new Node();
        int leftCnt = 0;
        if (len / 2 > 0) {
            List<Elem> leftList = list.subList(0, len / 2);
            Node left = buildTree(leftList, x, x + len / 2 - 1);
            root.left = left;
            leftCnt = left.cnt;
        }
        int rightCnt = 0;
        if (len - len / 2 > 0) {
            List<Elem> rightList = list.subList(len / 2, len);
            Node right = buildTree(rightList, x + len / 2, y);
            root.right = right;
            rightCnt = right.cnt;
        }
        root.x = x;
        root.y = y;
        root.cnt = Integer.max(leftCnt, rightCnt);
        return root;
    }

    int find(Node node, int x, int y) {
        if (node.x == x && node.y == y) {
            return node.cnt;
        }
        if (x <= node.x && y >= node.y) {
            return node.cnt;
        }
        assert (node.x <= x && node.y >= y);
        int mid = node.left.y;
        if (y <= mid) {
            return find(node.left, x, y);
        } else if (x > mid) {
            return find(node.right, x, y);
        } else {
            int leftCnt = find(node.left, x, node.left.y);
            int rightCnt = find(node.right, node.right.x, y);
            return Integer.max(leftCnt, rightCnt);
        }
    }

    void solve() throws IOException {
        while (true) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            n = Integer.parseInt(st.nextToken());
            if (n == 0) {
                break;
            }
            int q = Integer.parseInt(st.nextToken());
            nums = new int[n];
            st = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }
            ArrayList<Elem> list = new ArrayList<>();
            int p = 0;
            for (int u = 0; u < n; u++) {
                if (u == 0) {
                    p = 1;
                } else {
                    if (nums[u] != nums[u - 1]) {
                        int j = u - 1;
                        int i = j - p + 1;
                        list.add(new Elem(nums[u - 1], p, i, j));
                        p = 1;
                    } else {
                        p++;
                    }
                }
            }
            int tj = n - 1;
            int ti = tj - p + 1;
            list.add(new Elem(nums[n - 1], p, ti, tj));
            Node root = buildTree(list, 0, list.size() - 1);

            while (q > 0) {
                st = new StringTokenizer(in.readLine());
                int i = Integer.parseInt(st.nextToken());
                int j = Integer.parseInt(st.nextToken());
                i--;
                j--;
                int vi = nums[i];
                int vj = nums[j];
                int ans = 0;
                if (vi == vj) {
                    ans = j - i + 1;
                } else {
                    int li = indexOfList(list, vi);
                    int lj = indexOfList(list, vj);
                    Elem ei = list.get(li);
                    Elem ej = list.get(lj);
                    int cnt1 = ei.j - i + 1;
                    int cnt2 = j - ej.i + 1;
                    ans = Integer.max(cnt1, cnt2);
                    if (ej.i - ei.j > 1) {
                        int cnt3 = find(root, li + 1, lj - 1);
                        ans = Integer.max(ans, cnt3);
                    }
                }
                out.append(String.format("%d\n", ans));
                q--;
            }
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

    public static void main(String[] args) throws Exception {

        Main main = new Main();
        main.solve();
        main.close();
    }
}
