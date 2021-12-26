import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
//        m.test();
        m.close();
    }

    int period(String s) {
        int n = s.length();
        for (int k = 1; k <= n; k++) {
            boolean ok = true;
            for (int i = 0; i < n - k; i++) {
                if (s.charAt(i) != s.charAt(i + k)) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                return k;
            }
        }
        return -1;
    }

    void permutation(String s1, List<String> ps, int cur, int m) {
        if (cur == m) {
            if (period(s1) == m) {
                ps.add(s1);
            }
            return;
        }
        permutation(s1 + "0", ps, cur + 1, m);
        permutation(s1 + "1", ps, cur + 1, m);
    }

    int mink;
    String minks;

    boolean subsequence(String a, String b) {
        int an = a.length();
        int bn = b.length();
        int p = 0;
        for (int i = 0; i < bn; i++) {
            char c = b.charAt(i);
            while (p < an && a.charAt(p) != c) {
                p++;
            }
            if (p == an) {
                return false;
            }
            p++;
        }
        return true;
    }

    int cnt;

    void cal(char[] chs, String np, String t, boolean[] vis, int i, int m) {
        if (mink == 1) {
            return;
        }
//        if (cnt > 3000) {
//            return;
//        }
        if (i == m) {
            String ns = new String(chs);
            if (subsequence(ns, t)) {
                int k = period(ns);
                if (k < mink) {
                    mink = k;
                    minks = ns;
                    if (mink == 2) {
                        out.append('\n');
                    }
                    cnt = 0;
                } else {
                    cnt++;
                }
            }
            return;
        }
        for (int j = 0; j < m; j++) {
            if (!vis[j]) {
                vis[j] = true;
                chs[i] = np.charAt(j);
                cal(chs, np, t, vis, i + 1, m);
                vis[j] = false;
            }
        }
    }

    class Item {
        int c0, c1;

        Item(int c0, int c1) {
            this.c0 = c0;
            this.c1 = c1;
        }
    }

    Item count(String s) {
        int c0 = 0, c1 = 0;
        for (char c : s.toCharArray()) {
            if (c == '0') {
                c0++;
            } else if (c == '1') {
                c1++;
            }
        }
        return new Item(c0, c1);
    }

    String makeLen(String p, int k, int n) {
        String s = p;
        for (int i = 0; i < n - k; i++) {
            char c = s.charAt(s.length() - k);
            s += c;
        }
        return s;
    }

    String solve1(String t) {
        int n = t.length();
        Item it = count(t);
        boolean found = false;
        for (int k = 1; k <= n && !found; k++) {
            List<String> ps = new ArrayList<>();
            permutation("", ps, 0, k);
            for (String p : ps) {
                String start = makeLen(p, k, n);
                Item is = count(start);
                if ((it.c0 > 0 && is.c0 == 0) || (it.c1 > 0 && is.c1 == 0) || (it.c0 == 0 && is.c0 > 0) || (it.c1 == 0 && is.c1 > 0)) {
                    continue;
                }
                if (subsequence(start, t)) {
                    return start;
                }
                int sn = start.length();
                int rest = 2 * n - sn;
                for (int i = 0; i < rest; i++) {
                    char c = start.charAt(start.length() - k);
                    start += c;
                    if (subsequence(start, t)) {
                        return start;
                    }
                }
            }
        }
        return "";
    }

    void solve() throws IOException {
        int tt = Integer.parseInt(in.readLine());
        while (tt > 0) {
            tt--;
            String t = in.readLine();
            String s = solve1(t);
            out.append(String.format("%s\n", s));
        }
    }

    void test() throws IOException {
        Random random = new Random();
        for (; ; ) {
            int n = random.nextInt(5);
            StringBuilder sb = new StringBuilder(in.readLine());
            for (int i = 0; i < n; i++) {
                sb.append(random.nextInt(2) + '0');
            }
            String t = sb.toString();
            String a1 = solve1(t);
            String a2 = solve2(t);
            assert period(a1) == period(a2);
        }
    }

    String solve2(String t) {
        int n = t.length();
        for (int i = 0; i < n; i++) {
            mink = Integer.MAX_VALUE;
            List<String> ps = new ArrayList<>();
            permutation("", ps, 0, i);
            for (String p : ps) {
                String np = p + t;
                int m = np.length();

                char[] chs = new char[m];
                boolean[] vis = new boolean[m];
                cnt = 0;
                cal(chs, np, t, vis, 0, m);
            }
            if (mink <= n / 2 - 1) {
                break;
            }
        }
        return minks;
    }

}