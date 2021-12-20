import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Random;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void close() throws IOException {
        in.close();
        out.flush();
        out.close();
    }

    long cal(long a, long b) {
        StringBuilder sb = new StringBuilder();
        while (a != 0 || b != 0) {
            int ta = (int) (a % 10);
            int tb = (int) (b % 10);
            int sa = ta + tb;
            String s = String.format("%s", sa);
            String s1 = new StringBuilder(s).reverse().toString();
            sb.append(s1);

            a /= 10;
            b /= 10;
        }
        String fs = sb.reverse().toString();
        return Long.parseLong(fs);
    }

    boolean isValid(String s) {
        if (s.length() > 2) {
            return false;
        }
        if (s.length() == 2 && s.charAt(0) == '0') {
            return false;
        }
        return true;
    }

    int calDigit(String s, int c) {
        int v = Integer.parseInt(s);
        if (v < c) {
            return -1;
        } else {
            return v - c;
        }
    }

    long toInt(int[] bs) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bs.length; i++) {
            sb.append(String.format("%d", bs[i]));
        }
        return Long.parseLong(sb.toString());
    }

    void permutation(int[] idx, int i, int n) {
        if (found) {
            return;
        }
        if (i == n) {
            int m = n + 1;
            String[] ns = new String[m];
            for (int j = 0; j < m; j++) {
                int start;
                if (j == 0) {
                    start = 0;
                } else {
                    start = idx[j - 1];
                }
                int end;
                if (j == n) {
                    end = sn;
                } else {
                    end = idx[j];
                }
                String sub = ss.substring(start, end);
                if (!isValid(sub)) {
                    return;
                }
                ns[j] = sub;
            }
            int[] bs = new int[m];
            String tas = as;
            while (tas.length() < m) {
                tas = "0" + tas;
            }
            for (int j = 0; j < m; j++) {
                int d = calDigit(ns[j], digitA(tas, j));
                if (d == -1) {
                    return;
                }
                bs[j] = d;
            }
            long b = toInt(bs);
            if (cal(a, b) == s) {
                found = true;
                this.b = b;
            }
            return;
        }
        int st;
        if (i == 0) {
            st = 1;
        } else {
            st = idx[i - 1] + 1;
        }
        for (int j = st; j < sn; j++) {
            idx[i] = j;
            permutation(idx, i + 1, n);
        }
    }

    int digitA(String tas, int j) {
        return tas.charAt(j) - '0';
    }

    long a, s, b;
    String as, ss;
    int an, sn;
    boolean found;

    long trySolve(long a, long s) {
        this.a = a;
        this.s = s;
        as = String.format("%s", a);
        ss = String.format("%s", s);
        an = as.length();
        sn = ss.length();
        found = false;
        for (int i = 1; i < sn; i++) {
            int[] idx = new int[i];
            permutation(idx, 0, i);
            if (found) {
                break;
            }
        }
        if (!found) {
            return -1;
        } else {
            return b;
        }
    }

    void test() {
        Random random = new Random();
        for (int i = 1; i < 10000; i++) {
            long a = (long) (random.nextDouble() * 1e3);
            long b = (long) (random.nextDouble() * 1e3);
            long s = cal(a, b);
            long v = trySolve(a, s);
            // 36265413  5000011 311265424
            assert (v == b);
        }
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            long a = Long.parseLong(st.nextToken());
            long s = Long.parseLong(st.nextToken());
            long ans = trySolve(a, s);
            out.append(String.format("%d\n", ans));
            t--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
//        m.test();
        m.close();
    }

}