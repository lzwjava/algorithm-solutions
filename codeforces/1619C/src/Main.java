import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
            for (int j = 0; j < m; j++) {
                int d = calDigit(ns[j], as.charAt(j) - '0');
                if (d == -1) {
                    return;
                }
                bs[j] = d;
            }
            long b = toInt(bs);
            if (cal(a, b) == s) {
                found = true;
                out.append(String.format("%d\n", b));
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

    long a, s;
    String as, ss;
    int an, sn;
    boolean found;

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {

            StringTokenizer st = new StringTokenizer(in.readLine());
            a = Long.parseLong(st.nextToken());
            s = Long.parseLong(st.nextToken());
            as = String.format("%s", a);
            ss = String.format("%s", s);
            an = as.length();
            sn = ss.length();
            int[] idx = new int[an];
            found = false;
            permutation(idx, 0, an - 1);
            if (!found) {
                out.append("-1\n");
            }

//            long fs = cal(a, left);
//            if (fs == s) {
//                out.append(String.format("%d\n", left));
//            } else {
//                out.append("-1\n");
//            }
            t--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}