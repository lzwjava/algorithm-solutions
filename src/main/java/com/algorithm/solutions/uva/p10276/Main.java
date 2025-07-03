package com.algorithm.solutions.uva.p10276;

import java.io.*;
import java.util.*;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    boolean isSquare(int x) {
        int s = (int) Math.round(Math.sqrt(x));
        return s * s == x;
    }

    String getKey(ArrayList<Integer>[] list) {
        ArrayList<Integer>[] clist = new ArrayList[list.length];
        for (int i = 0; i < list.length; i++) {
            clist[i] = new ArrayList<>();
            clist[i].addAll(list[i]);
        }
        Arrays.sort(clist, new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                int s1 = o1.size();
                int s2 = o2.size();
                if (s1 != s2) {
                    return Integer.compare(s2, s1);
                }
                for (int i = 0; i < s1; i++) {
                    int a1 = o1.get(i);
                    int a2 = o2.get(i);
                    if (a1 != a2) {
                        return Integer.compare(a1, a2);
                    }
                }
                return 0;
            }
        });
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < clist.length; i++) {
            for (int j = 0; j < clist[i].size(); j++) {
                sb.append(String.format("%d,", clist[i].get(j)));
            }
        }
        return sb.toString();
    }

    void permutation(ArrayList<Integer>[] list, Set<String> vis, int n, int x) {
        if (end) {
            return;
        }
        for (int i = 0; i < n; i++) {
            int size = list[i].size();
            if (size == 0 || (size > 0 && isSquare(list[i].get(size - 1) + x))) {
                list[i].add(x);
                String key = getKey(list);
                if (!vis.contains(key)) {
                    if (x > max) {
                        max = x;
                    } else {
                        end = true;
                        return;
                    }
                    vis.add(key);
                    permutation(list, vis, n, x + 1);
                }
                list[i].remove(Integer.valueOf(x));
            }
        }
    }

    int max;
    boolean end;

    int cal(int n) {
        ArrayList<Integer>[] list = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
        }
        Set<String> vis = new HashSet<>();
        max = 0;
        end = false;
        permutation(list, vis, n, 1);
        return max;
    }

    void solve() throws IOException {
        int[] table = new int[]{0, 1, 3, 7, 11, 17, 23, 31, 39, 49, 59, 71, 83, 97, 111, 127, 143, 161, 179, 199, 219, 241, 263, 287, 311, 337,
            363, 391, 419, 449, 479, 511, 543, 577, 611, 647, 683, 721, 759, 799, 839, 881, 923, 967, 1011, 1057, 1103, 1151, 1199, 1249, 1299};
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            int n = Integer.parseInt(in.readLine());
//            int ans = cal(n);
            int ans = table[n];
            out.append(String.format("%d\n", ans));
            t--;
        }
//        for (int i = 1; i <= 50; i++) {
//            out.append(String.format("%d,", cal(i)));
//        }
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
        FileInputStream inStream = null;
        PrintStream outStream = null;
        boolean isLocal = System.getenv("LOCAL_JUDGE") != null;
        if (isLocal) {
            inStream = new FileInputStream("1.in");
            // outStream = new PrintStream("1.out");
            System.setIn(inStream);
            // System.setOut(outStream);
        }

        Main main = new Main();
        main.solve();
        main.close();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
