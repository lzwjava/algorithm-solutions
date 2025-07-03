package com.lzw.solutions.uva.p11572;

import java.io.*;
import java.util.HashSet;

public class MainPlus {

    BufferedReader in;
    PrintWriter out;

    MainPlus() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int n;
    int[] vs;

    int s3(int i, int j, int mid) {
        HashSet<Integer> set = new HashSet<Integer>();
        int ni = mid;
        int nj = mid + 1;
        do {
            if (set.contains(vs[ni])) {
                break;
            }
            set.add(vs[ni]);
            ni--;
        } while (ni >= i);
        do {
            if (set.contains(vs[nj])) {
                break;
            }
            set.add(vs[nj]);
            nj++;
        } while (nj <= j);
        int s3 = nj - ni - 1;
        return s3;
    }

    int s4(int i, int j, int mid) {
        HashSet<Integer> set = new HashSet<Integer>();
        int ni = mid;
        int nj = mid + 1;
        do {
            if (set.contains(vs[nj])) {
                break;
            }
            set.add(vs[nj]);
            nj++;
        } while (nj <= j);
        do {
            if (set.contains(vs[ni])) {
                break;
            }
            set.add(vs[ni]);
            ni--;
        } while (ni >= i);
        int s4 = nj - ni - 1;
        return s4;
    }

    int cal(int i, int j) {
        if (i == j) {
            return 1;
        }
        int mid = (i + j) / 2;
        int s1 = cal(i, mid);
        int s2 = cal(mid + 1, j);
        int s3 = s3(i, j, mid);
        int s4 = s4(i, j, mid);
        int ans = Math.max(s1, s2);
        ans = Math.max(ans, s3);
        ans = Math.max(ans, s4);
//        if (i == 0) {
//            System.out.println();
//        }
        return ans;
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            n = Integer.parseInt(in.readLine());
            vs = new int[n];
            for (int i = 0; i < n; i++) {
                vs[i] = Integer.parseInt(in.readLine());
            }
            int ans;
            if (n == 0) {
                ans = 0;
            } else {
                ans = cal(0, n - 1);
            }
            out.append(String.format("%d\n", ans));
            t--;
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

        MainPlus main = new MainPlus();
        main.solve();
        main.close();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
