package com.lzw.solutions.uva.p10037;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int readInt() throws IOException {
        return Integer.parseInt(in.readLine());
    }

    void print(StringBuilder sb, int v1, int v2) {
        if (v1 > v2) {
            int t = v1;
            v1 = v2;
            v2 = t;
        }
        sb.append(String.format("%d %d\n", v1, v2));
    }

    int dp(ArrayList<Integer> left, ArrayList<Integer> right, boolean flashlightAtLeft, StringBuilder sb) {
        assert (left.size() + right.size() == n);
        if (left.size() == 0 && !flashlightAtLeft) {
            return 0;
        }
        if (left.size() == 1) {
            if (flashlightAtLeft) {
                sb.append(String.format("%d\n", left.get(0)));
                return left.get(0);
            } else {
                int r0 = right.get(0);
                sb.append(String.format("%d\n", r0));
                print(sb, r0, left.get(0));
                return r0 + Integer.max(left.get(0), r0);
            }
        }
        if (left.size() == 2 && flashlightAtLeft) {
            print(sb, left.get(0), left.get(1));
            return Integer.max(left.get(0), left.get(1));
        }
        if (!flashlightAtLeft) {
            int r0 = right.get(0);
            ArrayList<Integer> nleft = new ArrayList<>(left);
            nleft.add(r0);
            Collections.sort(nleft);

            ArrayList<Integer> nright = new ArrayList<>(right);
            nright.remove(0);
            Collections.sort(nright);

            sb.append(String.format("%d\n", r0));

            return r0 + dp(nleft, nright, true, sb);
        }
        if (left.size() == 4) {
            int min = Integer.MAX_VALUE;
            StringBuilder tsb = null;
            for (int j = 1; j < 4; j++) {
                ArrayList<Integer> nleft = new ArrayList<>(left);
                ArrayList<Integer> nright = new ArrayList<>(right);
                int l0 = nleft.get(0);
                int lj = nleft.get(j);
                int lm = Integer.max(l0, lj);
                nright.add(l0);
                nright.add(lj);
                nleft.remove((Integer) l0);
                nleft.remove((Integer) lj);
                Collections.sort(nleft);
                Collections.sort(nright);
                StringBuilder nsb = new StringBuilder();
                print(nsb, l0, lj);
                int v = lm + dp(nleft, nright, false, nsb);
                if (v < min) {
                    min = v;
                    tsb = nsb;
                }
            }
            sb.append(tsb.toString());
            return min;
        }
        int min = Integer.MAX_VALUE;
//        if (left.size() == 3) {
//            System.out.println();
//        }
        if (right.size() == 0 || right.get(0) >= left.get(1)) {
            ArrayList<Integer> nleft = new ArrayList<>(left);
            ArrayList<Integer> nright = new ArrayList<>(right);
            int l0 = nleft.get(0);
            int l1 = nleft.get(1);
            int lm = Integer.max(l0, l1);
            nright.add(l0);
            nright.add(l1);
            nleft.remove((Integer) l0);
            nleft.remove((Integer) l1);
            Collections.sort(nleft);
            Collections.sort(nright);

            print(sb, l0, l1);

            int v = lm + dp(nleft, nright, false, sb);
            min = Integer.min(min, v);
        } else {
            ArrayList<Integer> nleft = new ArrayList<>(left);
            ArrayList<Integer> nright = new ArrayList<>(right);
            int ns = nleft.size();
            int l0 = nleft.get(ns - 1);
            int l1 = nleft.get(ns - 2);
            int lm = Integer.max(l0, l1);
            nright.add(l0);
            nright.add(l1);
            nleft.remove((Integer) l0);
            nleft.remove((Integer) l1);
            Collections.sort(nleft);
            Collections.sort(nright);

            print(sb, l0, l1);

            int v = lm + dp(nleft, nright, false, sb);
            min = Integer.min(min, v);
        }
        return min;
    }

    int n;
    int[] nums;

    void solve() throws IOException {
        int t = readInt();
        in.readLine();
        while (t > 0) {
            n = readInt();
            nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = readInt();
            }
            ArrayList<Integer> left = new ArrayList<Integer>();
            ArrayList<Integer> right = new ArrayList<Integer>();
            for (int i = 0; i < n; i++) {
                left.add(nums[i]);
            }
            StringBuilder sb = new StringBuilder();
            Collections.sort(left);
            Collections.sort(right);
            int v = dp(left, right, true, sb);
            out.append(String.format("%d\n", v));
            out.append(String.format("%s", sb.toString()));

            in.readLine();
            t--;
            if (t != 0) {
                out.append('\n');
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
        FileInputStream inStream = null;
        PrintStream outStream = null;
        boolean isLocal = System.getenv("LOCAL_JUDGE") != null;
        if (isLocal) {
            inStream = new FileInputStream("2.in");
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
