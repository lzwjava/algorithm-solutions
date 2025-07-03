package com.lzw.solutions.uva.p437;

import java.io.*;
import java.util.*;

public class Main1 {

    BufferedReader in;
    PrintWriter out;

    Main1() {
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

        public int strictCompareTo(Block o) {
            if (dms[0] == o.dms[0] && dms[1] == o.dms[1]) {
                if (dms[2] > o.dms[2]) {
                    return -1;
                } else if (dms[2] < o.dms[2]) {
                    return 1;
                } else {
                    return 0;
                }
            }
            if (dms[0] >= o.dms[0] && dms[1] >= o.dms[1]) {
                return -1;
            } else if (dms[0] <= o.dms[0] && dms[1] <= o.dms[1]) {
                return 1;
            } else {
                return 0;
            }
        }

        @Override
        public int compareTo(Block o) {
            int v = strictCompareTo(o);
            if (v != 0) {
                return v;
            } else {
                int s1 = dms[0] * dms[1];
                int s2 = o.dms[0] * o.dms[1];
                return Integer.compare(s2, s1);
            }
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
        int[][] orders = new int[][] {{0, 1, 2}, {0, 2, 1}, {1, 2, 0}};
        Set<Block> set = new HashSet<Block>();
        for (int i = 0; i < orders.length; i++) {
            int[] dms = b.dms.clone();
            sort(dms, orders[i]);
            set.add(new Block(dms));
        }
        ArrayList<Block> llist = new ArrayList<Block>();
        llist.addAll(set);
        return llist;
    }

    int hashCodePlus(int x, int y) {
        return x * 31 + y;
    }

    int dp(ArrayList<Block> all, int x, int y, int[] fa) {
        if (x == 0 || y == 0) {
            return 0;
        }
        int ans = 0;
        ArrayList<Block> list = new ArrayList<>();
        for (Block b : all) {
            if (b.dms[0] < x && b.dms[1] < y) {
                list.add(b);
            }
        }
        Collections.sort(list);
        if (list.size() == 0) {
            return ans;
        } else {
            ArrayList<Block> subList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                subList.add(list.get(i));
                //                boolean ok = true;
                //                for (int j = i + 1; j < list.size(); j++) {
                //                    if (list.get(i).strictCompareTo(list.get(j)) != -1) {
                //                        ok = false;
                //                        break;
                //                    }
                //                }
                //                if (ok) {
                //                    break;
                //                }
            }
            for (Block b : subList) {
                int v = dp(list, b.dms[0], b.dms[1], fa) + b.dms[2];
                if (v > ans) {
                    //                    fa[hashCodePlus(x, y)] = hashCodePlus(nx, ny);
                    ans = v;
                }
            }
        }

        return ans;
    }

    void printCode(int code) {
        int x = code / 31;
        int y = code % 31;
        out.append(String.format("(%d,%d) ", x, y));
    }

    void print(int[] fa, int code) {
        if (fa[code] == 0) {
            printCode(code);
        } else {
            print(fa, fa[code]);
            printCode(code);
        }
    }

    void solve() throws IOException {
        int caseNum = 1;
        while (true) {
            int n = Integer.parseInt(in.readLine());
            if (n == 0) {
                break;
            }
            Block[] blocks = new Block[n];
            int maxi = 0;
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                int[] dms = new int[3];
                for (int j = 0; j < 3; j++) {
                    dms[j] = Integer.parseInt(st.nextToken());
                }
                Arrays.sort(dms);
                blocks[i] = new Block(dms);
                if (dms[2] > maxi) {
                    maxi = dms[2];
                }
            }
            Set<Block> set = new HashSet<>();
            for (int i = 0; i < n; i++) {
                Block b = blocks[i];
                ArrayList<Block> list = genBlocks(b);
                set.addAll(list);
            }
            int[] fa = new int[1000];
            maxi++;
            ArrayList<Block> all = new ArrayList<>();
            all.addAll(set);
            int ans = dp(all, maxi, maxi, fa);
            //            int code = hashCodePlus(maxi, maxi);
            //            print(fa, code);
            //            out.append('\n');
            //            System.out.println(caseNum);
            //            int len = all.size();
            //            int[] d = new int[len];
            //            for (int i = 0; i < len; i++) {
            //                d[i] = all.get(i).dms[3];
            //            }

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

        Main1 main = new Main1();
        main.solve();
        main.close();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
