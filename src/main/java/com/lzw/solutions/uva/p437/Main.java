package com.lzw.solutions.uva.p437;

import java.io.*;
import java.util.*;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    class Block implements Comparable<Block> {
        int[] dms;

        Block(int[] dms) {
            this.dms = dms;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Block block = (Block) o;
            return Arrays.equals(dms, block.dms);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(dms);
        }

        @Override
        public int compareTo(Block o) {
            return Integer.compare(o.dms[0], dms[0]);
        }
    }

    void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    void sort(int[] dms, int[] orders) {
        int len = orders.length;
        int[] rs = new int[len];
        for (int i = 0; i < len; i++) {
            rs[orders[i]] = i;
        }
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (rs[i] > rs[j]) {
                    swap(rs, i, j);
                    swap(dms, i, j);
                }
            }
        }
    }

    ArrayList<Block> genBlocks(Block b) {
        int[][] orders = new int[][]{
            {0, 1, 2}, {0, 2, 1}, {1, 2, 0}
        };
        Set<Block> set = new HashSet<Block>();
        for (int i = 0; i < orders.length; i++) {
            int[] dms = b.dms.clone();
            sort(dms, orders[i]);
            set.add(new Block(dms));
        }
        ArrayList<Block> list = new ArrayList<Block>();
        list.addAll(set);
        return list;
    }

    void solve() throws IOException {
        int caseNum = 1;
        while (true) {
            int n = Integer.parseInt(in.readLine());
            if (n == 0) {
                break;
            }
            Block[] blocks = new Block[n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                int[] dms = new int[3];
                for (int j = 0; j < 3; j++) {
                    dms[j] = Integer.parseInt(st.nextToken());
                }
                Arrays.sort(dms);
                blocks[i] = new Block(dms);
            }
            Set<Block> set = new HashSet<>();
            for (int i = 0; i < n; i++) {
                Block b = blocks[i];
                ArrayList<Block> list = genBlocks(b);
                set.addAll(list);
            }
            ArrayList<Block> all = new ArrayList<>();
            all.addAll(set);
            
            Collections.sort(all);

            int len = all.size();
            int[] d = new int[len];
            for (int i = 0; i < len; i++) {
                d[i] = all.get(i).dms[2];
            }
            for (int i = 0; i < len - 1; i++) {
                for (int j = i + 1; j < len; j++) {
                    Block bi = all.get(i);
                    Block bj = all.get(j);
                    if (bi.dms[0] > bj.dms[0] && bi.dms[1] > bj.dms[1]) {
                        if (d[j] < d[i] + bj.dms[2]) {
                            d[j] = d[i] + bj.dms[2];
                        }
                    }
                }
            }
            int ans = 0;
            for (int i = 0; i < len; i++) {
                if (ans < d[i]) {
                    ans = d[i];
                }
            }

            out.append(String.format("Case %d: maximum height = %d\n", caseNum, ans));
            caseNum++;
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
        FileInputStream inStream = null;
        PrintStream outStream = null;
        boolean isLocal = System.getenv("LOCAL_JUDGE") != null;
        if (isLocal) {
            inStream = new FileInputStream("1.in");
            outStream = new PrintStream("1.out");
            System.setIn(inStream);
            System.setOut(outStream);
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
